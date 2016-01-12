<?php

$emailto='basic.soft@hotmail.com'; //อีเมล์ผู้รับ

$subject='$header'; //หัวข้อ
$header.= "Content-type: text/html; charset=tis-620\n";
$header.="from: ".$name."E-mail :".$mail; //ชื่อและอีเมลผู้ส่ง
$messages.= "$text</br>"; //ข้อความ
$messages.= "จาก $sender<br>"; //ข้อความ

$send_mail = mail($emailto,$subject,$messages,$header);

if(!$send_mail)
{
echo"true";
}
else
{
echo "fail";
}

?>

