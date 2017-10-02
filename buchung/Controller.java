import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.dbcon = new DBConnection(this.m); //needs the storage to save things in it
        this.dbcon.getCountrylist();
        this.v = new View(this, m); //needs the storage and the controller
    }

    /**
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
            System.out.println("selected departure country: " + this.m.getSelectedDestinationCountry());

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
            System.out.println("selected departure airport: " + this.m.getSelectedDestinationAirport());

            this.dbcon.getFlightList();

            this.v.flightPanel();
        }
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args){
        new Controller();
    }
}
