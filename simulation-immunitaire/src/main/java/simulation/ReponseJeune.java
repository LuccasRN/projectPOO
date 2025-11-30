package simulation;

public class ReponseJeune extends ReponseImmunitaire {
    private final double p;   // factor under sqrt in equation (5)

    public ReponseJeune(double I0, double beta, double f, double p) {
        super(I0, beta, f);
        this.p = p;
    }

    @Override
    public void mettreAJour(double Lsuivant) {
        double It1 = I + beta * Math.sqrt(p) * Lsuivant - f * I;
        I = Math.max(0.0, It1);
    }
}