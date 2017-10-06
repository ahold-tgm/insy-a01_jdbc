<?php
/*
flightmanage.php
@author: Hold Alexander
@version: 09/30/2017
*/
error_reporting(0); //Hiding errors
require_once 'script.php'; //import

//declare variables 
$airline = [];
$flightnr = [];
$flightinfo = [];
$passengerlist = [];
$planeinfo = [];

//get all params
$airline = $_GET['al'];
$flightnr = $_GET['fnr'];

$flightinfo = get_flightinfo($airline,$flightnr); //departure_time,departure_airport,destination_time,destination_airport,planetype
$passengerlist = get_passengers($airline,$flightnr); //id,firstname,lastname,rownr,seatposition

$planeinfo = get_planeinfo($flightinfo[0][4]); //manufacturer,type,lengthoverall,span,maxspeed,initialserviceyear,maxseats,seatsperrow

?>

<?php if(empty($flightinfo) == false || empty($passengerlist) == false || empty($planeinfo) == false): ?>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css"> </head>

<body>
  <?php
    //print a message if there was a user deleted
    if(isset($_GET['del'])){
      echo '<div class="alert alert-success alert-dismissible fade show" role="alert">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
              </button>
              <strong>Kick Successful!</strong> Thank you for using FlightManager :-)
            </div>';
    }
  ?>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="display-1 text-center">Flight Manager</h1>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <p class="lead">Flight Information:
            <br><?php echo $airline . "-" . $flightnr;?></p>
          <p class="">Departure: <b><?php echo $flightinfo[0][0] . "-" . $flightinfo[0][1];?></b>
            <br>Landing: <b><?php echo $flightinfo[0][2] . "-" . $flightinfo[0][3];?></b>
            <br>Booked Passengers: <b><?php echo count($passengerlist);?></b>&nbsp;</p>
        </div>
        <div class="col-md-4">
          <p class="lead">Plane Information:
            <br>(<?php echo $planeinfo[0][0] . " " . $planeinfo[0][1];?>)</p>
          <p class="">Initial Service: <b><?php echo $planeinfo[0][5];?></b>
            <br>Max. Passengers: <b><?php echo $planeinfo[0][6]?></b>&nbsp;
            <br>Seatsperrow: <b><?php echo $planeinfo[0][7]?></b>&nbsp;</p>
        </div>
        <div class="col-md-4"></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="">Passengers</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table">
            <thead>
              <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Seat</th>
                <th>kick of flight</th>
              </tr>
            </thead>
            <tbody>
              <?php
                for ($i=0; $i < count($passengerlist); $i++) { 
                    echo "<tr>
                              <td>" . $passengerlist[$i][1] . "</td>
                              <td>" . $passengerlist[$i][2] . "</td>
                              <td>" . $passengerlist[$i][4] . $passengerlist[$i][3] . "</td>
                              <td><a href=\"cancelpassenger.php?pid=" . $passengerlist[$i][0] . "&al=" . $airline . "&fnr=" . $flightnr . "\"><button type=\"button\" class=\"btn btn-danger\">Kick!</button></a>
</td>
                          </tr>";
                }
              ?>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

  <?php else: header('Location: ./index.php?err=1'); //redirecting to index.php?>
  Error
  <?php endif; ?>
</body>

</html>