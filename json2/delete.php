<?php
	$host='127.0.0.1';
	$uname='root';
	$pwd='1234';
	$db="mydatabase";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	$id=$_REQUEST['id'];
	 
	$flag['code']=0;
	 
	if($r=mysql_query("DELETE FROM sample WHERE id= '$id'",$con))
	{
		$flag['code']=1;
	}
	 
	print(json_encode($flag));
	mysql_close($con);
?>