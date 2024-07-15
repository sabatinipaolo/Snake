public class Punto {
    private int r;
    private int c;
    public Punto(int r, int c) {
        this.r = r;
        this.c = c;
    }
    public int getC() {
        return c;
    }
    public int getR() {
        return r;
    }
    public static Punto somma(Punto p1, Punto p2) {
        int r = p1.getR() + p2.getR();
        int c = p1.getC() + p2.getC();

        //TODO : rendere più elegante ...
        if (r < 0) {
            r = r + 20;
        } else if (r > 19) {
            r = r - 20;
        }

        if (c < 0) {
            c = c + 20;
        } else if (c > 19) {
            c = c - 20;
        }
        return (new Punto(r, c));
    }
}
