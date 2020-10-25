package fr.univ_amu.iut.beans;

public class Notation {
    private Etudiant etudiant;
    private Module module;
    private float moyCC;
    private float moyTest;

    public float getMoyCC() {
        return moyCC;
    }

    public void setMoyCC(float moyCC) {
        this.moyCC = moyCC;
    }

    public float getMoyTest() {
        return moyTest;
    }

    public void setMoyTest(float moyTest) {
        this.moyTest = moyTest;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString(){
        return "Notation [moy_test=" + this.getMoyTest() + ", moy_cc=" + this.getMoyCC() + "]";
    }
}
