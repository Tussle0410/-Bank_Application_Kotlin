<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$name = isset($_POST['name']) ? $_POST['name'] : 'aa';
$birth = isset($_POST['birth']) ? $_POST['birth'] : '20150517';
$sql = "select ID from user where Name='$name' and Birth='$birth'";
$stmt = $con->prepare($sql);
$stmt->execute();
if($stmt->rowCount()>0){
	$data = array();
	while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
		extract($row);
		array_push($data,
			array('ID'=>$row["ID"]));}
	echo json_encode($data);
}else{
	$data = array();
	array_push($data,
		array('ID' => ""));
	echo json_encode($data);
}
?>

