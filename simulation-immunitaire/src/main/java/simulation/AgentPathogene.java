package simulation;

import java.util.HashMap;
import java.util.Map;

public class AgentPathogene {
    private final String nom;
    private double L;                     // charge infectieuse Lt
    private final double tauC;           // taux de replication
    private final double alphaI;         // sensibilite a l'inmunite
    private final Map<Medicament, Double> alphaM;   // sensibilité au médicament αm
    private final Map<Medicament, Double> resistance; // Rm par medicament
    private final Map<Medicament, Double> delta;      // δm (seulement si resistance dynamique)

    public AgentPathogene(String nom,
                          double L0,
                          double tauC,
                          double alphaI) {
        this.nom = nom;
        this.L = L0;
        this.tauC = tauC;
        this.alphaI = alphaI;
        this.alphaM = new HashMap<>();
        this.resistance = new HashMap<>();
        this.delta = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public double getChargeInfectieuse() {
        return L;
    }

    public void setAlphaM(Medicament m, double alphaMVal) {
        alphaM.put(m, alphaMVal);
    }

    public void setResistanceInitiale(Medicament m, double r0) {
        resistance.put(m, r0);
    }

    public void setDelta(Medicament m, double deltaVal) {
        delta.put(m, deltaVal);
    }

    public double getResistance(Medicament m) {
        return resistance.getOrDefault(m, 0.0);
    }

    protected double termeReplication() {
        return tauC * L;
    }

    public void mettreAJour(double It, Map<Medicament, Double> dosesCourantes) {
        double sommeMedicaments = 0.0;
        for (Map.Entry<Medicament, Double> e : dosesCourantes.entrySet()) {
            Medicament m = e.getKey();
            double Dmt = e.getValue();
            double alpha = alphaM.getOrDefault(m, 0.0);
            double Rm = resistance.getOrDefault(m, 0.0);
            sommeMedicaments += alpha * Dmt * (1.0 - Rm);

            // si el patógeno tiene resistencia dinámica, actualizar Rm,t+1
            Double d = delta.get(m);
            if (d != null) {
                double rActuel = resistance.getOrDefault(m, 0.0);
                resistance.put(m, rActuel + d * Dmt);
            }
        }

        double Lt1 = L + termeReplication() - alphaI * It - sommeMedicaments;
        L = Math.max(0.0, Lt1);
    }

    public boolean estAgressif() {
        return false;
    }

    public boolean aResistanceDynamique() {
        return !delta.isEmpty();
    }
}
