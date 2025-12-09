package simulation;

import java.util.HashMap;

public class PathogeneDynamique extends Pathogene implements Dynamique {
    protected double tauxImpact; // delta_m

    public PathogeneDynamique(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta, double tauxImpact) {
        super(lt, tauxReplication, sensibiliteImm, sensibiliteMedicament, rm, beta);
        this.tauxImpact = tauxImpact;
    }

    @Override
    public void evoluerResistance() {
        // Méthode de l'interface Dynamique.
        // Sera appelée implicitement lors de l'évolution.
    }
    
    /**
     * Met à jour la résistance Rm selon l'équation (3).
     * Rm,t+1 = Rm,t + delta * Dm,t
     */
    private void mettreAJourResistance(double doseTotale) {
        this.rm = this.rm + (this.tauxImpact * doseTotale);
    }

    @Override
    public void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments) {
        // 1. Mise à jour de la résistance (Dynamique)
        double doseTotale = doseMedicaments.values().stream().mapToDouble(Double::doubleValue).sum();
        mettreAJourResistance(doseTotale);

        // 2. Évolution de la charge (Classique Eq 1 pour ce modèle de base)
        double effetMeds = calculerEffetMedicaments(doseMedicaments);
        double delta = (tauxReplication * this.lt) - (sensibiliteImm * reponseImmu) - effetMeds;
        this.lt = Math.max(0, this.lt + delta);
    }
}
