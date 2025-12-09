package simulation;

public class Cycle {
    private int nombreCycles;
    private int cycleActuel;
    // DureeDuCycle ignorée pour la logique mathématique pure, mais présente dans l'UML

    public Cycle(int nombreCycles) {
        this.nombreCycles = nombreCycles;
        this.cycleActuel = 0;
    }
    
    public boolean aUnSuivant() {
        return cycleActuel < nombreCycles;
    }
    
    public int suivant() {
        return ++cycleActuel;
    }
}
