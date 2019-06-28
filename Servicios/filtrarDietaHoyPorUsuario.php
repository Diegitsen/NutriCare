<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if(isset($_GET["idUsuario"])){
		$idProfesor=$_GET["idUsuario"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta=" SELECT * FROM `Dieta` 
                    WHERE fecha = CURRENT_DATE and idUsuario = '{$idUsuario}' ";
		$resultado=mysqli_query($conexion,$consulta);
			
		while($registro=mysqli_fetch_array($resultado)){
			$json['usuario'][]=$registro;
		//	echo $registro['id'].' - '.$registro['nombre'].' - '.$registro['profesion'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['usuario'][]=$resultar;
		echo json_encode($json);
	}
?>