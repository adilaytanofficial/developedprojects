using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using Newtonsoft.Json;
using System.Data.OleDb;
using System.Security.Cryptography;
using System.Text;
using System.Web.Script.Services;

namespace JSON_Web_Service
{
    /// <summary>
    /// Summary description for sendrequest
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class sendrequest : System.Web.Services.WebService
    {

        private String conString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpRuntime.AppDomainAppPath + "\\datas\\datas.mdb";
        private OleDbCommand com;
        private OleDbConnection con;
        private OleDbDataReader dr;
        private List<Users> userList;

        private class Users
        {
            public int id { get; set; }
            public String username { get; set; }
            public String password { get; set; }
        }



        [WebMethod]
        public string getDatas()
        {
            String result = "null";
            String query = "SELECT * from users ORDER BY patientid DESC";
            con = new OleDbConnection(conString);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = query;
            dr = com.ExecuteReader();
            Users user;
            userList = new List<Users>();
            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    user = new Users();
                    user.id = int.Parse(dr["patientid"].ToString());
                    user.username = dr["uname"].ToString();
                    user.password = dr["pass"].ToString();
                    userList.Add(user);
                }
                result = JsonConvert.SerializeObject(userList);
            }
            else
            {
                result = "error";
            }
            con.Close();
            return result;
        }


        [WebMethod]
        
        public void addData(String username,String password)
        {
            String pass = getMD5(password);
            String normal_query = "INSERT INTO users (uname,pass) VALUES ('" +  username + "','" + pass + "');";
            con = new OleDbConnection(conString);
            com = new OleDbCommand();
            con.Open();
            com.Connection = con;
            com.CommandText = normal_query;
            com.ExecuteNonQuery();
            con.Close();
        }

        private static string getMD5(string input)
        {
            MD5 md5 = MD5.Create();
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(input);
            byte[] hash = md5.ComputeHash(inputBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }
            return sb.ToString();
        }

    }
}
