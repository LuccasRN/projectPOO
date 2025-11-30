package simulation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {

    @Test
    void exempleDuSujetDoitDiminuerChargeVirale() throws Exception {
        // Reponse immunitaire adulte standard
        ReponseImmunitaire rep = new ReponseImmunitaire(0.0, 0.4, 0.1);

        // Pathogène
        AgentPathogene patho = new AgentPathogene("VirusA", 10.0, 0.3, 0.2);

        // Médicament
        Medicament med = new Medicament("MedA", 0.5, 0.8);
        patho.setAlphaM(med, med.getAlphaM());
        patho.setResistanceInitiale(med, 0.1);

        // Plan : dose 1 tous les 3 cycles, durée 20
        PlanTraitement plan = new PlanTraitement();
        plan.ajouterMedicament(med);
        int duree = 20;
        for (int t = 0; t < duree; t += 3) {
            plan.programmerDose(med, t, 1.0);
        }

        Patient p = new Patient("P1", rep, plan);
        p.ajouterAgent(patho);

        double LInitiale = patho.getChargeInfectieuse();

        Simulation simu = new Simulation(List.of(p), duree);
        simu.executer();

        double LFinale = patho.getChargeInfectieuse();

        assertTrue(LFinale < LInitiale,
                "La charge infectieuse doit diminuer sous traitement");
    }
}
