package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patient {
    private final String id;
    private final ReponseImmunitaire reponseImmunitaire;
    private final List<AgentPathogene> agents;
    private final PlanTraitement planTraitement;

    public Patient(String id,
                   ReponseImmunitaire reponseImmunitaire,
                   PlanTraitement planTraitement) {
        this.id = id;
        this.reponseImmunitaire = reponseImmunitaire;
        this.planTraitement = planTraitement;
        this.agents = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void ajouterAgent(AgentPathogene a) {
        agents.add(a);
    }

    public List<AgentPathogene> getAgents() {
        return agents;
    }

    public ReponseImmunitaire getReponseImmunitaire() {
        return reponseImmunitaire;
    }

    public PlanTraitement getPlanTraitement() {
        return planTraitement;
    }

    public void passerUnCycle(int cycle) throws InvalidDoseException {
        // 1. Evolución de las concentraciones de todos los medicamentos
        planTraitement.mettreAJourConcentrations(cycle);

        Map<Medicament, Double> dosesCourantes = new HashMap<>();
        for (Medicament m : planTraitement.getMedicaments()) {
            dosesCourantes.put(m, m.getConcentration());
        }

        // 2. Actualizar patógenos (L_{t+1})
        for (AgentPathogene a : agents) {
            a.mettreAJour(reponseImmunitaire.getI(), dosesCourantes);
        }

        // 3. Calcular L total para respuesta inmune (somme des Lp,t+1)
        double sommeL = agents.stream()
                .mapToDouble(AgentPathogene::getChargeInfectieuse)
                .sum();

        // 4. Actualizar respuesta inmune I_{t+1}
        reponseImmunitaire.mettreAJour(sommeL);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Patient ")
                .append(id)
                .append(" I=")
                .append(reponseImmunitaire.getI())
                .append("\n");
        for (AgentPathogene a : agents) {
            sb.append("  Pathogène ")
              .append(a.getNom())
              .append(" L=")
              .append(a.getChargeInfectieuse())
              .append("\n");
        }
        for (Medicament m : planTraitement.getMedicaments()) {
            sb.append("  Médicament ")
              .append(m.getNom())
              .append(" D=")
              .append(m.getConcentration())
              .append("\n");
        }
        return sb.toString();
    }
}