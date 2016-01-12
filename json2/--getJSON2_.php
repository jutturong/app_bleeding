 <?php
     include("configMYSQL.php");
     $id_per=$_REQUEST["id_per"];
     $tb="`tb_weight`";

	 $strSQL = "SELECT *   FROM   $tb  WHERE   id_per='".$id_per."' ;  ";
	// $strSQL = "SELECT *   FROM   $tb  ;  ";
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