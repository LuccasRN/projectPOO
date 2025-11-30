package simulation;

public class AgentAgressif extends AgentPathogene {

    public AgentAgressif(String nom, double L0, double tauC, double alphaI) {
        super(nom, L0, tauC, alphaI);
    }

    @Override
    protected double termeReplication() {
        double L = getChargeInfectieuse();
        return super.termeReplication() * L; // τc * L^2
    }

    @Override
    public boolean estAgressif() {
        return true;
    }
}

