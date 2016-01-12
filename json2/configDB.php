  <?php      
            $host="localhost";
            $us="root";
            $ps="";
            $db="cleft";
            $connect=mysql_connect($host,$us,$ps) or die("can't connect MYSQL SERVER!!");
            $select_db=mysql_select_db($db) or die("can't  select MYSQL SERVER!!!");
            mysql_query("SET NAMES UTF8");

            /*
            mysql_query("SET character_set_results=utf8");
            mysql_query("SET character_set_client=utf8");
            mysql_query("SET character_set_connection=utf8")
            */

            

   ?>         