import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * http://yuml.me/edit/f24a7854
 *
 * How to continue:
 * - fix flightPanel
 * - add the ViewAflightPanel
 * -  noFlightPanel
 * - add Commenting
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
        if (e.getSource() == this.v.getSubmit_country()){

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
        if (e.getSource() == this.v.getSubmit_airport()){
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
    }

    /**
     * binds the airline and flightnr to one string
     * @return list off all flights in the form of AA-000
     */
    private String[] fill_with_flightdata(){
        ArrayList flights = this.m.getAllAvailableFlights();
        String[] fill = new String[this.m.getAllAvailableFlights().size()];
        for(int i = 0; i<0; i++){
            String one = (String) ((ArrayList) flights.get(i)).get(0);
            String two = (String) ((ArrayList) flights.get(i)).get(1);
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
