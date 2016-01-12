<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />



</head>
<body>

<?php
   // echo  phpinfo();
      include("configMYSQL.php");
     $tb=" `tb_patientcleft` ";
     $str="SELECT *  FROM `tb_patientcleft` ";


   $query=mysql_query( $str) or die("MySQL SERVER cant' Query!! ");
   //mysql_query("SET NAMES UTF8");
   while(   $row=mysql_fetch_array($query) )
   {
        
     // echo $row[1]."     ".$row[2]."     ".$row[9] ;
      echo $row[1]."     ".$row[2];  
      echo "<br>";
  }

 // echo "ทดสอบ";
?>

</body>
</html>