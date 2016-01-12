<?php
include("configMYSQL.php");
	$strUsername = $_POST["strUser"];
	$strPassword = $_POST["strPass"];
    $tb="tb_personal";  // `tb_personal`

     $strUsername="user";
     $strPassword="clefttwc";

    $strSQL = "SELECT * FROM   $tb  WHERE   username='$strUsername'  AND  password ='$strPassword'   ; ";
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