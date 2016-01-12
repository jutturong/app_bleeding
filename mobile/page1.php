<?php
         

                 //   $objConnect = mysql_connect("localhost","root","root");
	// $objDB = mysql_select_db("mydatabase");
	
	/*

	$strKeyword = $_POST["txtKeyword"];
	$strSQL = "SELECT * FROM customer WHERE 1 AND Name LIKE '%".$strKeyword."%'  ";

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
         * 
         */
              
              require("configDB.php");
              
             // $id_title= $_POST["id_title"];
              //$strSQL = "SELECT * FROM  `tb_feeding`  ; ";
             //  $strSQL = "SELECT * FROM  `tb_feeding`  WHERE    id_title = $id_title  ; ";
              $strSQL = "SELECT * FROM  `tb_feeding`   ";
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
	
	//mysql_close($connect);
	
	echo json_encode($resultArray);
        
               //echo  "[{\"Content\":\"การเลี้ยงทารกปากแหว่งเพดานโหว่ด้วยน้ำนมแม่\"}]";

?>
