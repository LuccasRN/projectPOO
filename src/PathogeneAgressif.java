public class PathogeneAgressif extends Pathogene implements Agressif {
    public PathogeneAgressif(double tauxReplication, double sensibiliteImmunitaire,
            double rm, double beta) {
        super(tauxReplication, sensibiliteImmunitaire, rm, beta);
    }

    @Override
    public void evoluer(double It, Medicament medicament) {
        double effectMedicaments = medicament.getSensibiliteMediacament() * medicament.getDoseMedicament() * (1 - rm);

        double nouvelleCh = Lt + tauxReplication * Lt * Lt - sensibiliteImmunitaire * It - effectMedicaments;
        setLt(nouvelleCh);
    }

    @Override
    public void diminuerReponseImmunitaire(Patient patient) {
        // Pathogène agressif : impact significatif sur la réponse immunitaire
        double reduction = beta * Lt * 0.1; // Impact modéré (10%)
        patient.setActiviteImmunitaire(patient.getActiviteImmunitaire() - reduction);
    }
}
