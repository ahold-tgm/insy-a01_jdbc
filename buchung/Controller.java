import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Controller implements ActionListener {
    private Model m;
    private View v;
    private DBConnection dbcon;

    private Controller() {
        this.m = new Model();
        this.dbcon = new DBConnection(this.m);
        this.dbcon.getCountrylist();
        this.v = new View(this, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.v.getSubmit()){

            this.m.setSelectedDepartureCountry((String)this.v.getDeparture().getSelectedItem());
            this.m.setSelectedDestinationCountry((String) this.v.getDestination().getSelectedItem());
            System.out.println(this.m.getSelectedDepartureCountry());
            System.out.println(this.m.getSelectedDestinationCountry());
        }
    }

    public static void main(String[] args){
        new Controller();
    }
}
