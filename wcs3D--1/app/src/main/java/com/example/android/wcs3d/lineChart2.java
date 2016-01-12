package com.example.android.wcs3d;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by linux on 28/4/2558.
 */
public class lineChart2 extends Activity {

    //  String url = "http://10.87.59.117/json2/getJSON2_.php";
   public String url =  SplashScreen.ip +  "json2/getJSON2_.php";

      String Id_per;
      int  year;
      double[] stackY=new double[100];
      double[] stackX=new double[100];
      int numrows;
      private   String id_sex;

      String sex_detail;

    String id;
    InputStream is=null;
    String result=null;
    String line=null;
    String  StatusID;

    /*
    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };
    */

    // search patient
    //private  String  urlautocomp="http://10.87.59.117/json2/autocomp_patient.php";
    private  String  urlautocomp=  SplashScreen.ip +  "json2/autocomp_patient.php";


    private AutoCompleteTextView autocomplete1;
    private  ArrayAdapter<String> adapter;
    private  List<String> arrList = new ArrayList<String>();
    private ImageButton  img_search;
    //private String strSrch; //for search by autoCompleteTextView1

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_patient);  //modify

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

          Intent intent=getIntent();
          Id_per=intent.getStringExtra("Id_per");
          //id_sex=intent.getStringExtra("id_sex");  //ok intent
          StatusID=intent.getStringExtra("StatusID");

         // Toast.makeText(lineChart2.this, Id_per , Toast.LENGTH_SHORT).show();
         // Toast.makeText(lineChart2.this,StatusID,Toast.LENGTH_LONG).show();

        //  callMYSQL(); OK chart

        //search  patient
        autocomplete1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arrList);
        autocomplete1.setAdapter(adapter);
        strMYSQL();

        img_search=(ImageButton)findViewById(R.id.img_search);
        //strSrch=autocomplete1.getText().toString();




        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //  testDialog(autocomplete1.getText().toString()); //test dialog

              //  final AlertDialog.Builder adb=new AlertDialog.Builder(lineChart2.this);
             //   AlertDialog ad=adb.create();
               // ad.setMessage(autocomplete1.getText().toString());


                String send=autocomplete1.getText().toString();
                if( !send.equals("") )
                {
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    //  params.add(new BasicNameValuePair("id_per",Id_per)); //OK
                    //   params.add(new BasicNameValuePair("id_patient", Id_per ));  //test debug  //ok
                    params.add(new BasicNameValuePair("id_patient", autocomplete1.getText().toString()));  //test debug  //ok

                  //  testDialog(params.toString());
                  //  testDialog(url);
                    try {
                        JSONArray data__ = new JSONArray(getHttpPost(url, params));
                        testDialog(data__.toString());
                    }
                    catch (Exception e)
                    {
                        testDialog("parser json false");

                    }

                }

                //  callMYSQL();  //OK chart

            }
        });


    }

    public void testDialog(String str)
    {
        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        //ad.setMessage(autocomplete1.getText().toString());
        ad.setMessage(str);
        ad.show();
    }

    public  void  strMYSQL() //autocomplete
    {

        //strName=autocomplete1.getText().toString();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        // params.add(new BasicNameValuePair("name", strName )); //OK

        try{
            JSONArray data = new  JSONArray(getHttpPost(urlautocomp,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c = data.getJSONObject(i);

                //  id_sex=c.getInt("id_sex");


                // arrname[i]=c.getString("name");
                //PROVINCE_NAME
               // arrname[i]=c.getString("name");
              //  arrlastname[i]=c.getString("lastname");

              //  Toast.makeText(lineChart2.this, c.getString("name") , Toast.LENGTH_SHORT).show();

                arrList.add(c.getString("name") + " " + c.getString("lastname") + " " + c.getString("birthdate") + " " + c.getString("id_patient") );
                // txtbirth[i]=c.getString("birthdate");



            }


            Log.e("autocomplete sucssess ","");


        }catch (JSONException e)
        {
            // e.printStackTrace();
            Log.e("false autocomplete", e.toString());
        }


    }


    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
           // httpPost.setEntity(new UrlEncodedFormEntity(params));
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





    public void callMYSQL()
    {
       // ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
       // nameValuePairs.add(new BasicNameValuePair("id_per", Id_per));



        List<NameValuePair> params=new ArrayList<NameValuePair>();
      //  params.add(new BasicNameValuePair("id_per",Id_per)); //OK
       //   params.add(new BasicNameValuePair("id_patient", Id_per ));  //test debug  //ok
        params.add(new BasicNameValuePair("id_patient", autocomplete1.getText().toString() ));  //test debug  //ok

       //Toast.makeText(getApplicationContext(), "ทดสอบ"  ,Toast.LENGTH_LONG);

        try{
            // String url =  SplashScreen.ip +  "json2/getJSON2_.php";
            JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c = data.getJSONObject(i);
                    numrows=c.getInt("numrows");
                  //  id_sex=c.getInt("id_sex");
                    stackY[i]=c.getDouble("weight");
                  //  stackY[i]=c.getDouble("year");
                    stackX[i]=c.getDouble("year");

                    id_sex=c.getString("id_sex");

               // Toast.makeText(getApplicationContext(), id_sex  ,Toast.LENGTH_LONG);


            }



             Log.e("JSON success ","" + id_sex );

            //showDia(id_sex);
            openChart(stackY,stackX, numrows,id_sex);

        }catch (JSONException e)
        {
           // e.printStackTrace();
            Log.e("try fail 1",e.toString());
        }

       // int max=stackX.length;
       // max = max +2;

       // Toast.makeText(getApplicationContext(),"size of "+max,Toast.LENGTH_LONG);

    }



    private void openChart(double[] test1,double[] test2,int max,String id_sex )
    {



        if( id_sex.equals("1") )
        {
            sex_detail = "เพศชาย";
        }
        else if( id_sex.equals("2") )
        {
            sex_detail = "เพศหญิง";
        }
        else
        {
            sex_detail = "";
        }


        /***---------------------------------   test config graph    **/
        // double[] x = { 1,2,3,4,5,6,7,8 };  // x=test2
       //  double[] line3 = test1;
        //  int[] income = { 1500,2500,2700,3000,2800,3500,3700,3800};
        //  int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
        //  int[] line3 = {1000, 2500, 3500, 1000, 3800, 2000, 2300, 1400 }; // **********1.add data




        // Creating an  XYSeries for Income
        //    XYSeries incomeSeries = new XYSeries("Income");
        // Creating an  XYSeries for Expense
        //    XYSeries expenseSeries = new XYSeries("Expense");

        //test
        XYSeries testline3 = new XYSeries("testline3");  // **********2.add data

        // Adding data to Income and Expense Series

          //for(int i=0;i<x.length;i++){
         for(int i=0;i< max; i++){
            //    incomeSeries.add(x[i], income[i]);
            //    expenseSeries.add(x[i],expense[i]);
           // testline3.add(x[i],line3[i]);
             testline3.add(test2[i],test1[i]);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        //   dataset.addSeries(incomeSeries);
        // Adding Expense Series to dataset
        //    dataset.addSeries(expenseSeries);

        //test
        dataset.addSeries(testline3); // **********3.add data


   /*
        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.BLACK);
        incomeRenderer.setPointStyle(PointStyle.CIRCLE);
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(5);
        incomeRenderer.setDisplayChartValues(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.YELLOW);
        expenseRenderer.setPointStyle(PointStyle.CIRCLE);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(5);
        expenseRenderer.setDisplayChartValues(true);

 */

        // **********4.add data
        XYSeriesRenderer testRenderer = new XYSeriesRenderer();
        testRenderer.setColor(Color.RED);
        testRenderer.setPointStyle(PointStyle.CIRCLE);
        testRenderer.setFillPoints(true);
        testRenderer.setLineWidth(3);
        testRenderer.setDisplayChartValues(true);
        testRenderer.setChartValuesTextSize(20);  //font size of Label  X && Y
        testRenderer.setDisplayChartValues(true);
        testRenderer.setChartValuesFormat(new DecimalFormat("##.##"));
        testRenderer.setDisplayBoundingPoints(true);
        testRenderer.setPointStrokeWidth(5);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle(" กราฟแสดงน้ำหนักตามเกณฑ์อายุ " + sex_detail );
        multiRenderer.setChartTitleTextSize(30);

        multiRenderer.setXTitle(" อายุ (เดือน) ");
        multiRenderer.setXLabels(25);

        multiRenderer.setYTitle(" น้ำหนัก (กิโลกรัม) ");
        multiRenderer.setYLabels(25);

        multiRenderer.setAxisTitleTextSize(30);

        multiRenderer.setLabelsTextSize(20); //size font  x

        multiRenderer.setBackgroundColor(Color.BLACK); // back ground color
        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setLabelsColor(Color.WHITE); //color of label TEXT

        multiRenderer.setShowLabels(true);

        multiRenderer.setBarSpacing(0.1);

        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setMarginsColor(Color.RED); // front ground color

        multiRenderer.setYAxisMin(0); //set begin value Y
        multiRenderer.setXAxisMin(0); //set begin value X

        multiRenderer.setMargins(new int[]{10,20,10,10});

        multiRenderer.setPanEnabled(false,false);
        multiRenderer.setShowGridX(true);
        multiRenderer.setShowGridY(true);


        /*    //month array
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }
        */



        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        //   multiRenderer.addSeriesRenderer(incomeRenderer);
        //   multiRenderer.addSeriesRenderer(expenseRenderer);
        multiRenderer.addSeriesRenderer(testRenderer); //// **********5.add data

        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);

        // Start Activity
        startActivity(intent);

        /***---------------------------------   test config graph    **/

    }

    private  void  showDia(String str) //test dialogbox
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        ad.setMessage(str);
        ad.show();
    }

}
