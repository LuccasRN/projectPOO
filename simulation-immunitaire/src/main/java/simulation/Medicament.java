package simulation;

public class Medicament {
    private final String nom;
    private final double alphaM;   // sensibilité du pathogène αm
    private final double h;        // taux de disparition h
    private double concentration;  // Dm,t

    public Medicament(String nom, double alphaM, double h) {
        this.nom = nom;
        this.alphaM = alphaM;
        this.h = h;
        this.concentration = 0.0;
    }

    public String getNom() {
        return nom;
    }

    public double getAlphaM() {
        return alphaM;
    }

    public double getConcentration() {
        return concentration;
    }

    public void administrerDose(double dose) throws InvalidDoseException {
        if (dose < 0) {
            throw new InvalidDoseException("Dose négative interdite");
        }
        concentration += dose;
    }

    public void evoluer() {
        concentration = h * concentration;
    }
}
