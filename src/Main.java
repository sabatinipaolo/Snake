import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
    public Main() {

        // Setting up the window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);


        iniziaAGiocare();
    }

    private void iniziaAGiocare() {

        Partita partita;
        do {

            partita = new Partita();
            add(partita);  //aggiunge la partita al JFrame
            setVisible(true);

            partita.gioca();
        } while (utenteVuoleFareUnAltra(partita));

        dispose();
    }

    private boolean utenteVuoleFareUnAltra(Partita partita) {
        int scelta = JOptionPane.showConfirmDialog(this,
                "Vuoi giocare ancora ?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);
        remove(partita);
        return (scelta == JOptionPane.OK_OPTION);
    }

    public static void main(String[] args) {
        new Main();
    }
}
