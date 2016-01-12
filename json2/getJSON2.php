<?php

    /*
       #-- connect MYSQL server!! --
	$objConnect = mysql_connect("localhost","root","1234");
	$objDB = mysql_select_db("mydatabase");
    */
   
     include("configMYSQL.php");

	// $strSQL = "SELECT * FROM   `linechart`  WHERE 1  ";
	//select * from  linechart where  name='income';

	$id_user=trim($_POST["id_user"]);
	$strSQL = "SELECT * FROM   `linechart`  WHERE  name='".$id_user."' ;  ";
	$objQuery = mysql_query($strSQL);
	$intNumField = mysql_num_fields($objQuery);
	$resultArray = array();
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