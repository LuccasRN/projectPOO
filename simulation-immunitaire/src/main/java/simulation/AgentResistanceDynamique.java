package simulation;

public class AgentResistanceDynamique extends AgentPathogene {

    public AgentResistanceDynamique(String nom,
                                    double L0,
                                    double tauC,
                                    double alphaI) {
        super(nom, L0, tauC, alphaI);
    }

    @Override
    public boolean aResistanceDynamique() {
        return true;
    }
}
