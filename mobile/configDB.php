<?php
    $host="127.0.0.1";
    $us="root";
    $ps="";
    $DB="cleft"; 
    $connect=  mysql_connect($host,$us,$ps) or die(" Can't Connect DB SERVER !! "); 
    mysql_select_db($DB) or die(" Can't select DB SERVER !! ");
    mysql_query("SET NAMES UTF-8");
    mysql_set_charset('utf8',$connect);

?>

