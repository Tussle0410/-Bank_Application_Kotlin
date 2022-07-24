<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$userID = isset($_POST['userID']) ? $_POST['userID'] : '';
$sql = "select * from accountaddress where ID = BINARY('$userID') and mainAddressCheck=1";
$stmt = $con->prepare($sql);
$stmt->execute();
$data = array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array('money'=>$row['money'],
		'address'=>$row['address'],
		'remittanceLimit'=>$row['remittanceLimit'],
		'currentRemittance'=>$row['currentremittance'],
		'addressName'=>$row['addressName']
	));
}
echo json_encode($data);
?>