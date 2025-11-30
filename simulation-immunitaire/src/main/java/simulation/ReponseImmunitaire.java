package simulation;

public class ReponseImmunitaire {
    protected double I;     // It
    protected final double beta;
    protected final double f;

    public ReponseImmunitaire(double I0, double beta, double f) {
        this.I = I0;
        this.beta = beta;
        this.f = f;
    }

    public double getI() {
        return I;
    }

    public void mettreAJour(double Lsuivant) {
        double It1 = I + beta * Lsuivant - f * I;
        I = Math.max(0.0, It1);
    }
}
