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

    public void simule() {
        System.out.println("Début de la simulation...");

        System.out.printf("%-6s | %-15s | %-15s | %-15s | %-15s\n", 
            "Cycle", "Charge (L)", "Immunité (I)", "Médic (D)", "Résistance (R)");
        System.out.println("--------------------------------------------------------------------------------");

        while (gestionnaireCycle.aUnSuivant()) {
            int t = gestionnaireCycle.suivant();

            traitement.administrerDose(t);
            HashMap<Medicament, Double> dosesActuelles = traitement.getDoses();

            for (Pathogene p : patient.getPathogenes()) {
                p.evoluer(p.getLt(), dosesActuelles);
            }

            patient.evoluerReponseImmunitaire();

            traitement.evoluerConcentrations();

            afficherResultat(t);
        }
    }

    public void afficherResultat(int cycle) {

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

        Patient patient = new PatientAdulte(0.1);

        Medicament med = new Medicament("Antibiotique", 0.8);
        
        Traitement traitement = new Traitement();
        traitement.addMedicament(med);

        Pathogene pathogene = new PathogeneClassique(
            10.0, 
            0.3,  
            0.2,  
            0.5,  
            0.1,
            0.4  
        );
        
        patient.ajouterPathogene(pathogene);

        Simulation sim = new Simulation(patient, traitement, 20);
        sim.simule();
    }
}
