package simulation;

public class PatientAdulte extends Patient {
    public PatientAdulte(double coefficientFatigue) {
        super(coefficientFatigue);
    }

    @Override
    public void evoluerReponseImmunitaire() {
        // Utilisation de la Solution 1 : Une réponse par pathogène.
        for (Pathogene p : pathogenes) {
            double It = p.getReponseImmu();
            double Lt_next = p.getLt();
            double beta = p.getBeta();
            
            // It+1 = max(0, It + beta*Lt+1 - f*It)
            double It_next = It + (beta * Lt_next) - (coefficientFatigue * It);
            p.setReponseImmu(Math.max(0, It_next));
        }
    }
}
