class Model {
    private String[] countries = {"", "", ""};
    private String[] airports = {"", "", ""};
    private String selectedDepartureCountry = "";
    private String selectedDestinationCountry = "";

    Model() {

    }

    public String[] getCountries() {
        return countries;
    }
    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String[] getAirports() {
        return airports;
    }
    public void setAirports(String[] airports) {
        this.airports = airports;
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