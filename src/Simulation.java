public class Simulation {
    private Patient patient;
    private Traitement traitement;
    private int pasTemps;

    public Simulation(Patient patient, Traitement traitement) {
        this.patient = patient;
        this.traitement = traitement;
        this.pasTemps = 0;
    }

    public void executerPas() {
        traitement.evoluerConcentration(pasTemps);

        double activiteActuelle = patient.getActiviteImmunitaire();
        for (Pathogene p : patient.getPathogenes()) {
            p.evoluer(activiteActuelle, traitement.getMedicament());
            p.diminuerReponseImmunitaire(patient);
        }

        patient.evoluerReponseImmunitaire();

        pasTemps++;
    }

    public void simuleNpas(int n) {
        for (int i = 0; i < n; i++) {
            executerPas();
            afficherEtat();
        }
    }

    public void afficherEtat() {
        System.out.println("Cycle : " + pasTemps + " ==========");
        System.out.println("Activité immunitaire : " +
                String.format("%.2f", patient.getActiviteImmunitaire()));
        System.out.println("Concentration médicament : " +
                String.format("%.2f", traitement.getMedicament().getDoseMedicament()));

        int compteur = 1;
        for (Pathogene p : patient.getPathogenes()) {
            System.out.println("Pathogène " + compteur + " - Charge : " +
                    String.format("%.2f", p.getLt()));
            compteur++;
        }
        System.out.println();
    }

    public Patient getPatient() {
        return patient;
    }

    public Traitement getTraitement() {
        return traitement;
    }

    public int getPasTemps() {
        return pasTemps;
    }
}
