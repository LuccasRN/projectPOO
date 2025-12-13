package simulation;

public class Medicament {
    private String nom;
    private double tauxDisparition; 
    private double doseMedic;       
    
    public Medicament(String nom, double tauxDisparition) {
        this.nom = nom;
        this.tauxDisparition = tauxDisparition;
        this.doseMedic = 0.0;
    }

    public double getDoseMedic() { return doseMedic; }
    public String getNom() { return nom; }

  
    public void administrerDose(double dose) {
        this.doseMedic += dose;
    }

    public void evoluerConcentrations() {
        this.doseMedic = this.doseMedic * this.tauxDisparition;
    }
}
