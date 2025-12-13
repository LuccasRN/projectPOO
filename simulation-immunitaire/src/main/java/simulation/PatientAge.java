package simulation;

public class PatientAge extends Patient {
    public PatientAge(double coefficientFatigue) {
        super(coefficientFatigue);
    }

    @Override
    public void evoluerReponseImmunitaire() {
        for (Pathogene p : pathogenes) {
            double It = p.getReponseImmu();
            double Lt_next = p.getLt();
            double beta = p.getBeta();
            
            double It_next = It + (beta * Lt_next) - (coefficientFatigue * Math.pow(It, 2));
            p.setReponseImmu(Math.max(0, It_next));
        }
    }
}
