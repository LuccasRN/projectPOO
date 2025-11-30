package simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    @Test
    void passerUnCycleMetAJourLIDetLesMedicaments() throws Exception {
        ReponseImmunitaire rep = new ReponseImmunitaire(0.0, 0.4, 0.1);
        PlanTraitement plan = new PlanTraitement();

        Medicament med = new Medicament("Med", 0.5, 0.8);
        plan.ajouterMedicament(med);
        plan.programmerDose(med, 0, 1.0);

        AgentPathogene patho = new AgentPathogene("Patho", 10.0, 0.3, 0.2);
        patho.setAlphaM(med, med.getAlphaM());
        patho.setResistanceInitiale(med, 0.0);

        Patient patient = new Patient("P1", rep, plan);
        patient.ajouterAgent(patho);

        patient.passerUnCycle(0);

        // después de un ciclo: debe haber algo de medicamento y cambio de L e I
        assertEquals(1.0, med.getConcentration(), 1e-6);
        assertEquals(1, patient.getAgents().size());
    }
}
