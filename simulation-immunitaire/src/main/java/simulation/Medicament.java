package simulation;

public class Medicament {
    private String nom;
    private double tauxDisparition; // h : Taux de disparition
    private double doseMedic;       // Dm,t : Dose présente
    
    // Note : Dans le texte, la sensibilité est alpha_m.
    // Dans l'UML, 'sensibiliteMedicament' est dans Pathogene.
    // Nous utiliserons la dose ici pour les calculs.

    public Medicament(String nom, double tauxDisparition) {
        this.nom = nom;
        this.tauxDisparition = tauxDisparition;
        this.doseMedic = 0.0;
    }

    // Getters et Setters
    public double getDoseMedic() { return doseMedic; }
    public String getNom() { return nom; }

    /**
     * Ajoute une dose au médicament (dm,t).
     */
    public void administrerDose(double dose) {
        this.doseMedic += dose;
    }

    /**
     * Évolution de la concentration selon l'équation (9) :
     * Dm,t+1 = h * Dm,t
     * (La nouvelle dose dm,t est ajoutée via administrerDose).
     */
    public void evoluerConcentrations() {
        this.doseMedic = this.doseMedic * this.tauxDisparition;
    }
}
