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
        this.basicConfig();

        this.countryPanel();
    }

    void countryPanel(){
        this.setVisible(false);
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
     * adds basic JFrame options like Size, Title or Close Operation
     */
    private void basicConfig(){

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(800,550);
        this.setResizable(false);
        this.setLocation(d.width/2-this.getSize().width/2, d.height/2-this.getSize().height/2);
        this.setTitle("Booking");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JComboBox getDeparture_country() {
        return departure_country;
    }
    public void setDeparture_country(JComboBox departure_country) {
        this.departure_country = departure_country;
    }

    public JComboBox getDestination_country() {
        return destination_country;
    }
    public void setDestination_country(JComboBox destination_country) {
        this.destination_country = destination_country;
    }

    public JButton getSubmit_country() {
        return submit_country;
    }
    public void setSubmit_country(JButton submit_country) {
        this.submit_country = submit_country;
    }
}
