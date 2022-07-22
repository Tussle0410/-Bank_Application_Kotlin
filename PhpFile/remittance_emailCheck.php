<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$email = isset($_POST['email']) ? $_POST['email'] : 'aaa@naver.com';
$name = isset($_POST['name']) ? $_POST['name'] : 'aaa';
$sql = "select * from accountaddress natural join user where email='$email' and name='$name' and mainAddressCheck=1";
$stmt = $con->prepare($sql);
$stmt->execute();
$data = array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
	array(
		'addressName'=>$row['addressName'],
		'address'=>$row['address']));
}
echo json_encode($data)
?>