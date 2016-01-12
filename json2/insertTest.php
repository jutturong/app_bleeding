<?php
       //echo phpinfo();
       include("configDB.php");
       //include("configMYSQL.php");


       //insertTest.php


/*
ชื่อ   name
นามสกุล  lastname
เล ประชาชน    id_card
เบอร์โทรศัพท์    telephone
เพศ                id_sex   1=ชาย , 2=หญิง
วันเดือนปี เกิด    birthdate
ที่อยู่               address
จังหวัดเกิด        province_id
การวินิจฉัยโรค      diagnosis
        
*/


        $name= $_POST["name"];
           //$name="ทดสอบ";
        $lastname=$_POST["lastname"];
        $id_card=$_POST["id_card"];
        $telephone=$_POST["telephone"];
       
        $id_sex=$_POST["id_sex"];
        //$id_sex="หญิง";  //test
        if( $id_sex == "ชาย"  ) 
        {
             $id_va_sex=1;

        }elseif( $id_sex == "หญิง"  )
        {
             $id_va_sex=2;
        }

             //echo $id_va_sex;
             //echo "<br>";

        
        $birthdate=$_POST["birthdate"];  // receive   16/5/2558 

        //$birthdate="16/5/2558 ";

        if(  strlen($birthdate) > 0   )  // format 2015-05-04
        {
               $arrbth=explode("/", $birthdate );  
               $y=$arrbth[2];
               $yconv= $y - 543;
               $conv_dmy= $yconv."-".$arrbth[1]."-".$arrbth[0];

        }

              //echo $conv_dmy;


        $address=$_POST["address"];

      

        $province_id=$_POST["province_id"];
        //$province_id="28-kอนแก่น"; //test

        if(  strlen($province_id) > 0  )
        {
            $arrprov=explode("-", $province_id );
            $id_prov=$arrprov[0]; 
        }

        //echo  $id_prov;

        
        $diagnosis=$_POST["diagnosis"];


/*
INSERT INTO `cleft`.`tb_patientcleft` (`id_patient`, `name`, `lastname`, `id_card`, `telephone`, `id_sex`, `birthdate`, `address`, `province_id`, `diagnosis`) VALUES (NULL, '888', '444', '444', '44', '4', '2015-05-17', '444', '4', '44444444444444444');
*/

    // $tb="`tb_patientcleft`";
     /*
     $strquery=" INSERT INTO   $tb   (`id_patient`, `name`, `lastname`, `id_card`, `telephone`, `id_sex`, `birthdate`, `address`, `province_id`, `diagnosis`) 
                  VALUES 
                  (NULL, 
                  '$name', 
                  '$lastname', 
                  '$id_card', 
                  '$telephone', 
                  '$id_va_sex', 
                  '$conv_dmy', 
                  '$address', 
                  '$id_prov', 
                  '$diagnosis')
                ";
     //echo  $strquery;
     */

    $tb="`tb_test`";
    //INSERT INTO `cleft`.`tb_test` (`id_tb`, `test`) VALUES (NULL, 'ทดสอบบันทึก');
  //echo  $strquery = " INSERT INTO   $tb  (`id_tb`, `test`) VALUES (NULL, 'ทดสอบ'); ";
  echo  $strquery = " INSERT INTO   $tb  (`id_tb`, `test`) VALUES (NULL, '$name'); ";
  
   //  $strquery  = " UPDATE `cleft`.`tb_test` SET `test` = 'ทดสอบ' WHERE `tb_test`.`id_tb` =22; ";
             
     $query=mysql_query($strquery) or die("Fail connect INSERT MYSQL server!! ");
     //mysql_query("SET NAMES","tis620");
      
     
           if(  $query )
           {
               $flag['code']=1;
               //echo"success insert ";
                $flag['name']=$name;
            }
            else
            {
               $flag['code']=0;
            }

           echo  json_encode($flag);
          //mysql_close($con);


?>