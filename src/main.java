public class Main {
    public static void main(String[] args) throws Exception {
        exempleLivre();

        System.out.println("\n\n Pathogène Dynamique :\n");
        testDynamique();
    }

    public static void exempleLivre() {
        Patient patient = new PatientAdulte(0.1, 0.4);

        Pathogene pathogene = new PathogeneClassique(0.3, 0.2, 0.1, 0.4);
        pathogene.setLt(10);
        patient.ajouterPathogene(pathogene);

        Medicament medicament = new Medicament(0.8, 0.5);
        Traitement traitement = new Traitement(medicament);

        for (int i = 0; i < 20; i += 3) {
            traitement.administrerDose(i, 1.0);
        }

        Simulation simulation = new Simulation(patient, traitement);
        System.out.println("Patient adulte | Pathogène classique | Traitement tous les 3 cycles\n");
        simulation.afficherEtat();
        simulation.simuleNpas(20);
    }

    public static void testDynamique() {
        Patient patient = new PatientAdulte(0.1, 0.4);

        PathogeneDynamique pathogene = new PathogeneDynamique(0.3, 0.2, 0.05, 0.4, 0.02);
        pathogene.setLt(10);
        patient.ajouterPathogene(pathogene);

        Medicament medicament = new Medicament(0.8, 0.5);
        Traitement traitement = new Traitement(medicament);

        for (int i = 0; i < 20; i += 2) {
            traitement.administrerDose(i, 1.5);
        }

        Simulation simulation = new Simulation(patient, traitement);
        System.out.println("Pathogène avec résistance dynamique (augmente au fil du temps)\n");
        simulation.afficherEtat();
        simulation.simuleNpas(20);

        System.out.println("Résistance finale du pathogène : " + String.format("%.3f", pathogene.getRm()));
    }
}
