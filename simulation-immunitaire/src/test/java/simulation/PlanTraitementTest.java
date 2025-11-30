package simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanTraitementTest {

    @Test
    void mettreAJourConcentrationsAppliqueHPlusDose() throws Exception {
        Medicament med = new Medicament("Med", 0.0, 0.5); // h = 0.5
        PlanTraitement plan = new PlanTraitement();
        plan.ajouterMedicament(med);

        // ciclo 0: tomar 2.0
        plan.programmerDose(med, 0, 2.0);
        plan.mettreAJourConcentrations(0);
        assertEquals(2.0, med.getConcentration(), 1e-6);

        // ciclo 1: sin nueva dosis -> solo desaparición
        plan.mettreAJourConcentrations(1);
        assertEquals(1.0, med.getConcentration(), 1e-6);
    }
}
