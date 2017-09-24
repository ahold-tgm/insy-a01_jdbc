<?php
require_once 'script.php';

$passengerid = $_GET['pid'];
$airline = $_GET['al'];
$flightnumber = $_GET['fnr'];

cancel_passenger($passengerid,$flightnumber);

header('Location: ./flightmanage.php?al='. $airline . '&fnr=' . $flightnumber . '&del=true');
?>