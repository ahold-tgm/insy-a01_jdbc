import java.sql.*;

class DBConnection {
    private Model m;

    private Connection conn;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/flightdata";

    static final String USER = "testuser";
    static final String PASS = "testuser";

    /**
     * Connects to the Server
     * @param m
     */
    DBConnection(Model m) {
        this.m = m;
        conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
     * gets an Array with all Airports in the table and saves it in the model
     * first of destination and departure must be selected
     *
     * @param country the country of the airports
     * @return String Array with all airports
     */
    String[] getAirportList(String country){
        String[] airports = new String[0];
        try{
            /* -------------------------------------------------------- */
            String countrycode = "";
            String getcountrycode ="select code from countries where name= ?";
            PreparedStatement stmt_countrycode = conn.prepareStatement(getcountrycode);
            stmt_countrycode.setString(1,country);
            ResultSet rs_getcountrycode = stmt_countrycode.executeQuery();
            while (rs_getcountrycode.next()) {
                countrycode = rs_getcountrycode.getString("code");
                System.out.println("country code: " + countrycode);
            }
            /* -------------------------------------------------------- */

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
     * gets an Array with all Countries in the table and saves it in the model
     */
    void getCountrylist(){
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
            String[] countries = new String[size];

            int counter = 0;
            while (rs_getcountries.next()) {
                countries[counter] = rs_getcountries.getString("name");
                //System.out.println(countries[counter]);
                counter++;
            }

            m.setCountries(countries);

            rs_countrycount.close();
            rs_getcountries.close();
            stmt_countries.close();
            stmt_size.close();
        }catch(SQLException se){
            se.getStackTrace();
        }
    }

    void getFlightList(){
        //not implemented yet
    }
    /**
     * close the connection
     * @return true if the connection is closed
     */
    boolean closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
