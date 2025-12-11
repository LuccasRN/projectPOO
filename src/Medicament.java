public class Medicament {
    private double tauxDisparition;
    private double doseMedicament;
    private double sensibiliteMediacament;

    public Medicament(double tauxDisparition, double sensibiliteMediacament) {
        this.tauxDisparition = tauxDisparition;
        this.sensibiliteMediacament = sensibiliteMediacament;
        this.doseMedicament = 0;
    }

    public void evoluerConcentration(double dosePrise) {
        doseMedicament = tauxDisparition * doseMedicament + dosePrise;
    }

    public double getTauxDisparition() {
        return tauxDisparition;
    }

    public double getDoseMedicament() {
        return doseMedicament;
    }

    public void setDoseMedicament(double doseMedicament) {
        this.doseMedicament = Math.max(0, doseMedicament);
    }

    public double getSensibiliteMediacament() {
        return sensibiliteMediacament;
    }
}
