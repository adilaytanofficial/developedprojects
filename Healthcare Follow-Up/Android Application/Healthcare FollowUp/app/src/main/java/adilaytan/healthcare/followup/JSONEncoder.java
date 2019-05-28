package adilaytan.healthcare.followup;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adilaytan.healthcare.followup.Structure.LastInfo;
import adilaytan.healthcare.followup.Structure.Parameter;
import adilaytan.healthcare.followup.Structure.Patient;

public class JSONEncoder {

   public JSONEncoder(){

   }

   // Works Fine
    public String loginJSON(String un,String pw) {
        String result = "";
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("username",un);
            jsonObj.put("password", pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result = jsonObj.toString();
        return result;
    }

    // Works Fine
    public String registerJSON(String un,String nm,String sn,String ag,String gn,String pw){
        String result="";
        JSONObject user = new JSONObject();
        try {
            user.put("username",un.toString());
            user.put("name",nm.toString());
            user.put("surname",sn.toString());
            user.put("age",ag.toString());
            user.put("gender",gn.toString());
            user.put("password",pw.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result = user.toString();
        return result;
    }

    // calisiyor
    public Patient getPatient(String json){
        Patient x = new Patient();
        try {
            JSONObject js = new JSONObject(json);
            x.setPatientid(js.getString("patientid"));
            x.setName(js.getString("name"));
            x.setSurname(js.getString("surname"));
            x.setAge(js.getString("age"));
            x.setGender(js.getString("gender"));
        } catch (JSONException e) {
            e.printStackTrace();
            x = null;
        }
        return x;
    }

    // calisiyor
    public LastInfo getLast(String json){
        LastInfo y = new LastInfo();
        try{
            JSONObject js = new JSONObject(json);
            y.setSpo2(js.getString("spo2"));
            y.setPulse(js.getString("pulse"));
            y.setDate(js.getString("datetime"));
            //y.setTime(js.getString(""));
        } catch (JSONException e) {
            e.printStackTrace();
            y = null;
        }
        return y;
    }

    // tp value

    public ArrayList<Parameter> GetTemp(String json)
    {
        ArrayList<Parameter> res = new ArrayList<>();
        Parameter pr;
        try{
            //JSONObject js = new JSONObject(json);
            JSONArray arr = new JSONArray(json);
            for (int i=0;i<arr.length();i++){
                pr = new Parameter();
                pr.setDate(arr.getJSONObject(i).getString("date"));
                pr.setValue(arr.getJSONObject(i).getString("value"));
                res.add(pr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    //

    public ArrayList<Parameter> GetPulse(String json)
    {
        ArrayList<Parameter> res = new ArrayList<>();
        Parameter pr;
        try{
            //JSONObject js = new JSONObject(json);
            JSONArray arr = new JSONArray(json);
            for (int i=0;i<arr.length();i++){
                pr = new Parameter();
                pr.setDate(arr.getJSONObject(i).getString("date"));
                pr.setValue(arr.getJSONObject(i).getString("value"));
                res.add(pr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    // oxy
    public ArrayList<Parameter> GetOxy(String json)
    {
        ArrayList<Parameter> res = new ArrayList<>();
        Parameter pr;
        try{
            //JSONObject js = new JSONObject(json);
            JSONArray arr = new JSONArray(json);
            for (int i=0;i<arr.length();i++){
                pr = new Parameter();
                pr.setDate(arr.getJSONObject(i).getString("date"));
                pr.setValue(arr.getJSONObject(i).getString("value"));
                res.add(pr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    // ECG
    public ArrayList<Parameter> GetECG(String json)
    {
        ArrayList<Parameter> res = new ArrayList<>();
        Parameter pr;
        try{
            //JSONObject js = new JSONObject(json);
            JSONArray arr = new JSONArray(json);
            for (int i=0;i<arr.length();i++){
                pr = new Parameter();
                pr.setDate(arr.getJSONObject(i).getString("date"));
                pr.setValue(arr.getJSONObject(i).getString("value"));
                res.add(pr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    // get ECG Data
    public ArrayList<Integer> getECGData(String json)
    {
        ArrayList<Integer> res = new ArrayList();
        try {
            JSONArray array = new JSONArray(json);
            for (int i=0;i<array.length();i++){
                res.add(array.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    // GetPatientList

    // get ECG Data
    public ArrayList<Patient> getPatientList(String json)
    {
        ArrayList<Patient> res = new ArrayList<Patient>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i=0;i<array.length();i++){
                Patient pt = new Patient();
                pt.setPatientid(array.getJSONObject(i).getString("patientid"));
                pt.setName(array.getJSONObject(i).getString("name"));
                pt.setSurname(array.getJSONObject(i).getString("surname"));
                res.add(pt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }
}
