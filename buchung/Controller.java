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

            this.m.setSelectedDepartureCountry((String)this.v.getDeparture_country().getSelectedItem());
            this.m.setSelectedDestinationCountry((String) this.v.getDestination_country().getSelectedItem());
            System.out.println(this.m.getSelectedDepartureCountry());
            System.out.println(this.m.getSelectedDestinationCountry());
            this.m.setDepartureAirports(this.dbcon.getAirportList(this.m.getSelectedDepartureCountry()));
            this.m.setDestinationAirports(this.dbcon.getAirportList(this.m.getSelectedDestinationCountry()));
            this.v.airportPanel();
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
