class Model {
    private String[] countries = {"", "", ""};
    private String[] departureAirports = {"", "", ""};
    private String[] destinationAirports = {"", "", ""};
    private String selectedDepartureCountry = "";
    private String selectedDestinationCountry = "";

    /**
     * the model is used to store certain information
     */
    Model() {

    }

    public String[] getCountries() {
        return countries;
    }
    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String[] getDepartureAirports() {
        return departureAirports;
    }
    public void setDepartureAirports(String[] departureAirports) {
        this.departureAirports = departureAirports;
    }

    public String[] getDestinationAirports() {
        return destinationAirports;
    }
    public void setDestinationAirports(String[] destinationAirports) {
        this.destinationAirports = destinationAirports;
    }

    public String getSelectedDepartureCountry() {
        return selectedDepartureCountry;
    }
    public void setSelectedDepartureCountry(String selectedDepartureCountry) {
        this.selectedDepartureCountry = selectedDepartureCountry;
    }

    public String getSelectedDestinationCountry() {
        return selectedDestinationCountry;
    }
    public void setSelectedDestinationCountry(String selectedDestinationCountry) {
        this.selectedDestinationCountry = selectedDestinationCountry;
    }
}