import javax.swing.*;
import java.awt.*;

class View extends JFrame {

    private Model m;
    private Controller c;

    private JComboBox departure;
    private JComboBox destination;
    private JButton submit;

    View(Controller controller, Model model) {
        this.m = model;
        this.c = controller;
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.destination = new JComboBox(this.m.getCountries());
        this.departure = new JComboBox(this.m.getCountries());
        this.submit = new JButton("Submit");

        panel.add(this.departure);
        panel.add(this.destination);
        panel.add(this.submit);
        this.add(panel);
        this.submit.addActionListener(this.c);
        this.departure.addActionListener(this.c);
        this.destination.addActionListener(this.c);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(800,550);
        this.setResizable(false);
        this.setLocation(d.width/2-this.getSize().width/2, d.height/2-this.getSize().height/2);
        this.setTitle("Booking");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JComboBox getDeparture() {
        return departure;
    }
    public void setDeparture(JComboBox departure) {
        this.departure = departure;
    }

    public JComboBox getDestination() {
        return destination;
    }
    public void setDestination(JComboBox destination) {
        this.destination = destination;
    }

    public JButton getSubmit() {
        return submit;
    }
    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
}
