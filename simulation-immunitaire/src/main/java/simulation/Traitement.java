package simulation;

import java.util.ArrayList;
import java.util.HashMap;

public class Traitement {
    private ArrayList<Medicament> medicaments;
    
    public Traitement() {
        this.medicaments = new ArrayList<>();
    }

    public void addMedicament(Medicament m) {
        this.medicaments.add(m);
    }
    
    public ArrayList<Medicament> getMedicaments() {
        return medicaments;
    }
    public void administrerDose(int cycle) {
        for (Medicament m : medicaments) {
            if (cycle % 3 == 0) { 
                m.administrerDose(1.0); 
            }
        }
    }
    public void evoluerConcentrations() {
        for (Medicament m : medicaments) {
            m.evoluerConcentrations();
        }
    }
    public HashMap<Medicament, Double> getDoses() {
        HashMap<Medicament, Double> doses = new HashMap<>();
        for(Medicament m : medicaments) {
            doses.put(m, m.getDoseMedic());
        }
        return doses;
    }
}
