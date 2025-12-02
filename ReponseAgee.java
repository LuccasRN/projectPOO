public class ReponseAgee extends ReponseImmunitaire {
    public ReponseAgee(double beta, double f) {
        super(beta, f);
    }

    public void calculerNouvelleActivite(double chargePathogene) {
        double nouvelleActivite = this.activite + (this.reactiviteSysteme * chargePathogene)
                - (this.coeffFatigue * (this.activite * this.activite));
        this.activite = Math.max(0, nouvelleActivite);
    }

}