import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * http://yuml.me/edit/f24a7854
 */
class Controller implements ActionListener {
    private Model m;
    private View v;
    private DBConnection dbcon;

    /**
     * Controller
     */
    private Controller() {
        this.m = new Model(); // just used as a Storage
        this.dbcon = new DBConnection(); //needs the storage to save things in it
        this.m.setCountries(this.dbcon.getCountrylist());
        this.v = new View(this, m); //needs the storage and the controller
    }

    /**
     * opens the next panel + does all the action needed (ex. new query > new list)
     *
     * @param e ActionEvent ex. Button pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.v.getSubmit_country()) {
            goToAirport();
        }
        if (e.getSource() == this.v.getSubmit_airport() || e.getSource() == this.v.getBack_flights()) {
            goToFlightList();
        }
        if(e.getSource() == this.v.getEnd_flights()){
            this.dbcon.closeConnection();
        }
        if(e.getSource() == this.v.getStart_again()){
            this.v.countryPanel();
        }
        if(e.getSource() == this.v.getView_flights()){
            goToFlight();
        }
        if(e.getSource() == this.v.getAddUser_flights()){
            this.v.addPassengerPanel();
        }
        if(e.getSource() == this.v.getInsertPassenger()){
            this.addFlightPassenger();
            goToFlight();
        }
    }

    /**
     * sets the selected departure and destination Country
     * gets the airport Lists of the 2 countries
     * and opens the airport panel
     */
    private void goToAirport(){
        String selectedDepartureCountry = (String) this.v.getDeparture_country().getSelectedItem();
        this.m.setSelectedDepartureCountry(selectedDepartureCountry);

        String selectedDestinationCountry = (String) this.v.getDestination_country().getSelectedItem();
        this.m.setSelectedDestinationCountry(selectedDestinationCountry);

        System.out.println("selected departure country: " + this.m.getSelectedDepartureCountry());
        System.out.println("selected destination country: " + this.m.getSelectedDestinationCountry());

        this.m.setDepartureAirports(this.dbcon.getAirportList(this.m.getSelectedDepartureCountry()));
        this.m.setDestinationAirports(this.dbcon.getAirportList(this.m.getSelectedDestinationCountry()));

        this.v.airportPanel();
    }

    /**
     * sets the selected departure and destination airport
     * also saves all available flights. then opens the
     * next panel - no Flights Available or flight Panel
     *
     */
    private void goToFlightList() {
        String selectedDepartureAirport = (String) this.v.getDeparture_airport().getSelectedItem();
        this.m.setSelectedDepartureAirport(selectedDepartureAirport);

        String selectedDestinationAirport = (String) this.v.getDestination_airport().getSelectedItem();
        this.m.setSelectedDestinationAirport(selectedDestinationAirport);

        System.out.println("selected departure airport: " + this.m.getSelectedDepartureAirport());
        System.out.println("selected destination airport: " + this.m.getSelectedDestinationAirport());

        this.m.setAllAvailableFlights(this.dbcon.getFlightList(this.m.getSelectedDepartureAirport(), this.m.getSelectedDestinationAirport()));

        if(this.m.getAllAvailableFlights().isEmpty()){
            this.v.noAvailableFlightsPanel();
        }else{
            this.m.setAllAvailableFlights_code(this.fill_with_flightdata());
            this.v.flightPanel();
        }
    }

    /**
     * gets the selected ComboBox ArrayList and
     * saves it in the selectedFlight var. After
     * that the Flight data panel will be opened
     */
    private void goToFlight(){
        System.out.println("Index of selected Item: " + this.v.getList_flights().getSelectedIndex());
        //gets index of selected ComboBox -> and takes that ArrayList<String> from the ArrayList -> saves it
        this.m.setSelectedFlight((ArrayList) this.m.getAllAvailableFlights().get(this.v.getList_flights().getSelectedIndex()));
        this.v.viewFlightPanel();
    }

    /**
     * gets all texts from the TextFields
     * and puts it as parameter in the addPassenger class
     * after that it shows the success Pane
     * and then the PassengerPanel again
     */
    private void addFlightPassenger(){
        String fname = this.v.getF_name_flights().getText();
        System.out.println("forname: " + fname);
        String sname = this.v.getS_name_flights().getText();
        System.out.println("surname: " + sname);
        String rownum= this.v.getRownumber_flights().getText();
        System.out.println("rownumber: " + rownum);
        String seatpos = this.v.getSeatpos_flights().getText();
        System.out.println("seat position: " + seatpos);
        this.dbcon.addPassenger(fname, sname, rownum, seatpos, (String) this.m.getSelectedFlight().get(0), (String) this.m.getSelectedFlight().get(1));
        this.v.showPane();
        this.v.addPassengerPanel();
    }

    /**
     * binds the airline and flightnr to one string
     * this has no real functional use its just to make
     * list at the flightlistPanel a bit beautifuler
     *
     * @return list off all flights in the form of AA-000
     */
    private String[] fill_with_flightdata(){
        ArrayList flights = this.m.getAllAvailableFlights();
        String[] fill = new String[this.m.getAllAvailableFlights().size()];
        for(int i = 0; i< this.m.getAllAvailableFlights().size(); i++){
            System.out.println(flights.get(i));
            String one = (String) ((ArrayList) flights.get(i)).get(0);
            String two = (String) ((ArrayList) flights.get(i)).get(1);
            System.out.println(one + "-" + two);
            fill[i] = one + "-" + two;
        }
        return fill;
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args){
        new Controller();
    }
}
