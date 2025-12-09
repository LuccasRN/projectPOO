package simulation;

import java.util.HashMap;

public class PathogeneAgressif extends Pathogene implements Agressif {
    public PathogeneAgressif(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta) {
        super(lt, tauxReplication, sensibiliteImm, sensibiliteMedicament, rm, beta);
    }

    @Override
    public void evoluer() {
        // Méthode de l'interface (logique spécifique si besoin hors du cycle standard)
    }

    @Override
    public void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments) {
        double effetMeds = calculerEffetMedicaments(doseMedicaments);
        // Équation 2 : Croissance quadratique (tau * Lt^2)
        double delta = (tauxReplication * Math.pow(this.lt, 2)) - (sensibiliteImm * reponseImmu) - effetMeds;
        
        this.lt = Math.max(0, this.lt + delta);
    }
}
