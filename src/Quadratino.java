import java.awt.Color;
import javax.swing.JPanel;


//TODO: la variabile tipo invita ad usare il polimorfismo ?!
public class Quadratino extends JPanel {
    private int tipo;

    public Quadratino() {
        setToPrato();
    }

    public int getTipo() {
        return tipo;
    }

    public void setToPrato() {
        setBackground(Color.GREEN);
        tipo = PRATO;

    }

    public void setToSerpente() {
        setBackground(Color.YELLOW);
        tipo = SERPENTE;

    }

    public void setToCibo() {
        setBackground(Color.RED);
        tipo = CIBO;

    }

    public static final int PRATO = 0;
    public static final int SERPENTE = 2;
    public static final int CIBO = 1;


}
