package com.example.android.wcs3d;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private MediaPlayer mediaPlayer;

    private  String url="";

    private  String TAG = "list";
   // private   VideoView video1;

    private int position = 0;

    private  String StatusID;

    private String urlcontent1="https://www.khunlook.com/healthcare/index.php?option=com_jumi&view=application&fileid=6&Itemid=105"; //พัฒฯาการ
    private String Urlcontent2="https://www.khunlook.com/healthcare/index.php?option=com_jumi&view=application&fileid=5&Itemid=109"; //วีัคตซีน
    private  String urlcontact="http://kkucleft.kku.ac.th/data.php";
   // ViewSwitcher Vs;
  //  public  String  ip="http://10.87.196.113/";
    public    String  urlweb1= SplashScreen.ip + "mobile/page1.php"; //โปรแกรมให้นมแม่ หน้า 1
    public    String  urlweb2 = "http://www.thaicreate.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.layout_login);

        // import class
       // thai th=new  thai();



        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


          Intent intent=getIntent();
          final String Id_per =  intent.getStringExtra("Id_per");
          //checkID(Id_per );  // for check ID in table
          final String  id_sex = intent.getStringExtra("id_sex");
                StatusID = intent.getStringExtra("StatusID");

          // Log.e("id_sex ",id_sex);

          final AlertDialog.Builder adb=new AlertDialog.Builder(this);



        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });




        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if( groupPosition==0) switch (childPosition)
                {
                    case 0: // FORM input  weight





                        Intent newActivity0 = new Intent (MainActivity.this, form_member.class);
                        newActivity0.putExtra("Id_per",Id_per);
                        //StatusID
                        newActivity0.putExtra("StatusID",StatusID);
                       // newActivity0.putExtra("id_sex",id_sex);
                        startActivity(newActivity0);


                        break;
                    case 1: // Line-chart


                      //  Intent newActivity1 = new Intent (MainActivity.this, linechart.class );
                        Intent newActivity1 = new Intent (MainActivity.this, lineChart2.class );
                        newActivity1.putExtra("Id_per",Id_per);
                        newActivity1.putExtra("id_sex",id_sex);
                        newActivity1.putExtra("StatusID",StatusID);
                        startActivity(newActivity1);
                      //  finish();

                        /*
                        Intent newActivity1 = new Intent (MainActivity.this, thai102.class);

                        newActivity1.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity1);
                        */




                        break;
                    case 2: //103

                      //  url="http://www.caiproject.com/subjects/thai/season3/103/video/thai103ad.mp4";
                     //   thai_section(url);

                        Intent newActivity2 = new Intent (MainActivity.this, thai103.class);

                        newActivity2.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity2);

                        break;
                    case 3: //104
                      //  url="http://www.caiproject.com/subjects/thai/season3/104/video/thai104ad.mp4";
                      //  thai_section(url);
                        Intent newActivity3 = new Intent (MainActivity.this, thai104.class);

                        newActivity3.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity3);

                        break;
                    case 4: //LOGOUT
                   // url="http://www.caiproject.com/subjects/thai/season3/105/video/thai105ad.mp4";
                   // thai_section(url);
                      //  Intent newActivity4 = new Intent (MainActivity.this, thai105.class);
                        Intent newActivity4 = new Intent (MainActivity.this, SplashScreen.class);
                        newActivity4.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                //StatusID
                        newActivity4.putExtra("StatusID","0" );
                        startActivity(newActivity4);

                    break;

                    case 5: //106 นักพูดคนเก่ง
                     //   url="http://www.caiproject.com/subjects/thai/season3/106/video/thai106ad.mp4";
                     //   thai_section(url);
                        Intent newActivity5 = new Intent (MainActivity.this, thai106.class);

                        newActivity5.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity5);

                        break;

                    case 6: //107กำเนิดของเสียง
                        //url="http://www.caiproject.com/subjects/thai/season3/107/video/thai107ad.mp4";
                       // thai_section(url);
                        Intent newActivity6 = new Intent (MainActivity.this, thai107.class);

                        newActivity6.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity6);

                        break;

                    case 7: //108 การสร้างคำนำความหมาย
                       // url="http://www.caiproject.com/subjects/thai/season3/108/video/thai108ad.mp4";
                      //  thai_section(url);
                        Intent newActivity7 = new Intent (MainActivity.this, thai108.class);

                        newActivity7.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity7);

                        break;
                    case 8: //109ฉันเป็นใครเธอรู้ไหม
                      //  url="http://www.caiproject.com/subjects/thai/season3/109/video/thai109ad.mp4";
                      //  thai_section(url);
                        Intent newActivity8 = new Intent (MainActivity.this, thai109.class);

                        newActivity8.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity8);

                        break;
                    case 9: //110 หน้าที่ของฉันสำคัญไฉน
                       // url="http://www.caiproject.com/subjects/thai/season3/110/video/thai110ad.mp4";
                      // thai_section(url);
                        Intent newActivity9 = new Intent (MainActivity.this, thai110.class);

                        newActivity9.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity9);

                        break;
                    case 10: //111     เรียงร้อยถ้อยคำเป็นคำกลอน

                     //   url="http://www.caiproject.com/subjects/thai/season3/111/video/thai111ad.mp4";
                     //   thai_section(url);
                        Intent newActivity10 = new Intent (MainActivity.this, thai111.class);

                        newActivity10.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity10);

                        break;
                    case 11:  //112  โคลงโลกนิติ
                       // url="http://www.caiproject.com/subjects/thai/season3/112/video/thai112ad.mp4";
                       // thai_section(url);
                        Intent newActivity11 = new Intent (MainActivity.this, thai112.class);

                        newActivity11.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity11);
                        break;
                    case 12: //113   สังข์ทอง
                       // url="http://www.caiproject.com/subjects/thai/season3/113/video/thai113ad.mp4";
                       // thai_section(url);
                        Intent newActivity12 = new Intent (MainActivity.this, thai113.class);

                        newActivity12.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity12);
                        break;
                    case 13:  //214
                      //  url="http://www.caiproject.com/subjects/thai/season3/214/video/thai214ad.mp4";
                      //  thai_section(url);
                        Intent newActivity13 = new Intent (MainActivity.this, thai214.class);

                        newActivity13.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity13);

                        break;
                    case 14: //215 สะท้อนนักเขียน
                       // url="http://www.caiproject.com/subjects/thai/season3/215/video/thai215ad.mp4";
                       // thai_section(url);
                        Intent newActivity14 = new Intent (MainActivity.this, thai215.class);

                        newActivity14.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity14);
                        break;
                    case 15: //216  สะท้อนเรื่องที่ฟังและพูด
                   // url="http://www.caiproject.com/subjects/thai/season3/216/video/thai216ad.mp4";
                   // thai_section(url);
                        Intent newActivity15 = new Intent (MainActivity.this, thai216.class);

                        newActivity15.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity15);
                    break;

                    case 16: //217 พรแห่งความหวัง

                      //  url="http://www.caiproject.com/subjects/thai/season3/217/video/thai217ad.mp4";
                      //  thai_section(url);
                        Intent newActivity16 = new Intent (MainActivity.this, thai217.class);

                        newActivity16.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity16);

                        break;

                    case 17: // 218  โน้มใจให้เชื่อ

                  //  url="http://www.caiproject.com/subjects/thai/season3/218/video/thai218ad.mp4";
                  //  thai_section(url);

                        Intent newActivity17 = new Intent (MainActivity.this, thai218.class);

                        newActivity17.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity17);


                    break;

                    case 18: //219  พูดดีมีเงิน
                      //  url="http://www.caiproject.com/subjects/thai/season3/219/video/thai219ad.mp4";
                      //  thai_section(url);
                        Intent newActivity18 = new Intent (MainActivity.this, thai219.class);

                        newActivity18.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity18);

                        break;

                    case 19: //220  เพื่อนสอนเพื่อน
                       // url="http://www.caiproject.com/subjects/thai/season3/220/video/thai220ad.mp4";
                       // thai_section(url);
                        Intent newActivity19 = new Intent (MainActivity.this, thai220.class);

                        newActivity19.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                        startActivity(newActivity19);
                        break;
                }
             else if(  groupPosition==1) switch (childPosition) //math
                {
                     case 0: //math 101 แทนแกรม 7 ชิ้นสร้างสรรค์

                        getURL(urlcontent1);

                        break;

                     case 1: //math 102  การหาตัวคูณร่วมน้อ
                         getURL(Urlcontent2);
                        break;
                }
                else if(  groupPosition==2) switch (childPosition)
                {
                       case 0:

                           Intent newActivity0 = new Intent (MainActivity.this, content.class);
                           newActivity0.putExtra("id_title", "1");
                           newActivity0.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                           startActivity(newActivity0);

                        break;

                    case 1:
                        Intent newActivity1 = new Intent (MainActivity.this, content.class);
                        newActivity1.putExtra("id_title","2");
                        newActivity1.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(newActivity1);

                        break;

                    case 2:
                        Intent newActivity2 = new Intent (MainActivity.this, content.class);
                        newActivity2.putExtra("id_title","3");
                        newActivity2.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(newActivity2);

                        break;

                    case 3:
                    Intent newActivity3 = new Intent (MainActivity.this, content.class);
                    newActivity3.putExtra("id_title","4");
                    newActivity3.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(newActivity3);

                    break;

                    case 4:
                        Intent newActivity4 = new Intent (MainActivity.this, content.class);
                        newActivity4.putExtra("id_title","5");
                        newActivity4.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(newActivity4);

                        break;

                    case 5:
                        Intent newActivity5 = new Intent (MainActivity.this, content.class);
                        newActivity5.putExtra("id_title","6");
                        newActivity5.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(newActivity5);

                        break;


                }
                else if ( groupPosition==3)
                    switch (childPosition)
                {
                    case 0:
                        getURL(urlcontact);
                        break;


                }


                return false;
            }
        });

    }

    private void  getURL(String url) // send to web browser
    {
       // Vs.showNext();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
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


    // ภาษาไทย 101
    public void thai101 ()
    {


        this.setContentView(R.layout.activity_vdo);
        try{

            final String TAG = "list";

            setContentView(R.layout.activity_vdo);
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            String link="http://www.caiproject.com/subjects/thai/season3/101/video/thai101ad.mp4";

            final VideoView video1=(VideoView)findViewById(R.id.videoView1);

            MediaController mediaController=new MediaController(this);
            mediaController.setAnchorView(video1);

            Uri video=Uri.parse(link);

            video1.setMediaController(mediaController);
            video1.setVideoURI(video);

            Intent intent=new Intent(Intent.ACTION_VIEW,video);
            intent.setDataAndType(video,"video/mp4");
            mediaController.setAnchorView(video1);


            video1.requestFocus();

            video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){


                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i(TAG, "Duration = " + video1.getDuration());
                }
            });

            video1.start();
            startActivity(intent);
            finish();


        }
        catch (Exception e){
            e.printStackTrace();
           // Toast.makeText(this,"Error connecting",Toast.LENGTH_SHORT).show();

        }







}
    public void thai102 ()
    {
        this.setContentView(R.layout.activity_vdo);
        try{

            setContentView(R.layout.activity_vdo);
            String link="http://www.caiproject.com/subjects/thai/season3/102/video/thai102ad.mp4";
            VideoView video1=(VideoView)findViewById(R.id.videoView1);
            MediaController mediaController=new MediaController(this);
            mediaController.setAnchorView(video1);
            Uri video=Uri.parse(link);

            video1.setMediaController(mediaController);
            video1.setVideoURI(video);

            Intent intent=new Intent(Intent.ACTION_VIEW,video);
            intent.setDataAndType(video,"video/mp4");
            video1.requestFocus();
            startActivity(intent);
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Error connecting",Toast.LENGTH_SHORT).show();
        }
    }
    public  void  thai_section(String url)
    {
        this.setContentView(R.layout.activity_vdo);
        try{

            setContentView(R.layout.activity_vdo);

           // String link="http://www.caiproject.com/subjects/thai/season3/102/video/thai102ad.mp4";
            String link=url;
            VideoView video1=(VideoView)findViewById(R.id.videoView1);
            MediaController mediaController=new MediaController(this);
            mediaController.setAnchorView(video1);
            Uri video=Uri.parse(link);

            video1.setMediaController(mediaController);
            video1.setVideoURI(video);

            Intent intent=new Intent(Intent.ACTION_VIEW,video);
            intent.setDataAndType(video,"video/mp4");
            video1.requestFocus();
            startActivity(intent);
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Error connecting",Toast.LENGTH_SHORT).show();
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();




        listDataHeader.add("ติดตามการเจริญเติบโตด้านน้ำหนัก");

        List<String> section1 = new ArrayList<String>();
        section1.add("-ฟอร์มกรอกข้อมูลผลการชั่ง"); //101
        section1.add("-แสดงกราฟน้ำหนัก"); //102



        listDataHeader.add("ประเมินและติดตามการเจริญเติบโต");
        List<String> section2 = new ArrayList<String>();
        section2.add("-พัฒนาการตามช่วงวัย");






        listDataHeader.add("่การให้นม");
        List<String> section3 = new ArrayList<String>();
        section3.add("การเลีั้ยงทารกปากแหว่งเพดานโหว่ด้วยนมแม่");
        section3.add("ประโยชน์ของนมแม่ต่อทารกที่มีปัญหาปากแหว่งเพดานโหว่");
        section3.add("เทคนิคการอุ้มทารกเพื่อช่วยให้ริมฝีปากกระชับกับเต้านมแม่");
        section3.add("เทคนิคการประคองเต้านม");
        section3.add("จังหวะการบีบนมแม่ขณะทารกดูดนมแม่ในทารกที่มีภาวะปากแหว่งและเพดานโหว่");
        section3.add("การให้นมด้วยวิธีอื่น");
        section3.add("การดูแลทารกหลังจากอิ่มนมแม่");

        listDataHeader.add("ติดต่อเรา");
        List<String> section4 = new ArrayList<String>();
        section4.add("-ที่อยู่เบอร์โทรศัพท์");


        listDataChild.put(listDataHeader.get(0), section1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), section2); // Header, Child data
        listDataChild.put(listDataHeader.get(2), section3); // Header, Child data
        listDataChild.put(listDataHeader.get(3), section4); // Header, Child data


    }

    public void checkID(String str)
    {
        Toast.makeText(MainActivity.this, str , Toast.LENGTH_SHORT).show();
    }

}



