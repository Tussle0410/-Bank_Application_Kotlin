<?php
error_reporting('E_ALL');
ini_set('display_errors',1);
include('dbcon.php');
$address = isset($_POST['address']) ? $_POST['address'] : '';
$sql = "select * from accountaddress natural join user where address = '$address'";
$stmt = $con->prepare($sql);
$stmt ->execute();
$data = array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
	array(
		'Name'=>$row['Name'],
		'addressName'=>$row['addressName']));
}
echo json_encode($data)

?>

