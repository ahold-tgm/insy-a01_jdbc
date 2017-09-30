import java.sql.*;

class DBConnection {
    private Model m;

    private Connection conn;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/flightdata";

    static final String USER = "testuser";
    static final String PASS = "testuser";

    DBConnection(Model m) {
        this.m = m;
        conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAirportList(){
        try{
            Statement stmt_airports = conn.createStatement();
            Statement stmt_size = conn.createStatement();

            String getairports = "select name from airport where country=\""+
                    this.m.getSelectedDepartureCountry()+ "\"";
            String getsize = "select count(name) from airports where country=\""+
                    this.m.getSelectedDepartureCountry()+ "\"";

            ResultSet rs_getairports = stmt_airports.executeQuery(getairports);
            ResultSet rs_airportcount = stmt_size.executeQuery(getsize);

            int size = 0;
            while (rs_airportcount.next()) {
                size = rs_airportcount.getInt("count(name)");
            }
            System.out.println(size);
            String[] airports = new String[size];

            int counter = 0;
            while (rs_getairports.next()) {
                airports[counter] = rs_getairports.getString("name");
                System.out.println(airports[counter]);
                counter++;
            }

            m.setAirports(airports);

            rs_airportcount.close();
            rs_getairports.close();
            stmt_airports.close();
            stmt_size.close();
        }catch(SQLException se){
            se.getStackTrace();
        }
    }

    public void getCountrylist(){
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
            System.out.println(size);
            String[] countries = new String[size];

            int counter = 0;
            while (rs_getcountries.next()) {
                countries[counter] = rs_getcountries.getString("name");
                System.out.println(countries[counter]);
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

    public boolean closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
