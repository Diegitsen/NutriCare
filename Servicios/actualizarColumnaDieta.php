
<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
    if(  isset($_GET["fecha"]) && isset($_GET["idPaciente"])  && isset($_GET["idAlimento"]) ){
		$fecha=$_GET['fecha'];
		$idPaciente=$_GET['idPaciente'];
		$idAlimento=$_GET['idAlimento'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
        

        $insert=" UPDATE Dieta
                  SET comio = 1
                  where fecha = '{$fecha}' and idAlimento = '{$idAlimento}' and idPaciente = '{$idPaciente}' ";

        
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			
			$consulta="SELECT * FROM Dieta WHERE idPaciente = '{$idPaciente}' ";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['dieta'][]=$registro;
			}
			
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["idDieta"]=0;
			$json['dieta'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["idDieta"]=0;
			$json['dieta'][]=$resulta;
			echo json_encode($json);
		}
?>




