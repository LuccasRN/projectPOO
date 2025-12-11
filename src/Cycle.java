import java.util.*;

public class Cycle {
    private int numer_Cycles;
    private double DureoDuCycle;
    private List<String> Historique;

    public Cycle(int numer_Cycles, double DureoDuCycle) {
        this.numer_Cycles = numer_Cycles;
        this.DureoDuCycle = DureoDuCycle;
        this.Historique = new ArrayList<>();
    }

    public void AjouterHistorique(String entry) {
        Historique.add(entry);
    }

    public int getNumer_Cycles() {
        return numer_Cycles;
    }

    public double getDureoDuCycle() {
        return DureoDuCycle;
    }

    public List<String> getHistorique() {
        return Historique;
    }
}
