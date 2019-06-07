<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if( isset($_GET["username"]) && isset($_GET["pass"])) {
		$username=$_GET["username"];
        $pass=$_GET["pass"];        
        
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta="select username,pass from Usuario where username= '{$username}' and pass= '{$pass}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$json['Usuario'][]=$registro;
		}else{
			$resultar["idUsuario"]=0;
			$resultar["nombre"]='no registra';
			$json['Usuario'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['Usuario'][]=$resultar;
		echo json_encode($json);
	}
?>