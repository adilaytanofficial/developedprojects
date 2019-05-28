using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.OleDb;

namespace HastaBilgiGetir
{
    /// <summary>
    /// Summary description for getpatientinfo
    /// </summary>
    [WebService(Namespace = "http://yourwebsite.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class getpatientinfo : System.Web.Services.WebService
    {
        private OleDbCommand com;
        private OleDbConnection con;
        private OleDbDataReader dr;
        private OleDbDataAdapter adap;
        private String con_string = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpRuntime.AppDomainAppPath + "\\datas\\datas.mdb";


        private class Patient
        {
            public String patientid { get; set; }
            public String name { get; set; }
            public String surname { get; set; }
            public String age { get; set; }
            public String gender { get; set; }
        }

        private class LastInfo
        {   
            public String spo2 { get; set; }
            public String pulse { get; set; }
            public String datetime { get; set; }
        }
       
        [WebMethod]
        public String getPersonalInfo(String patid)
        {     
            String res = "null";
            String query = "SELECT * from patients where patientid=" + patid + "";
            Patient patient = new Patient();
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            if (dr.Read())
            {
                patient.patientid = dr["patientid"].ToString();
                patient.name = dr["name"].ToString();
                patient.surname = dr["surname"].ToString();
                patient.age = dr["age"].ToString();
                patient.gender = dr["gender"].ToString();
                res = JsonConvert.SerializeObject(patient);
            }
            else
            {
                res = "false";
                con.Close();
            }
            con.Close();
            return res;
        }


        [WebMethod]
        public String getLastInfo(String patid)
        {
            String res = "null";
            String query = "SELECT * from patient" + patid + " ORDER BY value_date DESC";
            LastInfo lst = new LastInfo();
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            if (dr.Read()){
                lst.spo2 = dr["spo2"].ToString();
                lst.pulse = dr["pulse"].ToString();
                lst.datetime = dr["value_date"].ToString();
                res = JsonConvert.SerializeObject(lst);
            }
            else
            {
                res = "false";
                con.Close();
            }
            con.Close();
            return res;
        }



  }
}
