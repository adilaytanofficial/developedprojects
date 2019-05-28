package adilaytan.healthcare.followup;


import android.content.Context;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SOAPER {
    private static final String NAMESPACE = "http://www.healthappnew.somee.com/";

    private static final String loginURL = "http://www.healthappnew.somee.com/loginprocess.asmx";
    private static final String login_ACTION = "http://www.healthappnew.somee.com/login";
    private static final String login_METHOD = "login";

    private static final String createURL = "http://www.healthappnew.somee.com/loginprocess.asmx";
    private static final String create_ACTION = "http://www.healthappnew.somee.com/createUser";
    private static final String create_METHOD = "createUser";

    private static final String personURL = "http://healthappnew.somee.com/getpatientinfo.asmx";
    private static final String person_ACTION ="http://www.healthappnew.somee.com/getPersonalInfo";
    private static final String person_METHOD = "getPersonalInfo";

    private static final String lastURL = "http://www.healthappnew.somee.com/getpatientinfo.asmx";
    private static final String last_ACTION ="http://www.healthappnew.somee.com/getLastInfo";
    private static final String last_METHOD = "getLastInfo";


    private static final String getValues_URL = "http://www.healthappnew.somee.com/getvalues.asmx";
    private static final String spo2_ACTION ="http://www.healthappnew.somee.com/allSPO2";
    private static final String spo2_METHOD = "allSPO2";

    private static final String temp_ACTION ="http://www.healthappnew.somee.com/allTEMP";
    private static final String temp_METHOD = "allTEMP";

    private static final String ecg_ACTION ="http://www.healthappnew.somee.com/allECG";
    private static final String ecg_METHOD = "allECG";

    private static final String pulse_ACTION ="http://www.healthappnew.somee.com/allPULSE";
    private static final String pulse_METHOD = "allPULSE";


    private static Context ctx;

    public SOAPER(Context context) {
        this.ctx = context;
    }

    public static String createVoid(String json){
        String rs = "null";
        SoapObject crReq = new SoapObject(NAMESPACE,create_METHOD);
        PropertyInfo crinfo = new PropertyInfo();
        crinfo.setName("data");
        crinfo.setValue(json.toString());
        crinfo.setType(String.class);
        crReq.addProperty(crinfo);
        SoapSerializationEnvelope enveloper = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        enveloper.dotNet = true;
        enveloper.setOutputSoapObject(crReq);
        HttpTransportSE trans = new HttpTransportSE(createURL);
        try {
            trans.call(create_ACTION,enveloper);
            SoapPrimitive res = (SoapPrimitive) enveloper.getResponse();
            rs = res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String loginVoid(String json){
        String rs = "null";
        SoapObject lgReq = new SoapObject(NAMESPACE,login_METHOD);
        PropertyInfo lginfo = new PropertyInfo();
        lginfo.setName("data");
        lginfo.setValue(json);
        lginfo.setType(String.class);
        lgReq.addProperty(lginfo);
        SoapSerializationEnvelope enveloper = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        enveloper.dotNet = true;
        enveloper.setOutputSoapObject(lgReq);
        HttpTransportSE trans = new HttpTransportSE(loginURL);
        try {
            trans.call(login_ACTION,enveloper);
            SoapPrimitive res = (SoapPrimitive) enveloper.getResponse();
            rs = res.toString().toLowerCase();
        } catch (IOException e) {
            rs = e.toString();
        } catch (XmlPullParserException e) {
            rs = e.toString();
        }
        return rs;
    }

    // calisiyor async task
    public static String getPersonalInfo(String patid){
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,person_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet = true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(personURL);
        try {
            se.call(person_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }

    // calisiyor
    public static String getLastInfo(String patid){
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,last_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet =true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(lastURL);
        try {
            se.call(last_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }

    // getSPO2 ?
    public String allSPO2(String patid){
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,spo2_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet =true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(getValues_URL);
        try {
            se.call(spo2_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }

    // getECG ?
    public String allECG(String patid)
    {
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,ecg_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet =true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(getValues_URL);
        try {
            se.call(ecg_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }

    // get Temp ?

    public String allTEMP(String patid)
    {
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,temp_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet =true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(getValues_URL);
        try {
            se.call(temp_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }

    // pulse ?

    public String allPULSE(String patid)
    {
        String res = "null";
        SoapObject sp = new SoapObject(NAMESPACE,pulse_METHOD);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("patid");
        pi.setValue(patid);
        pi.setType(String.class);
        sp.addProperty(pi);
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        env.dotNet =true;
        env.setOutputSoapObject(sp);
        HttpTransportSE se = new HttpTransportSE(getValues_URL);
        try {
            se.call(pulse_ACTION,env);
            SoapPrimitive primitive = (SoapPrimitive) env.getResponse();
            res = primitive.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return res;
    }


}
