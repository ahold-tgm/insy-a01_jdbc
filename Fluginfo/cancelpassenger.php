<?php
/*
cancelpassenger.php
@author: Hold Alexander
@version: 09/30/2017
*/
error_reporting(0); //Hiding errors
require_once 'script.php'; //import

//get all params
$passengerid = $_GET['pid']; 
$airline = $_GET['al'];
$flightnumber = $_GET['fnr'];

cancel_passenger($passengerid,$flightnumber); //cancel passenger

header('Location: ./flightmanage.php?al='. $airline . '&fnr=' . $flightnumber . '&del=true'); //redirect
?>