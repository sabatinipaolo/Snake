import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Serpente extends Thread implements KeyListener {
    //TODO:? creare una classe esterna AscoltaComandiPerSerpente
    // per evitare di usare le interfacce ?
    //
    //perché un thread? un serpente è una cosa viva!!
    //un domani ne mettiamo più di uno e abbiamo delle race condition (la mela !) ?

    private boolean vivo;
    private LinkedList<Punto> corpo; //TODO : provare una lista di Quadratini invece di una lista di coordinate dei quadratini
    private Punto direzione;
    private CampoDiGioco campo;

    //TODO: trasformare in enum ?
    private static final Punto NORD = new Punto(-1, 0);
    private static final Punto SUD = new Punto(1, 0);
    private static final Punto EST = new Punto(0, 1);
    private static final Punto OVEST = new Punto(0, -1);

    public Serpente(CampoDiGioco campo) {
        //Un serpente deve vivere in un campo ...//TODO: coupling ?

        this.campo = campo;

        vivo = true;
        direzione = NORD;

        corpo = new LinkedList<Punto>();

        inizializzaIlCorpoDelSerpente();


    }

    private void inizializzaIlCorpoDelSerpente() {
        Punto punto;

        // inizializza un serpente lungo 3 quadratini
        punto = new Punto(19, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();  // viola la legge di Demetra?

        punto = new Punto(18, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();

        punto = new Punto(17, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();
    }

    public void avanza() {
        Punto posTesta = corpo.getFirst();

        Punto nuovaPos = Punto.somma(posTesta, direzione);


        switch (campo.getQuadratino(nuovaPos).getTipo()) {
            case Quadratino.CIBO -> {
                System.out.print("GnamGnam ");
                corpo.addFirst(nuovaPos);
                campo.getQuadratino(nuovaPos).setToSerpente();
                campo.generaCibo();
            }
            case Quadratino.PRATO -> {
                corpo.addFirst(nuovaPos);
                campo.getQuadratino(nuovaPos).setToSerpente();
                campo.getQuadratino(corpo.getLast()).setToPrato();
                corpo.removeLast();
            }
            case Quadratino.SERPENTE -> {
                System.out.print("MORTOOOOOOOOOO ");
                vivo = false;
            }
            default -> {
            } //non può accadere!!

        }

        campo.repaint();
    }

    public void run() {
        while (vivo) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            avanza();
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {

        int keycode = e.getKeyCode();

        if ((keycode == KeyEvent.VK_DOWN) && (direzione != NORD))
            direzione = SUD;
        else if ((keycode == KeyEvent.VK_UP) && (direzione != SUD))
            direzione = NORD;
        else if ((keycode == KeyEvent.VK_LEFT) && (direzione != EST))
            direzione = OVEST;
        else if ((keycode == KeyEvent.VK_RIGHT) && (direzione != OVEST))
            direzione = EST;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}
