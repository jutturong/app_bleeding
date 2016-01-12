<?php
   // include("configMYSQL.php");
    include("configDB.php");

    //$strUsername = $_POST["strUser"];
	//$strPassword = $_POST["strPass"];
//  $year=$_REQUEST["year"];
     $strUsername = $_REQUEST["strUser"];
     $strPassword = $_REQUEST["strPass"];

	$tb="`tb_personal`";

	
	$strSQL = "SELECT * FROM   ".$tb."   WHERE 1 
		AND username = '".$strUsername."'  
		AND password = '".$strPassword."'  
		";
		

   // $strSQL = "SELECT * FROM   ".$tb."   WHERE 1 ";


	$objQuery = mysql_query($strSQL) or die(" can't  MYSQL query!! ");
	$objResult = mysql_fetch_array($objQuery);
	$intNumRows = mysql_num_rows($objQuery);
	if($intNumRows==0)
	{
		$arr['StatusID'] = "0"; 
		$arr['MemberID'] = "0"; 
		$arr['Error'] = "Incorrect Username and Password";	
		//$arr['Error'] = "";	
	}
	else
	{
		$arr['StatusID'] = "1"; 
		//$arr['StatusID'] = $objResult["StatusID"]; 
		//$arr['MemberID'] = $objResult["MemberID"]; 
		//$arr['MemberID']=$objResult["Id_per"];
		$arr['Id_per']=$objResult["Id_per"];
		$arr['Name']=$objResult["Name"];
		$arr['id_sex']=$objResult["id_sex"];

		$arr['Error'] = "";	
	}

	/**
		$arr['StatusID'] // (0=Failed , 1=Complete)
		$arr['MemberID'] // MemberID
		$arr['Error' // Error Message
	*/
	
	//mysql_close($objConnect);
	
	echo json_encode($arr);

?>