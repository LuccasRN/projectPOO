package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanTraitement {
    private final List<Medicament> medicaments;
    // planning: cycle -> (médicament -> dose)
    private final Map<Integer, Map<Medicament, Double>> planning;

    public PlanTraitement() {
        this.medicaments = new ArrayList<>();
        this.planning = new HashMap<>();
    }

    public void ajouterMedicament(Medicament m) {
        if (!medicaments.contains(m)) {
            medicaments.add(m);
        }
    }

    public List<Medicament> getMedicaments() {
        return Collections.unmodifiableList(medicaments);
    }

    public void programmerDose(Medicament m, int cycle, double dose)
            throws InvalidParameterException {
        if (!medicaments.contains(m)) {
            throw new InvalidParameterException("Médicament non enregistré dans le plan");
        }
        if (cycle < 0) {
            throw new InvalidParameterException("Cycle négatif");
        }
        planning
            .computeIfAbsent(cycle, c -> new HashMap<>())
            .put(m, dose);
    }

    public void mettreAJourConcentrations(int cycle) throws InvalidDoseException {
        // desaparición de los medicamentos existentes
        for (Medicament m : medicaments) {
            m.evoluer();
        }

        // administración de dosis en este ciclo
        Map<Medicament, Double> doses = planning.getOrDefault(cycle, Collections.emptyMap());
        for (Map.Entry<Medicament, Double> e : doses.entrySet()) {
            e.getKey().administrerDose(e.getValue());
        }
    }
}

