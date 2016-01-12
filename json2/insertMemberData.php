<?
ob_start();
		require_once("lib/nusoap.php");
		 
		//Create a new soap server
		$server = new soap_server();
		 
		//Define our namespace
		$namespace = "http://localhost/json2/insertMemberData.php";
		$server->wsdl->schemaTargetNamespace = $namespace;
		 
		//Configure our WSDL
		$server->configureWSDL("insertMember");
		 
		// Register our method and argument parameters
        $varname = array(
                   'strUsername' => "xsd:string",
				   'strPassword' => "xsd:string",
				   'strName' => "xsd:string",
				   'strEmail' => "xsd:string",
				   'strTel' => "xsd:string"
        );
		$server->register('insertMember',$varname, array('return' => 'xsd:string'));
		 
		function insertMember($strUsername,$strPassword,$strName,$strEmail,$strTel)
		{

				$objConnect = mysql_connect("localhost","root","1234");
				$objDB = mysql_select_db("mydatabase");

				/*** Check Username Exists ***/
				$strSQL = "SELECT * FROM member WHERE Username = '".$strUsername."' ";
				$objQuery = mysql_query($strSQL);
				$objResult = mysql_fetch_array($objQuery);
				if($objResult)
				{
					$arr['StatusID'] = "0"; 
					$arr['Error'] = "Username Exists!";	

					header('Content-type: application/json');
					mysql_close($objConnect);

					return json_encode($arr);
				}

				/*** Check Email Exists ***/
				$strSQL = "SELECT * FROM member WHERE Email = '".$strEmail."' ";
				$objQuery = mysql_query($strSQL);
				$objResult = mysql_fetch_array($objQuery);
				if($objResult)
				{
					$arr['StatusID'] = "0"; 
					$arr['Error'] = "Email Exists!";	

					header('Content-type: application/json');
					mysql_close($objConnect);

					return json_encode($arr);
				}

				
				/*** Insert ***/
				$strSQL = "INSERT INTO member (Username,Password,Name,Email,Tel) 
					VALUES (
						'".$strUsername."',
						'".$strPassword."',
						'".$strName."',
						'".$strEmail."',
						'".$strTel."'
						)
					";

				$objQuery = mysql_query($strSQL);
				if(!$objQuery)
				{
					$arr['StatusID'] = "0"; 
					$arr['Error'] = "Cannot save data!";	
				}
				else
				{
					$arr['StatusID'] = "1"; 
					$arr['Error'] = "";	
				}

				/**
					$arr['StatusID'] // (0=Failed , 1=Complete)
					$arr['Error'] // Error Message
				*/
				
				mysql_close($objConnect);
				
				header('Content-type: application/json');
				return json_encode($arr);
		}
		 
		// Get our posted data if the service is being consumed
		// otherwise leave this data blank.
		$POST_DATA = isset($GLOBALS['HTTP_RAW_POST_DATA']) ? $GLOBALS['HTTP_RAW_POST_DATA'] : '';
		 
		// pass our posted data (or nothing) to the soap service
		$server->service($POST_DATA);
		exit(); 
?>
