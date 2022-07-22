<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$ID = isset($_POST['ID']) ? $_POST['ID'] : '';
$sql = "select money from accountaddress where ID='$ID' and mainAddressCheck=1";
$stmt = $con->prepare($sql);
$stmt->execute();
$data=array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array(
			'money'=>$row['money']
		));
	}
echo json_encode($data);;
?>