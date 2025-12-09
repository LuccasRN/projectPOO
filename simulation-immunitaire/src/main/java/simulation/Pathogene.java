package simulation;

import java.util.HashMap;

public abstract class Pathogene {
    protected double lt;                  // Charge infectieuse (L)
    protected double tauxReplication;     // tau_c
    protected double sensibiliteImm;      // alpha_i
    protected double sensibiliteMedicament; // alpha_m
    protected double rm;                  // Résistance (Rm)
    protected double beta;                // Réactivité du système immunitaire
    protected double reponseImmu;         // It (Stocké ici selon l'UML et Solution 1)

    public Pathogene(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta) {
        this.lt = lt;
        this.tauxReplication = tauxReplication;
        this.sensibiliteImm = sensibiliteImm;
        this.sensibiliteMedicament = sensibiliteMedicament; // alpha_m
        this.rm = rm;
        this.beta = beta;
        this.reponseImmu = 0.0;
    }

    // Accesseurs nécessaires pour le Patient et l'affichage
    public double getLt() { return lt; }
    public double getBeta() { return beta; }
    public double getReponseImmu() { return reponseImmu; }
    public void setReponseImmu(double reponseImmu) { this.reponseImmu = reponseImmu; }
    public double getRm() { return rm; }
    public void setRm(double rm) { this.rm = rm; }

    /**
     * Méthode abstraite définie dans l'UML.
     * @param lt Charge actuelle (parfois redondant avec this.lt mais demandé par l'UML)
     * @param doseMedicaments Map des médicaments et leurs doses.
     */
    public abstract void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments);
    
    /**
     * Méthode utilitaire pour calculer l'effet des médicaments (Somme dans Eq 1 & 2).
     * Somme(alpha_m * Dm,t * (1 - Rm))
     */
    protected double calculerEffetMedicaments(HashMap<Medicament, Double> doseMedicaments) {
        double effetTotal = 0;
        for (HashMap.Entry<Medicament, Double> entry : doseMedicaments.entrySet()) {
            double dose = entry.getValue();
            // alpha_m * dose * (1 - Rm)
            effetTotal += this.sensibiliteMedicament * dose * (1 - this.rm);
        }
        return effetTotal;
    }
    
    // Méthode de l'UML (pour une éventuelle interaction globale - Solution 2 non utilisée ici)
    public void diminuerReponseImmunitaire(Patient patient) {
        // Non implémenté car nous utilisons la Solution 1 (indépendance par pathogène)
    }
}
