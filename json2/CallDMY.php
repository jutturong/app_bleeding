
<?php

        //    $dmyBirth="22/1/2557"; //test  for simmulate  
        //     //  $dmyBirth="22/1/2557";         

            $dmyBirth=$_POST["dmyBirth"];  // กานดา-บุญประครอง-2015-05-19
          //  $dmyBirth="กานดา-บุญประครอง-2015-05-19-142";
         //   $dmyBirth="กานดา บุญประครอง 2014-05-19 142"; //test
            //echo  $dmyBirth;
          //  echo "<br>";

            /*
$tm=strtotime('1/17/2006');   $currentDMY=date("n/d/Y");  
timestamp ของวันที่ 17 มกราคม 2006
*/
            
        
             if(   strlen($dmyBirth) > 0  )
             {
                 $con1=  explode(" ",  $dmyBirth);
          
              
        
             
                 //echo $con1[2]; //ok result
                 //echo "<br>";
              
              
                 $dmy_rec= $con1[2];
                 if(  strlen( $dmy_rec ) > 0 )
                 {
                       $exdmy=explode("-",$dmy_rec);
                      $con2=  $exdmy[1]."/".$exdmy[2]."/".$exdmy[0];
                     // echo "<br>";
                       $startTimeStamp =  strtotime($con2);
                 }
              
                 /*
                  echo $exbrt[2];
                 echo "<br>";
                 echo $exbrt[3];
                 echo "<br>";
                   echo $exbrt[4];
                 echo "<br>";
                  * 
                  */
                 
                 // $con2=  $con1[3]."/".$con1[4]."/".$con1[2];   //strtotime('1/17/2006');     //เดิม
                 
                 // $startTimeStamp =  strtotime($con2);
             }
         
      
       $currentDMY=date("n/d/Y"); //21/5/2015
      if(strlen($currentDMY )  > 0  )
      {
                 $endTimeStamp =  strtotime($currentDMY);           
                 
      }
      
      
    if( strlen( $startTimeStamp ) > 0  && strlen($endTimeStamp) > 0  )  
    {
      $timeDiff=abs($endTimeStamp - $startTimeStamp);
      $numberDays =round(  $timeDiff/86400 );  // 86400 seconds in one day         
      $calMonth=   $numberDays / 30  ;     // cal in Month
    }
      else
      {         
          $calMonth=0;
      }
      
          

      $resultArray = array();    
      $arrCol["countAGE"]=  $calMonth;  
      
     
      array_push($resultArray,$arrCol);    
      echo json_encode($resultArray);

?>