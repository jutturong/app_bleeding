<?php
  // include_once("koneksi.php");   
 //  $objConnect = mysql_connect("localhost","root","1234");
  // $objDB = mysql_select_db("mydatabase");
  // $username = $_REQUEST['username']; 

/*
   $sqlString = "SELECT * FROM login WHERE username_user = '$username'";

   $rs = mysql_query($sqlString);

   if($rs){
      while($objRs = mysql_fetch_assoc($rs)){
         $output[] = $objRs;
      }

      echo json_encode($output);
   }
   mysql_close();
*/



   $objConnect = mysql_connect("localhost","root","1234");
   $objDB = mysql_select_db("mydatabase");
   $strSQL = "SELECT * FROM `linechart`    ";
   $objQuery = mysql_query($strSQL);
   

    
     while($obResult = mysql_fetch_array($objQuery))
   {
      $arrCol = array();
      for($i=0;$i<$intNumField;$i++)
      {
         $arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
      }
      array_push($resultArray,$arrCol);
   }
  




   
   mysql_close($objConnect);
   
   echo json_encode($resultArray);



?>