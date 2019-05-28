using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.OleDb;
using Newtonsoft.Json;
using System.Text;
using System.Security.Cryptography;

namespace HastaBilgiGetir
{
    /// <summary>
    /// Summary description for loginprocessasmx
    /// </summary>
    [WebService(Namespace = "http://yourwebsite.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    
    public class loginprocessasmx : System.Web.Services.WebService
    {

        private OleDbDataAdapter adap;
        private OleDbConnection con;
        private OleDbCommand com;
        private OleDbDataReader dR;
        private String con_string = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpRuntime.AppDomainAppPath + "\\datas\\datas.mdb";


        [WebMethod]
        public String login(String data)
        {
            String res = "null", query = "";
            LoginDetails lg = JsonConvert.DeserializeObject<LoginDetails>(data);
            lg.password = md5(lg.password); 
            query = "SELECT * from users where username='" + lg.username.ToString() + "' AND password='" + lg.password.ToString() + "';";
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dR = com.ExecuteReader();
            if (dR.Read()){
                res = dR["patientid"].ToString();
            }
            else
            {
                res = "false";
            }
            con.Close();
            return res;
        }


        [WebMethod]
        public String createUser(String data)
        {
            String result = "null";
            int patid;
            Account ac = JsonConvert.DeserializeObject<Account>(data);
            String add = "INSERT INTO users(username,password) values ('" + ac.username.ToString() + "','" + ac.password.ToString() + "')";
            String id_query = "Select @@Identity";
            con = new OleDbConnection(con_string);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = add;
            com.ExecuteNonQuery();
            com.CommandText = id_query;
            patid = (int)com.ExecuteScalar();
            String queryadd = "INSERT INTO patients (patientid,name,surname,age,gender,weight,size) values ('" + patid + "','" + ac.name + "','" + ac.surname + "','" + ac.age + "','" + ac.gender + "','" + ac.weight + "','" + ac.size + "')";
            String table = "CREATE TABLE patient" + patid + "([patientid] int NOT NULL,[date] date NOT NULL,[time] time NOT NULL,[spo2] varchar(255) NOT NULL,[pulse] varchar(255) NOT NULL)"; 
            com.CommandText = queryadd;
            com.ExecuteNonQuery();
            com.CommandText = table;
            com.ExecuteNonQuery();
            con.Close();
            result = "Tebrikler " + ac.name + " " + ac.surname + " profiliniz başarıyla oluşturuldu!";
            return result;
        }



        public class Account
        {
            public String username { get; set; }
            public String name { get; set; }
            public String surname { get; set; }
            public byte age { get; set; }
            public String gender { get; set; }
            public String password { get; set; }
            public String weight { get; set; }
            public String size { get; set; }
        }

        public class LoginDetails
        {
            public String username { get; set; }
            public String password { get; set; }
        }

        public static string md5(string input)
        {
            StringBuilder hash = new StringBuilder();
            MD5CryptoServiceProvider md5provider = new MD5CryptoServiceProvider();
            byte[] bytes = md5provider.ComputeHash(new UTF8Encoding().GetBytes(input));

            for (int i = 0; i < bytes.Length; i++)
            {
                hash.Append(bytes[i].ToString("x2"));
            }
            return hash.ToString();
        }

        

    }
}
