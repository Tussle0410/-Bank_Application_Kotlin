<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$kinds = isset($_POST['kinds']) ? $_POST['kinds'] : '';
$sql = "select * from production where kinds = '$kinds'";
$stmt = $con->prepare($sql);
$stmt->execute();
if($stmt->rowCount()>0){
	$data=array();
	while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
		extract($row);
		array_push($data,
			array('productionName'=>$row['productionName'],
			'interestRate'=>$row['interestRate'],
			'description'=>$row['description'],
			'interestCycle'=>$row['interestCycle']));
	}
	header("Content-Type:application/json; charset=utf8");
	$json = json_encode(array("financial"=>$data),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
	echo $json;
}else{
	echo "fail";
}
?>