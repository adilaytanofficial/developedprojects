using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.OleDb;
using Newtonsoft.Json;


namespace HastaBilgiGetir
{
    /// <summary>
    /// Summary description for getvalues
    /// </summary>
    [WebService(Namespace = "http://yourwebsite.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class getvalues : System.Web.Services.WebService
    {
        private OleDbCommand com;
        private OleDbConnection con;
        private OleDbDataReader dr;
        private OleDbDataAdapter adap;
        private String con_string = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpRuntime.AppDomainAppPath + "\\datas\\datas.mdb";

        [WebMethod]
        public String allSPO2(String patid)
        {
            String res = "";
            String query = "SELECT * FROM patient" + patid.ToString() + " ORDER BY value_date DESC";
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            Parameter pr;
            List<Parameter> parms = new List<Parameter>();
            if (dr.HasRows)
            {
                while(dr.Read()){
                    String tar = dr["value_date"].ToString();
                    DateTime dt = DateTime.Parse(tar);
                    String extr = dt.ToString("dd.MM.yyyy");
                    String extm = dt.ToString("hh:mm:ss");
                    pr = new Parameter();
                    pr.date = extr;
                    pr.time = extm;
                    pr.value = dr["spo2"].ToString();
                    parms.Add(pr);
                }
                res = JsonConvert.SerializeObject(parms);
            }
            else
            {
                res = "null";
            } 
            con.Close();
            return res;
        }
        
        [WebMethod]
        public String allPULSE(String patid)
        {
            String res = "";
            String query = "SELECT * FROM patient" + patid.ToString() + " ORDER BY value_date DESC";
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            Parameter pr;
            List<Parameter> parms = new List<Parameter>();
            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    String tar = dr["value_date"].ToString();
                    DateTime dt = DateTime.Parse(tar);
                    String extr = dt.ToString("dd.MM.yyyy");
                    String extm = dt.ToString("hh:mm:ss");
                    pr = new Parameter();
                    pr.date = extr;
                    pr.time = extm;
                    pr.value = dr["pulse"].ToString();
                    parms.Add(pr);
                }
                res = JsonConvert.SerializeObject(parms);
            }
            else
            {
                res = "null";
            }
            con.Close();
            return res;
        }
        
        [WebMethod]
        public String allECG(String patid)
        {
            String res = "";
            String query = "SELECT * from ecg" + patid.ToString() + " ORDER BY value_date DESC";
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            Parameter pr;
            List<Parameter> parms = new List<Parameter>();
            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    String tar = dr["value_date"].ToString();
                    DateTime dt = DateTime.Parse(tar);
                    String extr = dt.ToString("dd.MM.yyyy");
                    String extm = dt.ToString("hh:mm:ss");
                    pr = new Parameter();
                    pr.date = extr;
                    pr.time = extm;
                    pr.value = dr["ecgdata"].ToString();
                    parms.Add(pr);
                }
                res = JsonConvert.SerializeObject(parms);
            }
            else
            {
                res = "null";
            }
            con.Close();
            return res;
        }

        //

        public class Parameter
        {
            public String date { get; set; }
            public String time { get; set; }
            public String value { get; set; }

        }

        [WebMethod]
        private String dateTime()
        {
            TimeZoneInfo ist = TimeZoneInfo.FindSystemTimeZoneById("Turkey Standard Time");
            DateTimeOffset localServerTime = DateTimeOffset.Now;
            DateTimeOffset localTime = TimeZoneInfo.ConvertTime(localServerTime, ist);
            return localTime.DateTime.ToString();
        }

    }
}
