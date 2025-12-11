import java.util.*;

public class Traitement {
    private Map<Integer, Double> dosesPrescrites;
    private Medicament medicament;
    private double doseTrait;

    public Traitement(Medicament medicament) {
        this.medicament = medicament;
        this.dosesPrescrites = new HashMap<>();
        this.doseTrait = 0;
    }

    public void administrerDose(int cycle, double dose) {
        dosesPrescrites.put(cycle, dose);
    }

    public double getDosePourCycle(int cycle) {
        return dosesPrescrites.getOrDefault(cycle, 0.0);
    }

    public void evoluerConcentration(int cycle) {
        double dm = getDosePourCycle(cycle);
        medicament.evoluerConcentration(dm);
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public double getDoseTrait() {
        return doseTrait;
    }

    public void setDoseTrait(double doseTrait) {
        this.doseTrait = doseTrait;
    }

    public Map<Integer, Double> getDosesPrescrites() {
        return dosesPrescrites;
    }
}
