<?php
error_reporting(E_ALL);
ini_set('errors_display',1);
include('dbcon.php');
$sql = "select * from banner";
$stmt = $con->prepare($sql);
$stmt->execute();
$data=array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array('bannerRoute'=>$row['bannerRoute'],
		'kinds'=>$row['kinds']));
}
echo json_encode($data);
?>