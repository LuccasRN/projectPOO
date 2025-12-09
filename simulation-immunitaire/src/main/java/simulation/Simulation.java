package simulation;

import java.util.HashMap;

public class Simulation {
    private Patient patient;
    private Traitement traitement;
    private Cycle gestionnaireCycle;

    public Simulation(Patient patient, Traitement traitement, int cycles) {
        this.patient = patient;
        this.traitement = traitement;
        this.gestionnaireCycle = new Cycle(cycles);
    }

    /**
     * Boucle principale de simulation (Equation du texte Section 1).
     */
    public void simule() {
        System.out.println("Début de la simulation...");
        // En-têtes du tableau
        System.out.printf("%-6s | %-15s | %-15s | %-15s | %-15s\n", 
            "Cycle", "Charge (L)", "Immunité (I)", "Médic (D)", "Résistance (R)");
        System.out.println("--------------------------------------------------------------------------------");

        while (gestionnaireCycle.aUnSuivant()) {
            int t = gestionnaireCycle.suivant();

            // 1. Administration du traitement (si prescrit à ce cycle)
            traitement.administrerDose(t);
            HashMap<Medicament, Double> dosesActuelles = traitement.getDoses();

            // 2. Évolution des Pathogènes (Calcul de Lt+1)
            // Utilise l'immunité actuelle (It) et les doses actuelles (Dm,t)
            for (Pathogene p : patient.getPathogenes()) {
                p.evoluer(p.getLt(), dosesActuelles);
            }

            // 3. Évolution de la Réponse Immunitaire (Calcul de It+1)
            // Utilise la nouvelle charge infectieuse (Lt+1) calculée juste avant
            patient.evoluerReponseImmunitaire();

            // 4. Évolution de la concentration médicamenteuse (Disparition)
            // Calcul de Dm,t+1 pour le prochain tour
            traitement.evoluerConcentrations();

            afficherResultat(t);
        }
    }

    public void afficherResultat(int cycle) {
        // Affichage des données du premier pathogène et premier médicament pour l'exemple
        if (!patient.getPathogenes().isEmpty()) {
            Pathogene p = patient.getPathogenes().get(0);
            double dose = 0;
            if(!traitement.getMedicaments().isEmpty()) {
                dose = traitement.getMedicaments().get(0).getDoseMedic();
            }
            
            System.out.printf("%-6d | %-15.4f | %-15.4f | %-15.4f | %-15.4f\n", 
                cycle, p.getLt(), p.getReponseImmu(), dose, p.getRm());
        }
    }

    public static void main(String[] args) {
        // --- Configuration du CAS DE TEST (Section 3.1) ---
        
        // 1. Patient : Adulte, f = 0.1
        Patient patient = new PatientAdulte(0.1);

        // 2. Médicament
        // Sensibilité pathogène alpha_m sera définie dans le pathogène (0.5)
        // Taux de disparition h = 0.8
        Medicament med = new Medicament("Antibiotique", 0.8);
        
        Traitement traitement = new Traitement();
        traitement.addMedicament(med);

        // 3. Pathogène
        // Charge initiale L0 = 10
        // Taux réplication tau_c = 0.3
        // Sensibilité immunitaire alpha_i = 0.2
        // Sensibilité médicament alpha_m = 0.5 (paramètre demandé par le test)
        // Résistance initiale Rm = 0.1
        // Réactivité immunitaire beta = 0.4
        Pathogene pathogene = new PathogeneClassique(
            10.0, // Lt
            0.3,  // tau
            0.2,  // alpha_i
            0.5,  // alpha_m
            0.1,  // Rm
            0.4   // beta
        );
        
        patient.ajouterPathogene(pathogene);

        // 4. Lancer la simulation sur 20 cycles
        Simulation sim = new Simulation(patient, traitement, 20);
        sim.simule();
    }
}
