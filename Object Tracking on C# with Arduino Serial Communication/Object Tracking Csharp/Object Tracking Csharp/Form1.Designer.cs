namespace Object_Tracking_Csharp
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.portList = new System.Windows.Forms.ComboBox();
            this.camList = new System.Windows.Forms.ComboBox();
            this.selectCam = new System.Windows.Forms.Button();
            this.selectport = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.baudrateList = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.last = new System.Windows.Forms.PictureBox();
            this.trackG = new System.Windows.Forms.TrackBar();
            this.trackR = new System.Windows.Forms.TrackBar();
            this.trackB = new System.Windows.Forms.TrackBar();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.stopCam = new System.Windows.Forms.Button();
            this.camPic = new System.Windows.Forms.PictureBox();
            this.portRefresh = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.last)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackG)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackR)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackB)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.camPic)).BeginInit();
            this.SuspendLayout();
            // 
            // portList
            // 
            this.portList.FormattingEnabled = true;
            this.portList.Location = new System.Drawing.Point(82, 12);
            this.portList.Name = "portList";
            this.portList.Size = new System.Drawing.Size(146, 21);
            this.portList.TabIndex = 0;
            this.portList.SelectedIndexChanged += new System.EventHandler(this.portList_SelectedIndexChanged);
            // 
            // camList
            // 
            this.camList.FormattingEnabled = true;
            this.camList.Location = new System.Drawing.Point(332, 12);
            this.camList.Name = "camList";
            this.camList.Size = new System.Drawing.Size(214, 21);
            this.camList.TabIndex = 1;
            // 
            // selectCam
            // 
            this.selectCam.Location = new System.Drawing.Point(552, 10);
            this.selectCam.Name = "selectCam";
            this.selectCam.Size = new System.Drawing.Size(75, 23);
            this.selectCam.TabIndex = 2;
            this.selectCam.Text = "Select Cam";
            this.selectCam.UseVisualStyleBackColor = true;
            this.selectCam.Click += new System.EventHandler(this.selectCam_Click);
            // 
            // selectport
            // 
            this.selectport.Location = new System.Drawing.Point(633, 10);
            this.selectport.Name = "selectport";
            this.selectport.Size = new System.Drawing.Size(75, 23);
            this.selectport.TabIndex = 3;
            this.selectport.Text = "Select Port";
            this.selectport.UseVisualStyleBackColor = true;
            this.selectport.Click += new System.EventHandler(this.selectport_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Ports List :";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(273, 15);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Cam List :";
            // 
            // baudrateList
            // 
            this.baudrateList.FormattingEnabled = true;
            this.baudrateList.Location = new System.Drawing.Point(82, 41);
            this.baudrateList.Name = "baudrateList";
            this.baudrateList.Size = new System.Drawing.Size(253, 21);
            this.baudrateList.TabIndex = 7;
            this.baudrateList.SelectedIndexChanged += new System.EventHandler(this.baudrateList_SelectedIndexChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 44);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(56, 13);
            this.label3.TabIndex = 8;
            this.label3.Text = "Baudrate :";
            // 
            // last
            // 
            this.last.Location = new System.Drawing.Point(383, 100);
            this.last.Name = "last";
            this.last.Size = new System.Drawing.Size(320, 240);
            this.last.TabIndex = 12;
            this.last.TabStop = false;
            this.last.Click += new System.EventHandler(this.last_Click);
            // 
            // trackG
            // 
            this.trackG.Location = new System.Drawing.Point(52, 407);
            this.trackG.Maximum = 255;
            this.trackG.Name = "trackG";
            this.trackG.Size = new System.Drawing.Size(247, 45);
            this.trackG.TabIndex = 14;
            this.trackG.Scroll += new System.EventHandler(this.trackG_Scroll);
            // 
            // trackR
            // 
            this.trackR.Location = new System.Drawing.Point(52, 359);
            this.trackR.Maximum = 255;
            this.trackR.Name = "trackR";
            this.trackR.Size = new System.Drawing.Size(247, 45);
            this.trackR.TabIndex = 15;
            this.trackR.Scroll += new System.EventHandler(this.trackR_Scroll);
            // 
            // trackB
            // 
            this.trackB.Location = new System.Drawing.Point(52, 451);
            this.trackB.Maximum = 255;
            this.trackB.Name = "trackB";
            this.trackB.Size = new System.Drawing.Size(247, 45);
            this.trackB.TabIndex = 16;
            this.trackB.Scroll += new System.EventHandler(this.trackB_Scroll);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 458);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(14, 13);
            this.label5.TabIndex = 17;
            this.label5.Text = "B";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 366);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(15, 13);
            this.label6.TabIndex = 17;
            this.label6.Text = "R";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(12, 415);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(15, 13);
            this.label7.TabIndex = 18;
            this.label7.Text = "G";
            // 
            // stopCam
            // 
            this.stopCam.Location = new System.Drawing.Point(471, 39);
            this.stopCam.Name = "stopCam";
            this.stopCam.Size = new System.Drawing.Size(109, 23);
            this.stopCam.TabIndex = 19;
            this.stopCam.Text = "Stop the Cam";
            this.stopCam.UseVisualStyleBackColor = true;
            this.stopCam.Click += new System.EventHandler(this.stopCam_Click);
            // 
            // camPic
            // 
            this.camPic.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.camPic.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.camPic.Location = new System.Drawing.Point(15, 100);
            this.camPic.Name = "camPic";
            this.camPic.Size = new System.Drawing.Size(320, 240);
            this.camPic.TabIndex = 20;
            this.camPic.TabStop = false;
            // 
            // portRefresh
            // 
            this.portRefresh.Location = new System.Drawing.Point(586, 39);
            this.portRefresh.Name = "portRefresh";
            this.portRefresh.Size = new System.Drawing.Size(117, 23);
            this.portRefresh.TabIndex = 21;
            this.portRefresh.Text = "Refresh Ports List";
            this.portRefresh.UseVisualStyleBackColor = true;
            this.portRefresh.Click += new System.EventHandler(this.portRefresh_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(715, 514);
            this.Controls.Add(this.portRefresh);
            this.Controls.Add(this.camPic);
            this.Controls.Add(this.stopCam);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.trackB);
            this.Controls.Add(this.trackR);
            this.Controls.Add(this.trackG);
            this.Controls.Add(this.last);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.baudrateList);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.selectport);
            this.Controls.Add(this.selectCam);
            this.Controls.Add(this.camList);
            this.Controls.Add(this.portList);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Object Tracking C# - Arduino Serial Communication";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.last)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackG)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackR)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackB)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.camPic)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox portList;
        private System.Windows.Forms.ComboBox camList;
        private System.Windows.Forms.Button selectCam;
        private System.Windows.Forms.Button selectport;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox baudrateList;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.PictureBox last;
        private System.Windows.Forms.TrackBar trackG;
        private System.Windows.Forms.TrackBar trackR;
        private System.Windows.Forms.TrackBar trackB;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Button stopCam;
        private System.Windows.Forms.PictureBox camPic;
        private System.Windows.Forms.Button portRefresh;
    }
}

