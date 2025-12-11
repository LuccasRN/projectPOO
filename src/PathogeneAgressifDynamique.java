public class PathogeneAgressifDynamique extends Pathogene implements Agressif, Dynamique {
    private double tauxImpactResistance;

    public PathogeneAgressifDynamique(double tauxReplication, double sensibiliteImmunitaire,
            double rm, double beta, double tauxImpactResistance) {
        super(tauxReplication, sensibiliteImmunitaire, rm, beta);
        this.tauxImpactResistance = tauxImpactResistance;
    }

    @Override
    public void evoluer(double It, Medicament medicament) {
        double effectMedicaments = medicament.getSensibiliteMediacament() * medicament.getDoseMedicament() * (1 - rm);

        double nouvelleCh = Lt + tauxReplication * Lt * Lt - sensibiliteImmunitaire * It - effectMedicaments;
        setLt(nouvelleCh);

        evoluerResistance(medicament.getDoseMedicament());
    }

    @Override
    public void evoluerResistance(double doseMedicaments) {
        rm = rm + tauxImpactResistance * doseMedicaments;
        if (rm > 1.0) {
            rm = 1.0;
        }
    }

    @Override
    public void diminuerReponseImmunitaire(Patient patient) {
        
        double reduction = beta * Lt * rm * 0.15; 
        patient.setActiviteImmunitaire(patient.getActiviteImmunitaire() - reduction);
    }

    public double getTauxImpactResistance() {
        return tauxImpactResistance;
    }
}
