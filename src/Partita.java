import javax.swing.*;

public class Partita extends JPanel {
    // la partita è un JPanel per ora ospita il campo di gioco e il serpernte
    //TODO: aggiungere un pannello che conti i punti ...
    private CampoDiGioco campo;
    private Serpente serpente;


    public Partita() {

        campo = new CampoDiGioco();

        serpente = new Serpente(campo);
        addKeyListener(serpente);

        campo.generaCibo();

        add(campo); //aggiunge il JPanel campo al Jpanel Partita

    }

    public void gioca() {
        requestFocusInWindow();

        serpente.start();

        try {
            serpente.join(); //aspetta che il serpente (thread) muoia (finisca)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

