package com.example.android.wcs3d;

/**
 * Created by linux on 16/4/2558.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import org.apache.http.client.methods.HttpGet;
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


public class linechart extends ActionBarActivity {

    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

    // SplashScreen.ip +
   //public  String url = "http://192.168.2.112/json2/getJSON2.php";
    public  String url =  SplashScreen.ip + "json2/getJSON2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // setContentView(R.layout.form_chartline);


     /*  test string json */
        // int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};


        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




        //openChart();
       // openChart_org();
        linechart();



/*
        Button btnChart = (Button) findViewById(R.id.btn_chart);

        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Draw the Income vs Expense Chart
                openChart();
            }
        };


        btnChart.setOnClickListener(clickListener);

  */

       // String url = "http://10.87.196.113/json2/getJSON2.php";


     /* -------------- config ----------------- */
        /*
        String url = "http://10.87.196.113/json2/getJSON2.php";
        int[] stackArr=new int[100];

        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id_user","income"));
        try{
            JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c=data.getJSONObject(i);
                stackArr[i]=c.getInt("val");

            }


           // openChart(stackArr);
            AlertDialog ad=adb.create();
            // ad.setMessage(MyArrList.toArray().toString()); //*OK
            ad.setMessage( "testing" ); //*OK
            ad.show(); //*OK

        }catch (JSONException e)
        {
          // e.printStackTrace();
            AlertDialog ad=adb.create();
            // ad.setMessage(MyArrList.toArray().toString()); //*OK
            ad.setMessage( "false connect!!" ); //*OK
            ad.show(); //*OK
        }

*/

      /* -------------- config ----------------- */

    }


    public  void  getMYSQL()
    {


        int[] stackArr=new int[100];

        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id_user","income"));
        try{
            JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c=data.getJSONObject(i);
                stackArr[i]=c.getInt("val");

            }


            /// openChart( stackArr);



        }catch (JSONException e)
        {
            e.printStackTrace();
        }
       // return  stackArr;
    }

    public String getJSONUrl(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
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


    private void openChart(double[] test1){



        /***---------------------------------   test config graph    **/
        int[] x = { 1,2,3,4,5,6,7,8 };
        //  int[] income = { 1500,2500,2700,3000,2800,3500,3700,3800};
        //  int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
        //  int[] line3 = {1000, 2500, 3500, 1000, 3800, 2000, 2300, 1400 }; // **********1.add data
        double[] line3 = test1;

        // Creating an  XYSeries for Income
        //    XYSeries incomeSeries = new XYSeries("Income");
        // Creating an  XYSeries for Expense
        //    XYSeries expenseSeries = new XYSeries("Expense");

        //test
        XYSeries testline3 = new XYSeries("testline3");  // **********2.add data

        // Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            //    incomeSeries.add(x[i], income[i]);
            //    expenseSeries.add(x[i],expense[i]);
            testline3.add(x[i],line3[i]);
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
        testRenderer.setColor(Color.BLACK);
        testRenderer.setPointStyle(PointStyle.CIRCLE);
        testRenderer.setFillPoints(true);
        testRenderer.setLineWidth(5);
        testRenderer.setDisplayChartValues(true);
        testRenderer.setChartValuesTextSize(25);  //size font value
        testRenderer.setDisplayChartValues(true);
        testRenderer.setChartValuesFormat(new DecimalFormat("##.##"));


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Year 2012");
        multiRenderer.setYTitle("Amount in Dollars");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }

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



    public void linechart()
    {
       /*
        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        ad.setMessage("test");
        ad.show();
        */

        //SplashScreen.ip +
        //String url = "http://10.87.196.113/json2/getJSON2.php";
        String url =  SplashScreen.ip  + "json2/getJSON2.php";

        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id_user","income"));




        double[] stackArr=new double[100];
        try{
            JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c = data.getJSONObject(i);


                stackArr[i]=c.getDouble("val");

            }


             openChart( stackArr);



        }catch (JSONException e)
        {
            e.printStackTrace();
        }


    }


    private void openChart_org(){




        int[] x = { 1,2,3,4,5,6,7,8 };
        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
        Double[] test1 = {2100.12, 2400.22, 2700.34, 2900.12, 2500.22, 2000.45, 3500.00, 3100.66 };







        // Creating an  XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Income and Expense Series

        XYSeries test1Series =new XYSeries("test line");

        for(int i=0;i<x.length;i++){
            incomeSeries.add(x[i], income[i]);
            expenseSeries.add(x[i],expense[i]);
            test1Series.add(x[i],test1[i]);

        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
        // Adding Expense Series to dataset
        dataset.addSeries(expenseSeries);

        dataset.addSeries(test1Series);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        // incomeRenderer.setColor(Color.WHITE);
        incomeRenderer.setColor(Color.RED);
        incomeRenderer.setPointStyle(PointStyle.CIRCLE);
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(5);
        incomeRenderer.setDisplayChartValues(true);



        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        //  expenseRenderer.setColor(Color.YELLOW);
        expenseRenderer.setColor(Color.BLUE);
        expenseRenderer.setPointStyle(PointStyle.CIRCLE);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(5);
        expenseRenderer.setDisplayChartValues(true);


       /*  test config line */
        XYSeriesRenderer test1Renderer=new XYSeriesRenderer();

        test1Renderer.setColor(Color.GREEN);


        test1Renderer.setPointStyle(PointStyle.CIRCLE);
        test1Renderer.setFillPoints(true);
        test1Renderer.setLineWidth(5);
        test1Renderer.setDisplayChartValues(true);
        /* addjust properties */
        test1Renderer.setDisplayChartValues(true);
        test1Renderer.setChartValuesTextSize(30);  //size font value
        test1Renderer.setChartValuesFormat(new DecimalFormat("#.##"));
        test1Renderer.setPointStrokeWidth(10);



        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Year 2012");
        multiRenderer.setYTitle("Amount in Dollars");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);
        multiRenderer.addSeriesRenderer(test1Renderer);

        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);

        // Start Activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
