
<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id9729488_nutricaredb";
$username_localhost ="id9729488_fundadesw";
$password_localhost ="fundamentodesoftware19";
$json=array();
    if(isset($_GET["nombre"]) && isset($_GET["info"]) && isset($_GET["tipo"]) ){
		$nombre=$_GET['nombre'];
        $info=$_GET['info'];
        $tipo=$_GET['tipo'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO Alimento( nombre, tipo, info) 
        VALUES ('{$nombre}','{$tipo}' ,'{$info}' )";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM Alimento WHERE nombre = '{$nombre}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['Alimento'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["idAlimento"]=0;
			$resulta["nombre"]='No Registra';
            $resulta["info"]='No Registra';
			$json['Alimento'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["idAlimento"]=0;
			$resulta["nombre"]='WS No retorna';
            $resulta["info"]='WS No retorna';
			$json['Alimento'][]=$resulta;
			echo json_encode($json);
		}
?>