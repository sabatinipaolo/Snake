import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;

public class CampoDiGioco extends JPanel {
    private Quadratino[][] matrice = new Quadratino[20][20];  //TODO: è ridondante? potrei usare direttamente il Gridlayout

    public CampoDiGioco() {
        super(new GridLayout(20, 20));  //TODO: generalizzare
        for (int x = 0; x < 20; x++)
            for (int y = 0; y < 20; y++) {
                matrice[x][y] = new Quadratino(); //di default è un quadratino di prato
                add(matrice[x][y]);
            }
    }


    public Quadratino getQuadratino(Point punto) {
        return (matrice[punto.x][punto.y]);
    }

    public void generaCibo() {
        new Cibo(this);
    }
}
