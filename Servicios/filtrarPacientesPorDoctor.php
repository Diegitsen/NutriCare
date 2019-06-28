<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if(isset($_GET["idDoctor"])){
		$idDoctor=$_GET["idDoctor"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		

		$consulta=" SELECT a.idPaciente, b.nombre, b.apellido, b.peso, b.edad 
                    FROM Paciente_Doctor a 
                    INNER JOIN Paciente b on a.idPaciente = b.idPaciente
                    WHERE a.idDoctor = '{$idDoctor}' ";
		
		
		$resultado=mysqli_query($conexion,$consulta);
			
		while($registro=mysqli_fetch_array($resultado)){
			$json['paciente'][]=$registro;
		//	echo $registro['id'].' - '.$registro['nombre'].' - '.$registro['profesion'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['paciente'][]=$resultar;
		echo json_encode($json);
	}
?>