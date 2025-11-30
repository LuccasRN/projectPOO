package simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReponseImmunitaireTest {

    @Test
    void reponseStandardSuitEquation4() {
        ReponseImmunitaire r = new ReponseImmunitaire(0.0, 0.4, 0.1);
        double L1 = 10.0;

        r.mettreAJour(L1);

        double attendu = Math.max(0.0, 0.0 + 0.4 * L1 - 0.1 * 0.0);
        assertEquals(attendu, r.getI(), 1e-6);
    }

    @Test
    void reponseJeuneMoinsReactive() {
        ReponseImmunitaire adulte = new ReponseImmunitaire(0.0, 0.4, 0.1);
        ReponseJeune jeune = new ReponseJeune(0.0, 0.4, 0.1, 1.0);

        double L1 = 10.0;
        adulte.mettreAJour(L1);
        jeune.mettreAJour(L1);

        assertTrue(jeune.getI() <= adulte.getI());
    }

    @Test
    void reponseAgeeFatigueQuadratique() {
        ReponseAgee agee = new ReponseAgee(5.0, 0.4, 0.1);
        double L1 = 10.0;

        agee.mettreAJour(L1);

        double attendu = Math.max(0.0, 5.0 + 0.4 * L1 - 0.1 * 25.0);
        assertEquals(attendu, agee.getI(), 1e-6);
    }
}
