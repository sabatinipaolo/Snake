import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;
import java.util.LinkedList;

public class Serpente extends Thread implements KeyListener {
    //TODO:? creare una classe esterna AscoltaComandiPerSerpente
    // per evitare di usare le interfacce ?
    //
    //perché un thread? un serpente è una cosa viva!!
    //un domani ne mettiamo più di uno e abbiamo delle race condition (la mela !) ?

    private boolean vivo;
    private LinkedList<Point> corpo; //TODO : provare una lista di Quadratini invece di una lista di coordinate dei quadratini
    private Point direzione;
    private CampoDiGioco campo;

    //TODO: trasformare in enum ?
    private static final Point NORD = new Point(-1, 0);
    private static final Point SUD = new Point(1, 0);
    private static final Point EST = new Point(0, 1);
    private static final Point OVEST = new Point(0, -1);

    public Serpente(CampoDiGioco campo) {
        //Un serpente deve vivere in un campo ...//TODO: coupling ?

        this.campo = campo;

        vivo = true;
        direzione = NORD;

        corpo = new LinkedList<Point>();

        inizializzaIlCorpoDelSerpente();


    }

    private void inizializzaIlCorpoDelSerpente() {
        Point punto;

        // inizializza un serpente lungo 3 quadratini
        punto = new Point(19, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();  // viola la legge di Demetra?

        punto = new Point(18, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();

        punto = new Point(17, 0);
        corpo.addFirst(punto);
        campo.getQuadratino(punto).setToSerpente();
    }

    public void avanza() {
        Point posTesta = corpo.getFirst();

        Point nuovaPos = somma(posTesta, direzione);

        //controllo cosa c'è nella nuova posizione
        switch (campo.getQuadratino(nuovaPos).getTipo()) {
            case Quadratino.CIBO -> {
                campo.generaCibo();
            }
            case Quadratino.PRATO -> {
                campo.getQuadratino(corpo.getLast()).setToPrato();
                corpo.removeLast();
            }
            case Quadratino.SERPENTE -> {
                vivo = false;
            }
            default -> {
            } //non può accadere!!

        }

        corpo.addFirst(nuovaPos);
        campo.getQuadratino(nuovaPos).setToSerpente();

        campo.repaint();
    }
    public static Point somma(Point p1, Point p2) {
        int x = p1.x + p2.x;
        int y = p1.y + p2.y;

        //TODO : rendere più elegante ...
        if (x < 0) {
            x = x + 20;
        } else if (x > 19) {
            x = x - 20;
        }

        if (y < 0) {
            y = y + 20;
        } else if (y > 19) {
            y = y - 20;
        }
        return (new Point(x, y));
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
