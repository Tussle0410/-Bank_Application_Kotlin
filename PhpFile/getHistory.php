<?PHP
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
$address = isset($_POST['address']) ? $_POST['address'] : '';
$sql="select * from transactionHistory where (sendAddress = '$address' OR receiveAddress = '$address') order by transactionDate DESC";
$stmt = $con->prepare($sql);
$stmt->execute();
$data = array();
while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
	extract($row);
	array_push($data,
		array('sendAddress'=>$row['sendAddress'],
		'receiveAddress'=>$row['receiveAddress'],
		'sendName'=>$row['sendName'],
		'receiveName'=>$row['receiveName'],
		'money'=>$row['money'],
		'transactionDate'=>$row['transactionDate']));
}
echo json_encode($data);		
?>


