<?php
error_reporting('E_ALL');
ini_set('display_errors',1);
include('dbcon.php');
$address = isset($_POST['address']) ? $_POST['address'] : '';
$receiveAddress = isset($_POST['receiveAddress']) ? $_POST['receiveAddress'] : '';
$ID = isset($_POST['ID']) ? $_POST['ID'] : '';
$name = isset($_POST['name']) ? $_POST['name'] : '';
$receiveName = isset($_POST['receiveName'])? $_POST['receiveName'] : '';
$money = isset($_POST['money']) ? $_POST['money'] : '';
$pw = isset($_POST['pw']) ? $_POST['pw'] : '';
$pwCheckSql = "select * from user where ID='$ID' and PW='$pw'";
$pwCheckStmt = $con->prepare($pwCheckSql);
$pwCheckStmt->execute();
if($pwCheckStmt->rowCount() > 0){
	$updateSql  = "update accountaddress set money=CASE WHEN address='$address' THEN money-'$money' WHEN address='$receiveAddress' THEN money+'$money' ELSE money END,currentremittance=CASE WHEN address='$address' THEN currentremittance+'$money' ELSE currentremittance END Where address IN('$address','$receiveAddress')";
	$updateStmt = $con->prepare($updateSql);
	$updateStmt->execute();
	$insertSql = "insert into transactionhistory(sendAddress,receiveAddress,sendName,receiveName,money,transactionDate) value('$address','$receiveAddress','$name','$receiveName','$money',NOW())";
	$insertStmt = $con->prepare($insertSql);
	$insertStmt->execute();
	echo 'true';
}else{
	echo 'false';
}
?>