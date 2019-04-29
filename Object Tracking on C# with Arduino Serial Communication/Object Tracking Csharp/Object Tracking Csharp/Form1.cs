using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using AForge.Video;
using AForge.Video.DirectShow;
using AForge.Imaging.Filters;
using AForge;
using AForge.Imaging;
using System.Drawing.Imaging;

namespace Object_Tracking_Csharp
{
    public partial class Form1 : Form
    {
        String portname;
        int baudrate = 9600;
        SerialPort device;
        private VideoCaptureDevice cur_cam;
        private FilterInfoCollection webcams;
        bool filter=false,device_open = false;
        String cur = "";
        int R, G, B;


        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            fillPortList();
            fillCamList();
        }

        private void fillPortList()
        {
            string[] ports = SerialPort.GetPortNames();
            string[] baudrates = { "300","600", "1200", "2400", "4800", "9600", "14400", "19200", "28800", "38400", "57600", "115200" };
            portList.Items.AddRange(ports);
            baudrateList.Items.AddRange(baudrates);
            if (portList.Items.Count > 0) portList.SelectedIndex = 0; baudrateList.SelectedIndex = 5;
        }

        private void fillCamList()
        {
            camList.Items.Clear();
            webcams = new FilterInfoCollection(FilterCategory.VideoInputDevice);
            foreach(FilterInfo cam in webcams){
                camList.Items.Add(cam.Name);
            }
            camList.SelectedIndex = 0;
        }

        private void portList_SelectedIndexChanged(object sender, EventArgs e)
        {
            portname = portList.SelectedItem.ToString();
        }

        private void baudrateList_SelectedIndexChanged(object sender, EventArgs e)
        {
            baudrate = int.Parse(baudrateList.SelectedItem.ToString());
        }

        private void selectport_Click(object sender, EventArgs e)
        {
            device = new SerialPort(portname,baudrate,Parity.None,8,StopBits.One);
            if(!device.IsOpen){
                device.Open();
                device_open = true;
                MessageBox.Show(portname + " Port is Activated!");
            }
        }

        private void selectCam_Click(object sender, EventArgs e)
        {
            cur_cam = new VideoCaptureDevice(webcams[camList.SelectedIndex].MonikerString);
            cur_cam.NewFrame += new NewFrameEventHandler(cur_cam_NewFrame);
            cur_cam.DesiredFrameRate = 20; // in each second 20fps
            cur_cam.DesiredFrameSize = new Size(320, 240);
            cur_cam.Start();
            if (cur_cam.IsRunning) filter = true;
        }

        private void cur_cam_NewFrame(object sender, NewFrameEventArgs eventArgs)
        {
            Bitmap img = (Bitmap)eventArgs.Frame.Clone();
            Bitmap ls = (Bitmap)eventArgs.Frame.Clone();
            camPic.Image = img;
            if(filter == true){
                EuclideanColorFiltering ec = new EuclideanColorFiltering();
                ec.CenterColor = new RGB(Color.FromArgb(R,G,B));
                ec.Radius= 50;
                ec.ApplyInPlace(ls);
                FindObject(ls);
            }
        }


        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (device != null) 
                device.Write("off");
            if (cur_cam != null && cur_cam.IsRunning) cur_cam.Stop();
        }

  
        private void FindObject(Bitmap im)
        {
            BlobCounter blobCounter = new BlobCounter();
            blobCounter.MinWidth = 5;
            blobCounter.MinHeight = 5;
            blobCounter.FilterBlobs = true;
            blobCounter.ObjectsOrder = ObjectsOrder.Size;
            

            BitmapData objectsData = im.LockBits(new Rectangle(0, 0, im.Width, im.Height), ImageLockMode.ReadOnly, im.PixelFormat);
            Grayscale grayscaleFilter = new Grayscale(0.2125, 0.7154, 0.0721);
            UnmanagedImage grayImage = grayscaleFilter.Apply(new UnmanagedImage(objectsData));
            im.UnlockBits(objectsData);

            blobCounter.ProcessImage(im);
            Rectangle[] rects = blobCounter.GetObjectsRectangles();
            Blob[] blobs = blobCounter.GetObjectsInformation();

            last.Image = im;
            String state = "null";
            foreach (Rectangle recs in rects)
            {
                if (rects.Length > 0)
                {
                    Rectangle objectRect = rects[0];
                    
                    Graphics g = camPic.CreateGraphics();
                    using (Pen pen = new Pen(Color.FromArgb(252, 3, 26), 2))
                    {
                        g.DrawRectangle(pen, objectRect);
                    }
                    
                    int objectX = objectRect.X + (objectRect.Width / 2);
                    int objectY = objectRect.Y + (objectRect.Height / 2);
                    g.Dispose();



                    state = getState(objectRect);
                   

                     this.Invoke((MethodInvoker)delegate
                     {
                         if (state != "null" && cur != state)
                         {
                             cur = state;
                             sendData(state);
                         }
                     });
                }
               
            }
            
             if(rects.Length <= 0 && cur != "begin")
             {
                  cur = "begin";
                  sendData("null");
                 // if no rectangle, null
             }
        }


        String getState(Rectangle rc)
        {
            // 320/3 = 107 , 213, 320
            // 240/3 = 80, 160,240 
            String data = "null";
            if (rc.Location.X <= (107) && rc.Location.Y <= (80)) data = "led1";
            else if ((rc.Location.X > 107 && rc.Location.X <= (213)) && rc.Location.Y <= (80)) data = "led2";
            else if ((rc.Location.X > (213) && rc.Location.X <= 320) && rc.Location.Y <= (80)) data = "led3";
            else if ((rc.Location.X <= 107) && (rc.Location.Y > 80 && rc.Location.Y <= 160)) data = "led4";
            else if ((rc.Location.X > 107 && rc.Location.X <= 213) && (rc.Location.Y > 80 && rc.Location.Y <= 160)) data = "led5";
            else if ((rc.Location.X > 213 && rc.Location.X <= 320) && (rc.Location.Y > 80 && rc.Location.Y <= 160)) data = "led6";
            else if ((rc.Location.X <= 107) && (rc.Location.Y > 160 && rc.Location.Y <= 240)) data = "led7";
            else if ((rc.Location.X > 107 && rc.Location.X <= 213) && (rc.Location.Y > 160 && rc.Location.Y <= 240)) data = "led8";
            else if ((rc.Location.X > 213 && rc.Location.X <= 320) && (rc.Location.Y > 160 && rc.Location.Y <= 240)) data = "led9";
            return data;
        }


        void sendData(String data)
        {
            // 320/3 = 107 , 213, 320
            // 240/3 = 80, 160,240 
            if (device_open == true)
            {
                device.Write(data);
            }
        }

        private void trackR_Scroll(object sender, EventArgs e)
        {
            R = trackR.Value;
        }

        private void trackG_Scroll(object sender, EventArgs e)
        {
            G = trackG.Value;
        }

        private void trackB_Scroll(object sender, EventArgs e)
        {
            B = trackB.Value;
        }

        private void stopCam_Click(object sender, EventArgs e)
        {
            if (cur_cam.IsRunning) cur_cam.Stop();
        }


        private void portRefresh_Click(object sender, EventArgs e)
        {
            fillPortList();
        }

        private void last_Click(object sender, EventArgs e)
        {

        }
    }
}
