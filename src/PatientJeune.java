public class PatientJeune extends Patient {
    private double beta;

    public PatientJeune(double coefficientFatigue, double beta) {
        super(coefficientFatigue);
        this.beta = beta;
    }

    @Override
    public void evoluerReponseImmunitaire() {
        double chargeTotal = 0;
        for (Pathogene p : pathogenes) {
            chargeTotal += p.getLt();
        }

        double nouvelleActivite = activiteImmunitaire + beta * Math.sqrt(chargeTotal)
                - coefficientFatigue * activiteImmunitaire;
        setActiviteImmunitaire(nouvelleActivite);
    }

    public double getBeta() {
        return beta;
    }
}
