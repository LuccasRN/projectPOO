public abstract class Pathogene {
    protected double tauxReplication;
    protected double sensibiliteImmunitaire;
    protected double rm;
    protected double beta;
    protected double Lt;

    public Pathogene(double tauxReplication, double sensibiliteImmunitaire,
            double rm, double beta) {
        this.tauxReplication = tauxReplication;
        this.sensibiliteImmunitaire = sensibiliteImmunitaire;
        this.rm = rm;
        this.beta = beta;
        this.Lt = 0;
    }

    public abstract void evoluer(double It, Medicament medicament);

    public abstract void diminuerReponseImmunitaire(Patient patient);

    public double getTauxReplication() {
        return tauxReplication;
    }

    public double getSensibiliteImmunitaire() {
        return sensibiliteImmunitaire;
    }

    public double getRm() {
        return rm;
    }

    public double getBeta() {
        return beta;
    }

    public double getLt() {
        return Lt;
    }

    public void setLt(double Lt) {
        this.Lt = Math.max(0, Lt);
    }
}
