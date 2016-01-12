<?php
	$objConnect = mysql_connect("localhost","root","");
	$objDB = mysql_select_db("mydatabase");
	$strSQL = "SELECT * FROM member WHERE 1  ";
	$objQuery = mysql_query($strSQL);
	 $intNumField = mysql_num_fields($objQuery);
	$resultArray = array();

	//echo  "<hr>";

	/*
	while($obResult = mysql_fetch_array($objQuery))
	{
		$arrCol = array();
		for($i=0;$i<$intNumField;$i++)
		{
			$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
		}
		array_push($resultArray,$arrCol);
	}
	*/

     while( $objQuery = mysql_fetch_assoc($objQuery) )
     {
            $output[] =  $objQuery;
     }

 
	     mysql_close($objConnect);
	
	 // json_encode($resultArray);
	     echo  json_encode($output);
?>