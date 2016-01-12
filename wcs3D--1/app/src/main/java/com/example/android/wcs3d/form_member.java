package com.example.android.wcs3d;

/**
 * Created by linux on 7/4/2558.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class form_member extends Activity {

    String stryear;
    String strweight;
    String strFood;

    //String  url="http://10.87.59.117/json2/insertWeight.php";
    String  url=  SplashScreen.ip + "json2/insertWeight.php";


    InputStream is=null;
    String result=null;
    String line=null;
    int code;
    String  id_per;
    String  StatusID;
    //final AlertDialog.Builder adb=new AlertDialog.Builder(this);


    //---- Autocomplete  ค้นหาชื่อคนป่วย


    // private  String  urlautocomp="http://10.87.196.113/json2/autocom.php";
    //private  String  urlautocomp="http://10.87.59.117/json2/autocomp_patient.php";
    private  String  urlautocomp= SplashScreen.ip + "json2/autocomp_patient.php";

    private AutoCompleteTextView autocomplete1;
    private String[] arrname=new String[1000];
    private String[] arrlastname=new String[1000];
    private String strName;
    private  List<String> arrList = new ArrayList<String>();
    private  ArrayAdapter<String> adapter;



    // date year  current
   // private  String  callDMY="http://10.87.59.117/json2/CallDMY.php";
    private  String  callDMY= SplashScreen.ip +  "json2/CallDMY.php";


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    // private EditText  edtDate;
    private int yeardmy, month, day;
    private String  strbirth;
    private String beginDate;
    private String countMonth;

  //  private  String[] txtbirth = new String[100];
    private Button btn_calbrith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_formmember);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

         Intent intent=getIntent();
         id_per=intent.getStringExtra("Id_per");
         StatusID=intent.getStringExtra("StatusID");

       //  // Toast.makeText(lineChart2.this, id_sex + sex_detail , Toast.LENGTH_SHORT).show();

       // Toast.makeText(form_member.this,StatusID,Toast.LENGTH_LONG).show();


        if( StatusID.equals("0")  || StatusID.length()==0  )
        {

          /*
            Intent newActivity1 = new Intent (MainActivity.this, lineChart2.class );
            newActivity1.putExtra("Id_per",Id_per);
            newActivity1.putExtra("id_sex",id_sex);
            newActivity1.putExtra("StatusID",StatusID);
            startActivity(newActivity1);
            */
            Intent sendIntent=new Intent( form_member.this , SplashScreen.class);
            sendIntent.putExtra("StatusID",StatusID);
            startActivity(sendIntent);
        }


         final EditText year=(EditText) findViewById(R.id.year);
         final EditText weight=(EditText) findViewById(R.id.weight);
         final EditText food_weight=(EditText) findViewById(R.id.food_weight);



        Button btn_member=(Button) findViewById(R.id.btn_member);
         btn_member.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 stryear = year.getText().toString();
                 strweight = weight.getText().toString();
                 strFood = food_weight.getText().toString();
                 insert();
             }
         });

        strMYSQL(); //call name
        //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arrList  );\
        autocomplete1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arrList);
        autocomplete1.setAdapter(adapter);

        // date month year  edittext
       // dateView = (TextView) findViewById(R.id.birthdate);
        // edtDate = (EditText) findViewById(R.id.edtDate);
        calendar = Calendar.getInstance();
        yeardmy = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(yeardmy , month+1, day);

       // final TextView birthdate=(TextView)findViewById(R.id.birthdate);
       // birthdate=(TextView)findViewById(R.id.birthdate);
         // beginDate=birthdate.getText().toString();


      //  yearstr=birthdate.getText().toString();
      //  callmonth();
      //  year.setText(countMonth);

       // strbirth=autocomplete1.getText();



        final AlertDialog.Builder adb=new AlertDialog.Builder(this);


        btn_calbrith=(Button)findViewById(R.id.btn_calbrith);
        btn_calbrith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                AlertDialog ad=adb.create();
                ad.setMessage(autocomplete1.getText());
                ad.show();
                 */

                callmonth();
                year.setText(countMonth);

            }
        });

    }


    private void showDate(int year, int month, int day) {


        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year + 543 ));

        //edtDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }


    public void  callmonth() //cal year month
    {
        //strName=autocomplete1.getText().toString();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("dmyBirth", autocomplete1.getText().toString() )); //OK

        try{
            JSONArray data = new  JSONArray(getHttpPost(callDMY ,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                 JSONObject c = data.getJSONObject(i);
                 countMonth = c.getString("countAGE");

            }

            Log.e("callmonth sucssess ","");

        }catch (JSONException e)
        {
            // e.printStackTrace();
            Log.e("false callmonth",e.toString());
        }

    }


    public  void  strMYSQL() //autocomplete
    {
        List<NameValuePair> params=new ArrayList<NameValuePair>();
       // params.add(new BasicNameValuePair("name", strName )); //OK
        try{
            JSONArray data = new  JSONArray(getHttpPost(urlautocomp,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c = data.getJSONObject(i);
                arrname[i]=c.getString("name");
                arrlastname[i]=c.getString("lastname");


              //  Toast.makeText(getBaseContext(), c.getString("name") , Toast.LENGTH_SHORT).show();

                arrList.add(c.getString("name") + " " + c.getString("lastname") + " " + c.getString("birthdate") + " " + c.getString("id_patient") );
            }
            Log.e("autocomplete sucssess ","");
        }catch (JSONException e)
        {
            // e.printStackTrace();
            Log.e("false autocomplete",e.toString());
        }


    }

    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
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



    public void insert()
    {
        ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
          nameValuePairs.add(new BasicNameValuePair("year",stryear ));
          nameValuePairs.add(new BasicNameValuePair("weight",strweight));
          nameValuePairs.add(new BasicNameValuePair("food",strFood));
          nameValuePairs.add(new BasicNameValuePair("id_per",id_per));
          nameValuePairs.add(new BasicNameValuePair("strAuto", autocomplete1.getText().toString() )); //OK

          try{
              HttpClient httpClient=new DefaultHttpClient();
              HttpPost   httpPost=new  HttpPost(url);
              httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
              HttpResponse response=httpClient.execute(httpPost);
              HttpEntity entity=response.getEntity();
              is=entity.getContent();
              Log.e("pass 1","connection success");

              sendMessage(" บันทึกข้อมูลสำเร็จ ");

          }catch (Exception e)
          {
              Log.e("Fail ",e.toString());
              Toast.makeText(getApplicationContext(),"Invalid IP Address",Toast.LENGTH_LONG);
              StringBuilder sb=new StringBuilder();

          }

        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb=new StringBuilder();
            while((line=reader.readLine()) != null )
            {
                sb.append(line+"\n");

            }
            is.close();
            result=sb.toString();
            Log.e("pass 2 ","connection success");

        }catch (Exception e)
        {
            Log.e("Fail 2",e.toString());
        }

        try {
            JSONObject json_data=new JSONObject(result);
            code=(json_data.getInt("code"));
            if( code==1)
            {
                Toast.makeText(getBaseContext(),"Inserted Successfully",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getBaseContext(),"Sorry, Try Again",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e)
        {
            Log.e("Fail 3",e.toString());
        }

    }

    public void  sendMessage(String str)
    {
       /*
        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        ad.setMessage(str);
        ad.show();
        */

        Toast.makeText(form_member.this, str , Toast.LENGTH_SHORT).show();

    }


}
