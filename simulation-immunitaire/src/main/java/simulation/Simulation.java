package simulation;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Patient> patients;
    private final int cycles;

    public Simulation(List<Patient> patients, int cycles) {
        this.patients = new ArrayList<>(patients);
        this.cycles = cycles;
    }

    public void executer() throws InvalidDoseException {
        for (int t = 0; t < cycles; t++) {
            System.out.println("Cycle " + t);
            for (Patient p : patients) {
                p.passerUnCycle(t);
                System.out.println(p);
            }
            System.out.println("-----------------------");
        }
    }

    public static void main(String[] args) throws Exception {
        // Exemple de test du sujet:
        // Patient adulte, réponse standard (β = 0.4, f = 0.1)
        ReponseImmunitaire rep = new ReponseImmunitaire(0.0, 0.4, 0.1);

        // Pathogène classique :
        // L0 = 10, τc = 0.3, αi = 0.2, Rm = 0.1
        AgentPathogene patho = new AgentPathogene("VirusA", 10.0, 0.3, 0.2);

        // Médicament : αm = 0.5, h = 0.8
        Medicament med = new Medicament("MedA", 0.5, 0.8);
        patho.setAlphaM(med, med.getAlphaM());
        patho.setResistanceInitiale(med, 0.1); // Rm = 0.1

        // Plan de traitement : dm,t = 1 tous les 3 cycles
        PlanTraitement plan = new PlanTraitement();
        plan.ajouterMedicament(med);
        int duree = 20;
        for (int t = 0; t < duree; t += 3) {
            plan.programmerDose(med, t, 1.0);
        }

        Patient patient = new Patient("P1", rep, plan);
        patient.ajouterAgent(patho);

        List<Patient> lp = new ArrayList<>();
        lp.add(patient);

        Simulation simu = new Simulation(lp, duree);
        simu.executer();
    }
}

