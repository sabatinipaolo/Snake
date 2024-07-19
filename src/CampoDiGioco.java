import javax.swing.JPanel;
import java.awt.GridLayout;

public class CampoDiGioco extends JPanel {
    private Quadratino[][] matrice = new Quadratino[20][20];  //TODO: è ridondante? potrei usare direttamente il Gridlayout

    public CampoDiGioco() {
        super(new GridLayout(20, 20));  //TODO: generalizzare
        for (int r = 0; r < 20; r++)
            for (int c = 0; c < 20; c++) {
                matrice[r][c] = new Quadratino(); //di default è un quadratino di prato
                add(matrice[r][c]);
            }
    }


    public Quadratino getQuadratino(Punto punto) {
        return (matrice[punto.getR()][punto.getC()]);
    }

    public void generaCibo() {
        new Cibo(this);
    }
}
