import java.awt.Point;
import java.util.Random;


public class Cibo {  //TODO usare il polimorfismo visto che un cibo è alla fine è un quadratino...

    private Point posizione;

    public Cibo(CampoDiGioco campo) {
        //un cibo deve stare in un campo ... //TODO: coupling ?


        Random random = new Random();
        Point puntoACaso;
        do {
            puntoACaso = new Point(random.nextInt(19), random.nextInt(19)); //TODO: generalizzare
        }
        while (campo.getQuadratino(puntoACaso).getTipo() != Quadratino.PRATO);

        posizione = puntoACaso;
        campo.getQuadratino(posizione).setToCibo();  //viola la legge di demetra?

    }


}
