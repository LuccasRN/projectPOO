package simulation;

import java.util.HashMap;

public class PathogeneClassique extends Pathogene {
    public PathogeneClassique(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta) {
        super(lt, tauxReplication, sensibiliteImm, sensibiliteMedicament, rm, beta);
    }

    @Override
    public void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments) {
        // Lt+1 = Lt + (tau * Lt) - (alpha_i * It) - EffetMedicaments
        double effetMeds = calculerEffetMedicaments(doseMedicaments);
        double delta = (tauxReplication * this.lt) - (sensibiliteImm * reponseImmu) - effetMeds;
        
        // Mise à jour en évitant les valeurs négatives (max(0, ...))
        this.lt = Math.max(0, this.lt + delta);
    }
}
