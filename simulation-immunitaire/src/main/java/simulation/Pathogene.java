package simulation;

import java.util.HashMap;

public abstract class Pathogene {
    protected double lt;                  
    protected double tauxReplication;     
    protected double sensibiliteImm;      
    protected double sensibiliteMedicament; 
    protected double rm;                  
    protected double beta;                
    protected double reponseImmu;         

    public Pathogene(double lt, double tauxReplication, double sensibiliteImm, double sensibiliteMedicament, double rm, double beta) {
        this.lt = lt;
        this.tauxReplication = tauxReplication;
        this.sensibiliteImm = sensibiliteImm;
        this.sensibiliteMedicament = sensibiliteMedicament; 
        this.rm = rm;
        this.beta = beta;
        this.reponseImmu = 0.0;
    }

    public double getLt() { return lt; }
    public double getBeta() { return beta; }
    public double getReponseImmu() { return reponseImmu; }
    public void setReponseImmu(double reponseImmu) { this.reponseImmu = reponseImmu; }
    public double getRm() { return rm; }
    public void setRm(double rm) { this.rm = rm; }

   
    public abstract void evoluer(double lt, HashMap<Medicament, Double> doseMedicaments);
    
    
    protected double calculerEffetMedicaments(HashMap<Medicament, Double> doseMedicaments) {
        double effetTotal = 0;
        for (HashMap.Entry<Medicament, Double> entry : doseMedicaments.entrySet()) {
            double dose = entry.getValue();
            effetTotal += this.sensibiliteMedicament * dose * (1 - this.rm);
        }
        return effetTotal;
    }
    
    
    public void diminuerReponseImmunitaire(Patient patient) {
       
    }
}
