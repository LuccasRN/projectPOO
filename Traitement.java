public class Traitement {
    public Medicament medicament;
    public double concentrationActuelle;
    private double dosePrescrite;
    private int intervallePrise;

    public Traitement(Medicament medicament, double c, double d, int i) {
        this.medicament = medicament;
        this.concentrationActuelle = c;
        this.dosePrescrite = d;
        this.intervallePrise = i;
    }

    public void evoluerConcentration() {
        concentrationActuelle = concentrationActuelle * medicament.getTauxDisparition();
    }

    public void administer(int numeroCycle) {
        if (numeroCycle % intervallePrise == 0) {
            concentrationActuelle += dosePrescrite;
        }

    }

    public Medicament getMedicament() {
        return medicament;
    }
}