package simulation;

public class PatientJeune extends Patient {
    public PatientJeune(double coefficientFatigue) {
        super(coefficientFatigue);
    }

    @Override
    public void evoluerReponseImmunitaire() {
        for (Pathogene p : pathogenes) {
            double It = p.getReponseImmu();
            double Lt_next = p.getLt();
            double beta = p.getBeta();
            
            double It_next = It + (beta * Math.sqrt(Lt_next)) - (coefficientFatigue * It);
            p.setReponseImmu(Math.max(0, It_next));
        }
    }
}
