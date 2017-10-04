import javax.swing.*;
import java.awt.*;

class View extends JFrame {

    private Model m;
    private Controller c;

    private JComboBox departure_country;
    private JComboBox destination_country;
    private JButton submit_country;

    private JComboBox departure_airport;
    private JComboBox destination_airport;
    private JButton submit_airport;

    private JButton end_flights;
    private JButton view_flights;
    private JComboBox list_flights;

    private JPanel panel;

    /**
     * Obviously the View with all it components
     *
     * @param controller
     * @param model
     */
    View(Controller controller, Model model) {
        this.m = model;
        this.c = controller;

        this.panel = new JPanel();
        this.add(this.panel);
        this.basicConfig();

        this.countryPanel();
    }

    /**
     * Panel to select the countries
     */
    void countryPanel(){
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.departure_country = new JComboBox(this.m.getCountries());
        this.destination_country = new JComboBox(this.m.getCountries());
        this.submit_country = new JButton("Submit");

        this.panel.add(this.departure_country);
        this.panel.add(this.destination_country);
        this.panel.add(this.submit_country);
        this.add(panel);

        this.submit_country.addActionListener(this.c);
        this.departure_country.addActionListener(this.c);
        this.destination_country.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     * panel with 2 ComboBoxes so you can choose the aiports
     * with a button you submit the input and trigger the
     * event to read it out
     */
    void airportPanel(){
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.departure_airport = new JComboBox(this.m.getDepartureAirports());
        this.destination_airport = new JComboBox(this.m.getDestinationAirports());
        this.submit_airport = new JButton("Submit");

        this.panel.add(this.departure_airport);
        this.panel.add(this.destination_airport);
        this.panel.add(this.submit_airport);
        this.add(panel);

        this.submit_airport.addActionListener(this.c);
        this.departure_airport.addActionListener(this.c);
        this.destination_airport.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     *
     */
    void flightPanel() {
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.list_flights = new JComboBox();
        this.view_flights = new JButton("View");
        this.end_flights = new JButton("Close");

        this.panel.add(this.view_flights);
        this.panel.add(this.end_flights);
        this.add(panel);

        this.view_flights.addActionListener(this.c);
        this.end_flights.addActionListener(this.c);

        this.setVisible(true);
    }


    void noAvailableFlightsPanel() {
        //not implemented yet
        System.out.println("no Flights");
    }

    /**
     * adds basic JFrame options like Size, Title or Close Operation
     */
    private void basicConfig(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(800,250);
        this.setResizable(false);
        this.setLocation(d.width/2-this.getSize().width/2, d.height/2-this.getSize().height/2);
        this.setTitle("Booking");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    JComboBox getDeparture_country() {
        return departure_country;
    }

    JComboBox getDestination_country() {
        return destination_country;
    }

    JButton getSubmit_country() {
        return submit_country;
    }

    JComboBox getDeparture_airport() {
        return departure_airport;
    }

    JComboBox getDestination_airport() {
        return destination_airport;
    }

    JButton getSubmit_airport() {
        return submit_airport;
    }
}
