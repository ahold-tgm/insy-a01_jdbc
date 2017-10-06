import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class DBConnection {

    private Connection conn;

    //private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver"; //Tried with Oracle Thin Driver, but didnt managed
    //private static final String DB_URL = "jdbc:oracle:thin:@localhost/flightdata:1521:SQLConnection"; //to make it work
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/flightdata";

    //this user + password is created in the DB and got privileges on the flightdata db
    private static final String USER = "testuser";
    private static final String PASS = "testuser";

    /**
     * Constructor
     *
     * Connects to the Server
     */
    DBConnection() {
        conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets an String Array with all Countries
     * in the table and returns it
     *
     * @return String Array filled with all Country names
     */
    String[] getCountrylist(){
        String[] countries = new String[0];
        try{
            Statement stmt_countries;
            Statement stmt_size;
            stmt_countries = conn.createStatement();
            stmt_size = conn.createStatement();

            String getcountries = "select name from countries order by name ASC";
            String getsize = "select count(name) from countries";

            ResultSet rs_getcountries = stmt_countries.executeQuery(getcountries);
            ResultSet rs_countrycount = stmt_size.executeQuery(getsize);

            int size = 0;
            while (rs_countrycount.next()) {
                size = rs_countrycount.getInt("count(name)");
            }
            System.out.println("amount of countries entries: " + size);
            countries = new String[size];

            int counter = 0;
            while (rs_getcountries.next()) {
                countries[counter] = rs_getcountries.getString("name");
                counter++;
            }

            rs_countrycount.close();
            rs_getcountries.close();
            stmt_countries.close();
            stmt_size.close();

        }catch(SQLException se){
            se.getStackTrace();
        }
        return countries;
    }

    /**
     * gets an Array with all Airports in the table and saves
     * it in the model, but first of the destination and
     * departure country must be selected
     *
     * @param country the country of the airports
     * @return String Array which contains all airports
     */
    String[] getAirportList(String country){
        String[] airports = new String[0];
        try{
            String countrycode = this.getNameOfColumn("select code from countries where name= ?", country, "code");

            String getairports = "select name from airports where country= ? order by name ASC ";
            String getsize = "select count(name) from airports where country= ?";

            PreparedStatement stmt_airports = conn.prepareStatement(getairports);
            PreparedStatement stmt_size = conn.prepareStatement(getsize);

            stmt_airports.setString(1,countrycode);
            stmt_size.setString(1,countrycode);

            ResultSet rs_getairports = stmt_airports.executeQuery();
            ResultSet rs_airportcount = stmt_size.executeQuery();

            int size = 0;
            while (rs_airportcount.next()) {
                size = rs_airportcount.getInt("count(name)");
            }
            System.out.println("Amount of Airport entries: " + size);
            airports = new String[size];

            int counter = 0;
            while (rs_getairports.next()) {
                airports[counter] = rs_getairports.getString("name");
                counter++;
            }

            rs_airportcount.close();
            rs_getairports.close();

            stmt_airports.close();
            stmt_size.close();

        }catch(SQLException se){
            se.getStackTrace();
        }
        return airports;
    }

    /**
     * gets the airport code -> search for flights where
     * airportcode from departure and destination airport fits
     * then saves the flight data as Strings and saves these
     * Strings in a ArrayList. the flight ArrayList is saved in
     * a ArrayList.
     *
     * ArrayList< ArrayList<String> >
     * FlightList< Flights<data> >
     *
     * @param departureAirport departure airport name
     * @param destinationAirport destination airport name
     * @return ArrayList which contains another ArrayList<String>
     */
    ArrayList<ArrayList<String>> getFlightList(String departureAirport, String destinationAirport){
        ArrayList<ArrayList<String>> flights = new ArrayList<>();
        try{

            String sql = "select airportcode from airports where name= ?";
            String depaAirportcode = this.getNameOfColumn(sql, departureAirport, "airportcode");
            String destAirportcode = this.getNameOfColumn(sql, destinationAirport, "airportcode");

            String getflights = "select * from flights where departure_airport = ? and destination_airport = ?";

            PreparedStatement stmt_flights = conn.prepareStatement(getflights);

            stmt_flights.setString(1,depaAirportcode);
            stmt_flights.setString(2,destAirportcode);

            ResultSet rs_getflights = stmt_flights.executeQuery();

            String airline;
            String flightnr;
            String departureT;
            String departureA;
            String destinationT;
            String destinationA;
            String planetype;

            while (rs_getflights.next()) {
                ArrayList<String> x = new ArrayList<>();
                airline = rs_getflights.getString("airline");
                flightnr = rs_getflights.getString("flightnr");
                departureT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(rs_getflights.getTimestamp("departure_time"));
                departureA = rs_getflights.getString("departure_airport");
                destinationT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(rs_getflights.getTimestamp("destination_time"));
                destinationA = rs_getflights.getString("destination_airport");
                planetype = rs_getflights.getString("planetype");

                x.add(airline);
                x.add(flightnr);
                x.add(departureT);
                x.add(departureA);
                x.add(destinationT);
                x.add(destinationA);
                x.add(planetype);

                flights.add(x);
            }

            rs_getflights.close();

            stmt_flights.close();

        }catch(SQLException se){
            se.getStackTrace();
        }
        return flights;
    }

    /**
     * adds the passenger to the DB with a simple INSERT INTO
     *
     * @param forename forename of the Passenger
     * @param surname surname of the Passenger
     * @param row row of the Passenger
     * @param seat seatposition of the Passenger
     * @param airline airline of the Passenger
     * @param flightnumber flightnumber of the Passenger
     */
    void addPassenger(String forename, String surname, String row, String seat, String airline, String flightnumber){
        try {
            String sql = "INSERT INTO passengers(firstname, lastname, airline, flightnr, rownr, seatposition) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt_addPassenger = conn.prepareStatement(sql);

            stmt_addPassenger.setString(1,forename);
            stmt_addPassenger.setString(2,surname);
            stmt_addPassenger.setString(3,airline);
            stmt_addPassenger.setString(4,flightnumber);
            stmt_addPassenger.setString(5,row);
            stmt_addPassenger.setString(6,seat);

            stmt_addPassenger.executeUpdate();
        }catch (SQLException se){
            se.getStackTrace();
        }
    }

    /**
     * return string got from -> query where inWhere ->
     * extract getString(columnLabel)
     *
     * @param query a Query which has to be in Prepared Statement format
     * @param inWhere the variable put in the prepared statement parameter
     * @param columnLabel the name of the column where you get the return value from
     * @return String (used for getting a code; ex. country, airport, ...)
     */
    private String getNameOfColumn(String query, String inWhere, String columnLabel){
        String finalcode = "";
        try{
            PreparedStatement stmt_finalcode = conn.prepareStatement(query);
            stmt_finalcode.setString(1,inWhere);
            ResultSet rs_getfinalcode = stmt_finalcode.executeQuery();
            while (rs_getfinalcode.next()) {
                finalcode = rs_getfinalcode.getString(columnLabel);
                System.out.println(inWhere + ": " + finalcode);
            }
            stmt_finalcode.close();
            rs_getfinalcode.close();

        }catch(SQLException se){
            se.getStackTrace();
        }
        return finalcode;
    }

    /**
     * //not implemented yet
     * Should test the input of the seats and row of its validity
     *
     * @return true when its valid
     */
    boolean isSeatNumberVaild(){
        try {
            String sql = "SELECT f.airline,f.flightnr,p.maxseats,p.seatsperrow " +
                    "FROM flights f INNER JOIN planes p ON f.planetype = p.id " +
                    "where airlnine = ? and flightnr = ?";
            PreparedStatement stmt_test = conn.prepareStatement(sql);
        }catch (SQLException se){
            se.getStackTrace();
        }
        return true;
    }

    /**
     * closes the connection of the Database
     *
     * @return true if the connection is closed
     */
    boolean closeConnection(){
        try {
            this.conn.close();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
