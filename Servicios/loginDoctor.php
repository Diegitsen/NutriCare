<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if( isset($_GET["usuario"]) && isset($_GET["password"])) {
		$usuario=$_GET["usuario"];
        $password=$_GET["password"];        
        
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta="select * from Doctor where usuario= '{$usuario}' and password= '{$password}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$json['doctor'][]=$registro;
		}else{
			$resultar["idDoctor"]=0;
			$resultar["nombre"]='no registra';
			$json['doctor'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['doctor'][]=$resultar;
		echo json_encode($json);
	}
?>