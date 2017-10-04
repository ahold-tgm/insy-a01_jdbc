import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class DBConnection {

    private Connection conn;

    //private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/flightdata";
    //private static final String DB_URL = "jdbc:oracle:thin:@localhost/flightdata:1521:SQLConnection";

    private static final String USER = "testuser";
    private static final String PASS = "testuser";

    /**
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
     * gets an Array with all Countries in the table and saves it in the model
     */
    String[] getCountrylist(){
        String[] countries = new String[0];
        try{
            Statement stmt_countries;
            Statement stmt_size;
            stmt_countries = conn.createStatement();
            stmt_size = conn.createStatement();

            String getcountries = "select name from countries";
            String getsize = "select count(name) from countries";

            ResultSet rs_getcountries = stmt_countries.executeQuery(getcountries);
            ResultSet rs_countrycount = stmt_size.executeQuery(getsize);

            int size = 0;
            while (rs_countrycount.next()) {
                size = rs_countrycount.getInt("count(name)");
            }
            System.out.println("country size: " + size);
            countries = new String[size];

            int counter = 0;
            while (rs_getcountries.next()) {
                countries[counter] = rs_getcountries.getString("name");
                //System.out.println(countries[counter]);
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
     * gets an Array with all Airports in the table and saves it in the model
     * first of destination and departure must be selected
     *
     * @param country the country of the airports
     * @return String Array with all airports
     */
    String[] getAirportList(String country){
        String[] airports = new String[0];
        try{
            String countrycode = this.getNameOfColumn("select code from countries where name= ?", country);

            String getairports = "select name from airports where country= ?";
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
            System.out.println(size);
            airports = new String[size];

            int counter = 0;
            while (rs_getairports.next()) {
                airports[counter] = rs_getairports.getString("name");
                System.out.println(airports[counter]);
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
     * add Commenting
     *
     * @param departureAirport
     * @param destinationAirport
     * @return
     */
    ArrayList<ArrayList<String>> getFlightList(String departureAirport, String destinationAirport){
        ArrayList<ArrayList<String>> flights = new ArrayList<>();
        try{
            String sql = "select airportcode from airports where name= ?";
            String depaAirportcode = this.getNameOfColumn(sql, departureAirport);
            String destAirportcode = this.getNameOfColumn(sql, destinationAirport);

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
     * add Commenting
     *
     * @param query
     * @param inWhere
     * @return
     */
    private String getNameOfColumn(String query, String inWhere){
        String finalcode = "";
        try{
            PreparedStatement stmt_finalcode = conn.prepareStatement(query);
            stmt_finalcode.setString(1,inWhere);
            ResultSet rs_getfinalcode = stmt_finalcode.executeQuery();
            while (rs_getfinalcode.next()) {
                finalcode = rs_getfinalcode.getString("code");
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
     * close the connection
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
