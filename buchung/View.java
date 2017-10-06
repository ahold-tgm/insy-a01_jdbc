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

    private JButton start_again;

    private JPanel panel;
    private JButton back_flights;
    private JButton addUser_flights;

    private JTextField f_name_flights;
    private JTextField s_name_flights;
    private JTextField rownumber_flights;
    private JTextField seatpos_flights;
    private JButton insertPassenger;

    /**
     * Obviously the View with all it components
     *
     * @param controller Cntroller Class
     * @param model Model Class
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
     * 2 ComboBoxes and a submit button
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

        this.setVisible(true);
    }

    /**
     * panel with 2 ComboBoxes so you can choose the aiports.
     * with a button you are submiting the input and trigger the
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

        this.setVisible(true);
    }

    /**
     * shows all founded flights
     * and give the possibility to select
     * one of it and view the data
     */
    void flightPanel() {
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.list_flights = new JComboBox(this.m.getAllAvailableFlights_code());
        this.view_flights = new JButton("View");
        this.end_flights = new JButton("Close");

        this.panel.add(this.list_flights);
        this.panel.add(this.view_flights);
        this.panel.add(this.end_flights);
        this.add(panel);

        this.view_flights.addActionListener(this.c);
        this.end_flights.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     * Panel which shows the selected Flight
     *
     * some JLabels - shows the Flight information
     * and some Buttons to add a passenger to go back
     * to the list and to close the connection
     */
    void viewFlightPanel(){
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        JLabel id = new JLabel("Flight: " + this.m.getSelectedFlight().get(0) + "-" + this.m.getSelectedFlight().get(1));
        JLabel depT = new JLabel("departure time: " + this.m.getSelectedFlight().get(2));
        JLabel depA = new JLabel("departure airport: " + this.m.getSelectedDepartureAirport());
        JLabel desT = new JLabel("destination time: " + this.m.getSelectedFlight().get(4));
        JLabel desA = new JLabel("departure airport: " + this.m.getSelectedDestinationAirport());
        this.addUser_flights = new JButton("Add Passenger");
        this.back_flights = new JButton("Back");
        this.end_flights = new JButton("Close");

        this.panel.add(id);
        this.panel.add(depT);
        this.panel.add(depA);
        this.panel.add(desT);
        this.panel.add(desA);
        this.panel.add(this.addUser_flights);
        this.panel.add(this.back_flights);
        this.panel.add(this.end_flights);
        this.add(panel);

        this.addUser_flights.addActionListener(this.c);
        this.back_flights.addActionListener(this.c);
        this.end_flights.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     * Panel to add Entries to the DB/flightdata/passengers
     *
     * Panel which contains some Textfields
     * so the User can put it the data
     * he wants.
     */
    void addPassengerPanel(){
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        JLabel id = new JLabel("Flight: " + this.m.getSelectedFlight().get(0) + "-" + this.m.getSelectedFlight().get(1));
        JLabel f_name_label = new JLabel("forename: ");
        JLabel s_name_label = new JLabel("surname: ");
        JLabel rownumber_label = new JLabel("row: ");
        JLabel seatpos_label = new JLabel("Seat: ");

        this.f_name_flights = new JTextField("forename");
        this.s_name_flights = new JTextField("surname");
        this.rownumber_flights = new JTextField("00");
        this.seatpos_flights = new JTextField("A");
        this.insertPassenger = new JButton("add to table");

        this.panel.add(id);
        this.panel.add(f_name_label);
        this.panel.add(this.f_name_flights);
        this.panel.add(s_name_label);
        this.panel.add(this.s_name_flights);
        this.panel.add(rownumber_label);
        this.panel.add(this.rownumber_flights);
        this.panel.add(seatpos_label);
        this.panel.add(this.seatpos_flights);
        this.panel.add(this.insertPassenger);
        this.add(panel);

        this.insertPassenger.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     * Just a simple "success" Message
     */
    void showPane(){
        JOptionPane.showMessageDialog(null, "Passenger successfully added!");
    }

    /**
     * simple panel with 2 Buttons 1 for
     * trying again and 1 for ending the
     * connection to the DB
     */
    void noAvailableFlightsPanel() {
        System.out.println("no Flights");
        this.remove(this.panel);
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.start_again = new JButton("Try Again");
        this.end_flights = new JButton("Close");

        this.panel.add(this.end_flights);
        this.panel.add(this.start_again);
        this.add(panel);

        this.end_flights.addActionListener(this.c);
        this.start_again.addActionListener(this.c);

        this.setVisible(true);
    }

    /**
     * adds basic JFrame options like Size, Title or Close Operation
     */
    private void basicConfig(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(800,100);
        this.setResizable(false);
        this.setLocation(d.width/2-this.getSize().width/2, d.height/2-this.getSize().height/2);
        this.setTitle("Booking");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /*
     * all getter Methods here are used
     * to get the Action Event or a
     * selected Item in a List
     */

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

    JButton getEnd_flights() {
        return end_flights;
    }

    JButton getView_flights() {
        return view_flights;
    }

    JComboBox getList_flights() {
        return list_flights;
    }

    JButton getStart_again() {
        return start_again;
    }

    JButton getBack_flights() {
        return back_flights;
    }

    JButton getAddUser_flights() {
        return addUser_flights;
    }

    JTextField getF_name_flights() {
        return f_name_flights;
    }

    JTextField getS_name_flights() {
        return s_name_flights;
    }

    JTextField getRownumber_flights() {
        return rownumber_flights;
    }

    JTextField getSeatpos_flights() {
        return seatpos_flights;
    }

    JButton getInsertPassenger() {
        return insertPassenger;
    }
}
