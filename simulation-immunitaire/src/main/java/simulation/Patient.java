package simulation;

import java.util.ArrayList;

public abstract class Patient {
    protected double coefficientFatigue; // f
    protected ArrayList<Pathogene> pathogenes;

    public Patient(double coefficientFatigue) {
        this.coefficientFatigue = coefficientFatigue;
        this.pathogenes = new ArrayList<>();
    }

    public void ajouterPathogene(Pathogene p) {
        this.pathogenes.add(p);
    }
    
    public ArrayList<Pathogene> getPathogenes() {
        return pathogenes;
    }

    // Méthode abstraite pour l'évolution immunitaire spécifique au type de patient
    public abstract void evoluerReponseImmunitaire();
}