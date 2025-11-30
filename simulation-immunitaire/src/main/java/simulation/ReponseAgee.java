package simulation;

public class ReponseAgee extends ReponseImmunitaire {

    public ReponseAgee(double I0, double beta, double f) {
        super(I0, beta, f);
    }

    @Override
    public void mettreAJour(double Lsuivant) {
        double It1 = I + beta * Lsuivant - f * I * I;
        I = Math.max(0.0, It1);
    }
}
