 <?php
    // include("configMYSQL.php");
      include("configDB.php");
    // $id_per=$_REQUEST["id_per"];  //old

      //http://127.0.0.1/json2/getJSON2_.php?id_patient=กานดา วรประววัติ 202015-05-29%20172
    @$id_patient=$_REQUEST["id_patient"];
     // $id_call="172";  //test
     /*
     $tb="`tb_weight`";

	// $strSQL = "SELECT *   FROM   $tb  WHERE   id_per='".$id_per."' ;  "; //old
                 $strSQL = "SELECT *   FROM   $tb  WHERE   id_patient='".$id_patient."' ;  ";
	// $strSQL = "SELECT *   FROM   $tb  ;  ";
	 $objQuery = mysql_query($strSQL);
	 $intNumField = mysql_num_fields($objQuery);
	 $intNumRosw=mysql_num_rows($objQuery);
	 $resultArray = array();
	 while($obResult = mysql_fetch_array($objQuery))
	 {
		$arrCol = array();
		for($i=0;$i<$intNumField;$i++)
		{
			$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
		}
		   $arrCol["numrows"]=$intNumRosw;
		array_push($resultArray,$arrCol);
	 }
	
	 mysql_close($objConnect);
	
	 echo json_encode($resultArray);
      * 
      */
    
       
     
         // $id_patient="กานดา บุญประครอง 2014-05-19 142";
          if( strlen(  $id_patient ) > 0  )
          {
               $exstr=explode(" ",$id_patient  );
                $id_call=$exstr[3];
               if(  $id_call > 0 )
               {
                     $tb="`tb_weight`";
                     $tbJ="`tb_patientcleft`";
                   //  $strSQL = "SELECT *   FROM   $tb  WHERE   id_patient='".$id_call."' ;  ";
                    $strSQL = "SELECT *   FROM   $tb  
                                     LEFT  JOIN   $tbJ   ON   $tb.`id_patient`= $tbJ.`id_patient`
                                     WHERE   $tb.`id_patient`  ='".$id_call."'   
                                    ";
               // echo "<br>";
       
                     $objQuery = mysql_query($strSQL);
	 $intNumField = mysql_num_fields($objQuery);
	 $intNumRosw=mysql_num_rows($objQuery);
	 $resultArray = array();
	 while($obResult = mysql_fetch_array($objQuery))
	 {
		
        /*
		$arrCol = array();
		for($i=0;$i<$intNumField;$i++)
		{
			$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
		}
		   $arrCol["numrows"]=$intNumRosw;
		array_push($resultArray,$arrCol);
         */

		       $resultArray[]=$obResult;

	 }
         
                 
	
	 //mysql_close($objConnect);
	
	 echo json_encode($resultArray);
               }
          }
          

?>