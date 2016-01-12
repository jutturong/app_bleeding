<?php

/*
       $host="localhost";
       $us="root";
       $ps="1234";
       $db="mydatabase";
       $connect=mysql_connect($hos,$us,$ps) or die("Can't connect MYSQL SERVER!!");
       $objDB = mysql_select_db($db);
       */

       Class  Connection
       {
             protected $link;
             private $server,$us,$ps,$db;
             public function __construct($server,$us,$ps,$db)
             {
             	$this->server=$server;
             	$this->us=$us;
             	$this->ps=$ps;
             	$this->db=$db;
             	$this->connect();
             } 
             private function connect()
             {
             	$this->link=mysql_connect($this->server,$this->us,$this->ps) or die("Can't connect MYSQL SERVER!!");
             	mysql_select_db($this->db,$this->link);
                 // mysql_query("SET NAMES","tis620");
                  mysql_query("SET NAMES","utf-8");
             }

             public function __sleep()
             {
             	return array('server','us','ps','db');
             }

             public function __wakeup()
             {
             	$this->connect();
             }

       }//end class

       $host="localhost";
       $us="root";
       $ps="";
       //$db="mydatabase";
       $db="cleft";
       $connect = new Connection($host,$us,$ps,$db);
      

?>