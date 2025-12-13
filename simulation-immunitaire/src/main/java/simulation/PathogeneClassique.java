package simulation;

import java.util.HashMap;

public class PathogeneClassique extends Pathogene {
    public PathogeneClassique(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta) {
        super(lt, tauxReplication, sensibiliteImm, sensibiliteMedicament, rm, beta);
    }

    @Override
    public void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments) {
        
        double effetMeds = calculerEffetMedicaments(doseMedicaments);
        double delta = (tauxReplication * this.lt) - (sensibiliteImm * reponseImmu) - effetMeds;
        
        this.lt = Math.max(0, this.lt + delta);
    }
}
