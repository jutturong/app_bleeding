<?php

 include("configDB.php");

         
            $id_per=$_REQUEST["id_per"];
           // $id_per=3;
            $year=$_REQUEST["year"];
            $weight=$_REQUEST["weight"];
            $food=$_REQUEST["food"];
            //$id_sex=$_REQUEST["id_sex"];
            $strAuto=$_REQUEST["strAuto"];
            
           //  $strAuto="กานดา บุญประครอง 2015-05-19 142"; //test
           //    $strAuto="กานดา บุญประครอง 2014-05-19 142"; //test
           //  echo $strAuto;
            // echo "<br>";
            if(  strlen($strAuto) > 0  )
            {
                  $exauto=explode(" ", $strAuto );
                  $id_patient = $exauto[3];  //.`id_patient`
                
            }

         
                $tb="`tb_weight`";
        
             // $str="  INSERT INTO  $tb (`id_weight`, `id_per`, `year`, `weight`, `food_weight`,`id_sex`)
              // VALUES (NULL, ' $id_per', '$year', '$weight', '$food','$id_sex');  "; 
                
              /*     //original   code  before  chang structure
             $str="  INSERT INTO `cleft`.`tb_weight` (`id_weight`, `id_per`, `year`, `weight`, `food_weight`) 
                         VALUES (NULL, ' $id_per', '$year', '$weight', '$food');  ";
             */
                
                // `id_patient` 
                
                /*
                  $str="  INSERT INTO `cleft`.`tb_weight` (`id_weight`, `id_per`, `year`, `weight`, `food_weight`) 
                         VALUES (NULL, '$id_patient', '$year', '$weight', '$food');  ";  
                */
                  
                    $str="  INSERT INTO `tb_weight` (`id_weight`, `id_patient`, `year`, `weight`, `food_weight`) 
                         VALUES (NULL, '$id_patient', '$year', '$weight', '$food');  ";  
             
                  
            $query=mysql_query($str) or die("fail query");
            $flag['code']=0;
            if(  $query  )
            {
               $flag['code']=1;
               echo"hi";
            }
	        print(json_encode($flag));
	        mysql_close($con);
    

	      
?>