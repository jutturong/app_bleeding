package com.example.android.wcs3d;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SplashScreen extends Activity {

    // Splash screen timer
    //private static int SPLASH_TIME_OUT = 2000;  //OK code

    String strUser;
    String strPass;

    static  String ip ="http://10.87.196.113/";
    //String url="http://10.87.59.117/json2/checkLogin_.php";
    String url=  ip +  "json2/checkLogin_.php";

    InputStream is=null;
    String result=null;
    String line=null;

    String Id_per;
    String id_sex;
    String Name;
    String strError;
    String StatusID;

   // final AlertDialog.Builder ad = new AlertDialog.Builder(this);

    //test dialogbox
    //private  AlertDialog dia1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);


        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }





        /*    //OK code  config
        //setContentView(R.layout.activity_splash);  //original
		new Handler().postDelayed(new Runnable() {



			@Override
			public void run() {

                  Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
			}
		}, SPLASH_TIME_OUT);

        */


        final EditText txtUser = (EditText) findViewById(R.id.txtUsername);
        final EditText txtPass = (EditText) findViewById(R.id.txtPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        txtUser.setText("tawanchai");
        txtPass.setText("admin");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                /*
                String url = "http://10.87.196.113/json2/checkLogin_.php";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser", txtUser.getText().toString()));
                params.add(new BasicNameValuePair("strPass", txtPass.getText().toString()));
                String resultServer  = getHttpPost(url,params);


                String strStatusID = "0";
                String strMemberID = "0";
                Integer  Id_per = 0;
                String strError = "Unknow Status!";
                String strName ="0";
                JSONObject c;

                try {
                    c = new JSONObject(resultServer);
                    strStatusID = c.getString("StatusID");
                    strMemberID = c.getString("MemberID");
                  //  strMemberID = c.getString("MemberID");
                    Id_per = c.getInt("Id_per");
                    strError = c.getString("Error");
                    strName = c.getString("Name");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if(Id_per.equals(0))
                {
                    ad.setTitle("Error! ");
                    ad.setIcon(android.R.drawable.arrow_up_float);
                    ad.setPositiveButton("Close", null);
                    ad.setMessage(strError);
                    ad.show();
                    txtUser.setText("");
                    txtPass.setText("");
                }
                else
                {
                    Toast.makeText(SplashScreen.this, " ยินดีต้อนรับเข้าสู่ระบบ " + strName , Toast.LENGTH_SHORT).show()
                     Intent newActivity = new Intent(SplashScreen.this,MainActivity.class);
                     newActivity.putExtra("Id_per", Id_per );
                      startActivity(newActivity);

                }
                 */


                strUser = txtUser.getText().toString();
                strPass = txtPass.getText().toString();

                if (strUser.length() == 0) {
                    Toast.makeText(getBaseContext(), " INSERT username !! ", Toast.LENGTH_SHORT).show();
                } else if (strPass.length() == 0) {
                    Toast.makeText(getBaseContext(), " INSERT password !! ", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin();
                }

            }
        });




    }

    public void checkLogin()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("strUser",strUser));
        nameValuePairs.add(new BasicNameValuePair("strPass",strPass));
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");

        }catch (Exception e)
        {
           Log.e("Fail 1",e.toString());
           Toast.makeText(getApplicationContext(),"Invalid IP",Toast.LENGTH_LONG);
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }catch (Exception e)
        {
            Log.e("Fail 2",e.toString());

        }

        try{
            JSONObject json_data = new JSONObject(result);
            Id_per=json_data.getString("Id_per");
            id_sex=json_data.getString("id_sex");
            Name=json_data.getString("Name");
            strError=json_data.getString("Error");
            StatusID=json_data.getString("StatusID");

          //  Toast.makeText(getBaseContext(),"StatusID : "+StatusID,Toast.LENGTH_SHORT).show(); //1
          //  Toast.makeText(getBaseContext(),"Id_per : "+Id_per,Toast.LENGTH_SHORT).show(); //112
          //  Toast.makeText(getBaseContext(),"ยินดีต้อนรับ  : "+Name,Toast.LENGTH_SHORT).show(); //admin

        }catch (Exception e)
        {

            Log.e("Fail 3",e.toString());
        }


        /*
        if( Id_per.equals("")  || Name.equals("") )
        {
            Toast.makeText(getBaseContext()," Login Fail!! ",Toast.LENGTH_SHORT).show();
          //  ad.setTitle("Error! ");
          //  ad.setIcon(android.R.drawable.arrow_up_float);
          //  ad.setPositiveButton("Close", null);
          //  ad.setMessage(strError);
          //  ad.show();
           // txtUser.setText("");
           // txtPass.setText("");
        }
        */

       // if( StatusID.length() == 0  )
        if( StatusID.equals("0")  )
        {
            Toast.makeText(getBaseContext()," Login Fail!! ",Toast.LENGTH_SHORT).show();
        }
        //else if (  StatusID.length() == 1 )
        else if (  StatusID.equals("1") )
        {
            Toast.makeText(getBaseContext(),"ยินดีต้อนรับ  : "+Name ,Toast.LENGTH_SHORT).show();
            Intent newActivity = new Intent(SplashScreen.this,MainActivity.class);
            newActivity.putExtra("Id_per", Id_per );
            newActivity.putExtra("id_sex", id_sex );
            newActivity.putExtra("StatusID" ,StatusID );

            startActivity(newActivity);
        }


    }



    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }






}
