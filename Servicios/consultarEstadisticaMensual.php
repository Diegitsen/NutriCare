<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
	if(isset($_GET["fecha"])  &&  isset($_GET["idPaciente"])  ){
	    
		$idPaciente=$_GET["idPaciente"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta=" SELECT SUM(b.carbohidratos) as carbohidratos , SUM(b.calorias) as calorias, SUM(b.grasas) as grasas, 
		            SUM(b.proteinas) as proteinas
                    FROM Dieta a 
                    INNER JOIN Alimento b ON a.idAlimento = b.idAlimento
                    WHERE a.comio = 1 and MONTH(fecha) = MONTH(CURRENT_DATE) and idPaciente =  '{$idPaciente}' ";
                    
                 
		
		$resultado=mysqli_query($conexion,$consulta);
			
		while($registro=mysqli_fetch_array($resultado)){
			$json['dieta'][]=$registro;
		//	echo $registro['id'].' - '.$registro['nombre'].' - '.$registro['profesion'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['dieta'][]=$resultar;
		echo json_encode($json);
	}
?>