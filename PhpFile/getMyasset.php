<?php
error_reporting(E_ALL);
ini_set('errors_display',1);
include('dbcon.php');
$ID=isset($_POST['ID']) ? $_POST['ID'] : 'qqq';
$sql = "select sum(money) as money, kinds from accountaddress natural join production where ID='$ID' group by kinds";
$stmt=$con->prepare($sql);
$stmt->execute();
$data=array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array('money'=>$row['money'],
		'kinds'=>$row['kinds']));
}
echo json_encode($data);
?>

