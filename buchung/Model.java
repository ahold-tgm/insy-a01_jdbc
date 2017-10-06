import java.util.ArrayList;

class Model {
    private String[] countries;

    private String[] departureAirports;
    private String[] destinationAirports;

    private String selectedDepartureCountry;
    private String selectedDestinationCountry;

    private String selectedDepartureAirport;
    private String selectedDestinationAirport;

    private ArrayList allAvailableFlights;
    private String[] allAvailableFlights_code;

    private ArrayList selectedFlight;
    /**
     * the model - used to store all needed information
     */
    Model() {
        this.countries = new String[]{"", "", ""};

        this.departureAirports = new String[]{"", "", ""};
        this.destinationAirports = new String[]{"", "", ""};

        this.selectedDepartureCountry = "";
        this.selectedDestinationCountry = "";

        this.selectedDepartureAirport = "";
        this.selectedDestinationAirport = "";

        this.allAvailableFlights = new ArrayList<ArrayList>();
        this.allAvailableFlights_code= new String[]{"", "", ""};

        this.selectedFlight = new ArrayList<String>();
    }

    String[] getCountries() {
        return countries;
    }
    void setCountries(String[] countries) {
        this.countries = countries;
    }

    String[] getDepartureAirports() {
        return departureAirports;
    }
    void setDepartureAirports(String[] departureAirports) {
        this.departureAirports = departureAirports;
    }

    String[] getDestinationAirports() {
        return destinationAirports;
    }
    void setDestinationAirports(String[] destinationAirports) {
        this.destinationAirports = destinationAirports;
    }

    String getSelectedDepartureCountry() {
        return selectedDepartureCountry;
    }
    void setSelectedDepartureCountry(String selectedDepartureCountry) {
        this.selectedDepartureCountry = selectedDepartureCountry;
    }

    String getSelectedDestinationCountry() {
        return selectedDestinationCountry;
    }
    void setSelectedDestinationCountry(String selectedDestinationCountry) {
        this.selectedDestinationCountry = selectedDestinationCountry;
    }

    String getSelectedDepartureAirport() {
        return selectedDepartureAirport;
    }
    void setSelectedDepartureAirport(String selectedDepartureAirport) {
        this.selectedDepartureAirport = selectedDepartureAirport;
    }

    String getSelectedDestinationAirport() {
        return selectedDestinationAirport;
    }
    void setSelectedDestinationAirport(String selectedDestinationAirport) {
        this.selectedDestinationAirport = selectedDestinationAirport;
    }

    /**
     * to access a Flight do Flightlist.get(index of Flight)
     * in non pseudo code:
     *          ((ArrayList) allAvailableFlights.get(i)).get(j)
     * Cast needed
     * i ... index of the flight
     * j ... index of the data
     *      0 ... airline
     *      1 ... flightnr
     *      2 ... dep time
     *      3 ... dep airport
     *      4 ... dest time
     *      5 ... dest airport
     *      6 ... planetype
     *
     * @return ArrayList<ArrayList<String>> by default, when extracted
     */
    ArrayList getAllAvailableFlights() {
        return allAvailableFlights;
    }
    void setAllAvailableFlights(ArrayList allAvailableFlights) {
        this.allAvailableFlights = allAvailableFlights;
    }

    String[] getAllAvailableFlights_code() {
        return allAvailableFlights_code;
    }
    void setAllAvailableFlights_code(String[] allAvailableFlights_code) {
        this.allAvailableFlights_code = allAvailableFlights_code;
    }

    /**
     * ArrayList extracted from the FlightList
     *
     * @return ArrayList<String> ArrayList with data stored as Strings
     */
    ArrayList getSelectedFlight() {
        return selectedFlight;
    }
    void setSelectedFlight(ArrayList selectedFlight) {
        this.selectedFlight = selectedFlight;
    }
}