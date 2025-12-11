public class PatientAge extends Patient {
    private double beta;

    public PatientAge(double coefficientFatigue, double beta) {
        super(coefficientFatigue);
        this.beta = beta;
    }

    @Override
    public void evoluerReponseImmunitaire() {
        double chargeTotal = 0;
        for (Pathogene p : pathogenes) {
            chargeTotal += p.getLt();
        }

        double nouvelleActivite = activiteImmunitaire + beta * chargeTotal
                - coefficientFatigue * activiteImmunitaire * activiteImmunitaire;
        setActiviteImmunitaire(nouvelleActivite);
    }

    public double getBeta() {
        return beta;
    }
}
