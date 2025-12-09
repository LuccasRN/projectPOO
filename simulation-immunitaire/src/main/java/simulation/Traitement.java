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

    /**
     * Administre la dose selon le cycle.
     * Pour l'exemple du test : dose de 1 tous les 3 cycles.
     */
    public void administrerDose(int cycle) {
        for (Medicament m : medicaments) {
            // Logique de prescription simple (selon test 3.1)
            if (cycle % 3 == 0) { 
                m.administrerDose(1.0); 
            }
        }
    }

    /**
     * Fait évoluer la concentration (disparition naturelle).
     */
    public void evoluerConcentrations() {
        for (Medicament m : medicaments) {
            m.evoluerConcentrations();
        }
    }
    
    /**
     * Récupère une Map des doses actuelles pour les passer aux pathogènes.
     */
    public HashMap<Medicament, Double> getDoses() {
        HashMap<Medicament, Double> doses = new HashMap<>();
        for(Medicament m : medicaments) {
            doses.put(m, m.getDoseMedic());
        }
        return doses;
    }
}
