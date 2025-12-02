public class ReponseJeune extends ReponseImmunitaire {
    public ReponseJeune(double beta, double f) {
        super(beta, f);
    }

    public void calculerNouvelleActivite(double chargePathogene) {
        double nouvelleActivite = this.activite + (this.reactiviteSysteme * Math.sqrt(chargePathogene))
                - (this.coeffFatigue * this.activite);
        this.activite = Math.max(0, nouvelleActivite);

    }

}