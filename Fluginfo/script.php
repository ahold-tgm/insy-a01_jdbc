<?php
/*
script.php
@author: Hold Alexander
@version: 09/23/2017
*/
/*
Config
*/
$dbhost = "localhost";
$dbname = "jdbc";
$dbuser = "testuser";
$dbpwd = "testuser";

/*
Connection is established(with exception)
This will later be used as a global handler for db-connectivity
*/
try {
  $handler = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpwd);
} catch(PDOException $e) {
  echo $e->getMessage();
}

/*
Returns flight information(departure_time,departure_airport,destination_time,destination_airport,planetype) of a specific flight(defined by $airline,$flightnr)
*/
function get_flightinfo($airline,$flightnr) {
	global $handler; //get global handler variable
	/*
	Creating prepared statment to get flight info
	*/
	$stmt = $handler->prepare("SELECT departure_time,departure_airport,destination_time,destination_airport,planetype FROM flights WHERE airline = :airline AND flightnr = :flightnr"); //:airline/:flightnr = named parameter(placeholder) 

	$params = array("airline" => $airline, "flightnr" => $flightnr); //defining binding parameters

	$stmt->execute($params); //execute statment

	/*
	Creating $flightinfo array to return
	*/
	$flightinfo = $stmt->fetchAll();

 	return $flightinfo; 
}

/*
Returns a 2 Dimensional Array of every passengers information(id,firstname,lastname,rownr,seatposition) who is booked on a specific flight(defined by $airline,$flightnr)
*/
function get_passengers($airline,$flightnr) {
	global $handler; //get global handler variable
	/*
	Creating prepared statment to get all passengers
	*/
	$stmt = $handler->prepare("SELECT id,firstname,lastname,rownr,seatposition FROM passengers WHERE airline = :airline AND flightnr = :flightnr"); //:flightnr/:airline = named parameter(placeholder) 

	$params = array("airline" => $airline, "flightnr" => $flightnr); //defining binding parameters

	$stmt->execute($params); //execute statment

	/*
	Creating $passengers array to return
	*/
	while ($row = $stmt->fetch()) {
	  $passengers[] = $row;
	}

 	return $passengers; 
}

/*
Returns plane information(manufacturer,type,lengthoverall,span,maxspeed,initialserviceyear,maxseats,seatsperrow) of a specific plane(defined by planeid)
*/
function get_planinfo($planeid) {
	global $handler; //get global handler variable
	/*
	Creating prepared statment to get plane info
	*/
	$stmt = $handler->prepare("SELECT manufacturer,type,lengthoverall,span,maxspeed,initialserviceyear,maxseats,seatsperrow FROM planes WHERE id = :planeid"); //:planeid = named parameter(placeholder) 

	$params = array("planeid" => $planeid); //defining binding parameters

	$stmt->execute($params); //execute statment

	/*
	Creating $planinfo array to return
	*/
	$planeinfo = $stmt->fetchAll();

 	return $planeinfo; 
}

/*
Returns a 2 Dimensional Array of all countries(code,name)
*/
function get_countries() {
	global $handler; //get global handler variable

	$stmt   = $handler -> query("SELECT code,name FROM countries"); //creating query to get country info

	/*
	Creating $countries array to return
	*/
	while ($row = $stmt->fetch()) {
	  $countries[] = $row;
	}

 	return $countries; 
}

/*
Deletes a passenger on a specific flight(defined by $airline,$flightnr)
*/
function cancel_passenger($passengerid, $flightnr) {
	global $handler; //get global handler variable

	/*
	Creating prepared statment to delete passenger
	*/
	$stmt = $handler->prepare("DELETE FROM passengers WHERE id = :passengerid AND flightnr = :flightnr");

	$params = array("passengerid" => $passengerid, "flightnr" => $flightnr); //defining binding parameters

	$stmt->execute($params); //execute statment
}

/*
tests
*/
$arr_p = get_passengers("ED", 170);
print $arr_p[1][2];
$arr_c = get_countries();
print $arr_c[0][1];
$arr_f = get_flightinfo("ED", 170);
print $arr_f[0][0];
$arr_pl = get_planinfo(46);
print $arr_pl[0][1]
//cancel_passenger(19,480);
?>