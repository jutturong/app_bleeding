<?php

   // include("configMYSQL.php");
    include("configDB.php");
    //  $id_per=$_REQUEST["id_per"];    
    // $tb="`tb_weight`";

    //autocom.php

   

    /*
    $tb="`tb_patientcleft`";
  // $tb= "  `province`    ";
     
            //   $name = trim($_REQUEST["name"]);
              // $name="t";

	// $strSQL = "SELECT *   FROM   $tb  WHERE   id_per='".$id_per."' ;  ";
	//$strSQL = "SELECT *   FROM   $tb  WHERE  username LIKE('%$name%') ;  ";
     $strSQL = "SELECT *   FROM   `tb_patientcleft`   ;  ";
	 $objQuery = mysql_query($strSQL);        
               //  mysql_query("SET NAMES utf8");         
	// $intNumField = mysql_num_fields($objQuery);
	// $intNumRosw=mysql_num_rows($objQuery);
	  $resultArray = array();
	  $arrCol=array();
	 while($obResult = mysql_fetch_assoc($objQuery))
	 {
		/*
		$arrCol = array();
		for($i=0;$i<$intNumField;$i++)
		{
			$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
		}
			*/

		  //$arrCol["numrows"]=$intNumRosw;
		 //   $arrCol[]=$obResult;
		  //  array_push($resultArray,$arrCol);
	// }
	
	// mysql_close($objConnect);
	 //   echo json_encode($resultArray);
	//	echo json_encode($arrCol);


		$tb="  `tb_patientcleft`   ";
		$strSQL = "SELECT *   FROM   $tb   ;  ";
		$objQuery = mysql_query($strSQL);   
		mysql_query("SET NAMES utf8"); 
		$arrCol=array();
		while($obResult = mysql_fetch_assoc($objQuery))
		{
				//$arrCol["numrows"]=$obResult;
				$arrCol[]=$obResult;
		}
		echo json_encode($arrCol);

?>
