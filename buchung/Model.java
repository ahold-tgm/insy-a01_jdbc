class Model {
    private String[] countries = {"", "", ""};

    private String[] departureAirports = {"", "", ""};
    private String[] destinationAirports = {"", "", ""};

    private String selectedDepartureCountry = "";
    private String selectedDestinationCountry = "";

    private String selectedDepartureAirport = "";
    private String selectedDestinationAirport = "";

    /**
     * the model is used to store certain information
     */
    Model() {

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
}