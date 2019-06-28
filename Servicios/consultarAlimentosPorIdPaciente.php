<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if(isset($_GET["idPaciente"])){
	    
		$idPaciente=$_GET["idPaciente"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta=" select b.nombre, b.calorias, b.carbohidratos, b.grasas, b.proteinas, b.idAlimento, b.info, b.tipo
                    from Dieta a 
                    inner join Alimento b on a.idAlimento = b.idAlimento
                    where a.fecha = CURRENT_DATE and a.idPaciente = '{$idPaciente}'  ";
		
		$resultado=mysqli_query($conexion,$consulta);
			
		while($registro=mysqli_fetch_array($resultado)){
			$json['alimento'][]=$registro;
		//	echo $registro['id'].' - '.$registro['nombre'].' - '.$registro['profesion'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['alimento'][]=$resultar;
		echo json_encode($json);
	}
?>