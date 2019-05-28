using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.OleDb;
using Newtonsoft.Json;
using System.IO;
using System.Web.Script.Services;
using System.Text;

namespace HastaBilgiGetir
{
    /// <summary>
    /// Summary description for addvalues
    /// </summary>
    [WebService(Namespace = "http://yourwebsite.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class addvalues : System.Web.Services.WebService
    {

        private String conString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpRuntime.AppDomainAppPath + "\\datas\\datas.mdb";
        private OleDbCommand com;
        private OleDbConnection con;
        private OleDbDataReader dr;
                

       [WebMethod]
       public String addVal(String patid,String pulse,String spo2)
       {
           String res = "null";
           String date = getSystemDate();
           String normal_query = "INSERT INTO patient" + patid + " (patientid,value_date,pulse,spo2) VALUES ('" + patid + "','" + date + "','" + pulse + "','" + spo2 + "');";
           con = new OleDbConnection(conString);
           com = new OleDbCommand();
           con.Open();
           com.Connection = con;
           com.CommandText = normal_query;
           Object r = com.ExecuteNonQuery();
           if (r != null) res = "success";
           con.Close();
           return res;
       }

        [WebMethod]
        public String addECG(String patid,String ecg)
        {
            String res = "null";
            String date = getSystemDate();
            String json = ecgJSON(ecg);
            String query = "INSERT INTO ecg" + patid + " (patientid,value_date,ecgdata) VALUES ('" + patid + "','" + date + "','" + json + "');";
            con = new OleDbConnection(conString);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            Object r = com.ExecuteNonQuery();
            if (r != null) res = "success";
            con.Close();
            return res;
        }
        
        private String ecgJSON(String ecg)
        {
           List<String> b = new List<String>();
           String c = "";
           for (int i = 0; i < ecg.Length; i++)
           {
               if (ecg[i] != ',')
               {
                   c +=ecg[i];
                   if (i == ecg.Length - 1) b.Add(c);
               }
               else if (ecg[i] == ',')
               {
                   b.Add(c);
                   c = "";
               }
           }
           String res = JsonConvert.SerializeObject(b);
           return res;
        }
        private String getSystemDate()
        {
            String str = "";
            TimeZoneInfo ist = TimeZoneInfo.FindSystemTimeZoneById("Turkey Standard Time");
            DateTimeOffset localServerTime = DateTimeOffset.Now;
            DateTimeOffset localTime = TimeZoneInfo.ConvertTime(localServerTime, ist);
            str = localTime.DateTime.ToString();
            return str;
        }
    }
}
