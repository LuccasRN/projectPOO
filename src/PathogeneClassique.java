public class PathogeneClassique extends Pathogene {
    public PathogeneClassique(double tauxReplication, double sensibiliteImmunitaire,
            double rm, double beta) {
        super(tauxReplication, sensibiliteImmunitaire, rm, beta);
    }

    @Override
    public void evoluer(double It, Medicament medicament) {
        double effectMedicaments = medicament.getSensibiliteMediacament() * medicament.getDoseMedicament() * (1 - rm);

        double nouvelleCh = Lt + tauxReplication * Lt - sensibiliteImmunitaire * It - effectMedicaments;
        setLt(nouvelleCh);
    }

    @Override
    public void diminuerReponseImmunitaire(Patient patient) {
        
        double reduction = beta * Lt * 0.05; 
        patient.setActiviteImmunitaire(patient.getActiviteImmunitaire() - reduction);
    }
}
