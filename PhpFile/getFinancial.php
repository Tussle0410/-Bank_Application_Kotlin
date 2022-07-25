<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$sql = "select * from production";
$stmt = $con->prepare($sql);
$stmt->execute();
$data=array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array('productionName'=>$row['productionName'],
		'interestRate'=>$row['interestRate'],
		'description'=>$row['description'],
		'interestCycle'=>$row['interestCycle']));
}
echo json_encode($data);
?>
