<?php
		require_once("lib/nusoap.php");
		 
		//Create a new soap server
		$server = new soap_server();
		 
		//Define our namespace
		$namespace = "http://10.87.196.113/json2/WebServiceServer.php";
		$server->wsdl->schemaTargetNamespace = $namespace;
		 
		//Configure our WSDL
		$server->configureWSDL("HelloWorld");
		 
		// Register our method and argument parameters
        $varname = array(
                   'strName' => "xsd:string",
				   'strEmail' => "xsd:string"
        );
		$server->register('HelloWorld',$varname, array('return' => 'xsd:string'));
		 
		function HelloWorld($strName,$strEmail)
		{
			$arr["sName"] = "Sawatdee : ".$strName;
			$arr["sEmail"] = "Sawatdee : ".$strEmail;

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
