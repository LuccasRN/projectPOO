package simulation;

import java.util.HashMap;

public class PathogeneDynamique extends Pathogene implements Dynamique {
    protected double tauxImpact; 

    public PathogeneDynamique(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta, double tauxImpact) {
        super(lt, tauxReplication, sensibiliteImm, sensibiliteMedicament, rm, beta);
        this.tauxImpact = tauxImpact;
    }

    @Override
    public void evoluerResistance() {
    }
    
    private void mettreAJourResistance(double doseTotale) {
        this.rm = this.rm + (this.tauxImpact * doseTotale);
    }

    @Override
    public void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments) {
        
        double doseTotale = doseMedicaments.values().stream().mapToDouble(Double::doubleValue).sum();
        mettreAJourResistance(doseTotale);

        double effetMeds = calculerEffetMedicaments(doseMedicaments);
        double delta = (tauxReplication * this.lt) - (sensibiliteImm * reponseImmu) - effetMeds;
        this.lt = Math.max(0, this.lt + delta);
    }
}
