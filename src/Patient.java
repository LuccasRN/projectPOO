import java.util.*;

public abstract class Patient {
    protected double coefficientFatigue;
    protected List<Pathogene> pathogenes;
    protected double activiteImmunitaire;

    public Patient(double coefficientFatigue) {
        this.coefficientFatigue = coefficientFatigue;
        this.pathogenes = new java.util.ArrayList<>();
        this.activiteImmunitaire = 0;
    }

    public abstract void evoluerReponseImmunitaire();

    public void ajouterPathogene(Pathogene p) {
        pathogenes.add(p);
    }

    public double getCoefficientFatigue() {
        return coefficientFatigue;
    }

    public List<Pathogene> getPathogenes() {
        return pathogenes;
    }

    public double getActiviteImmunitaire() {
        return activiteImmunitaire;
    }

    public void setActiviteImmunitaire(double activiteImmunitaire) {
        this.activiteImmunitaire = Math.max(0, activiteImmunitaire);
    }
}
