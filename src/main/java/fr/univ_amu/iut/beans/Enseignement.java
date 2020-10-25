package fr.univ_amu.iut.beans;

import java.util.Objects;

public class Enseignement{
    private Module module;
    private Etudiant etudiant;
    private Prof prof;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Enseignement(Module module, Etudiant etudiant, Prof prof){
        this.module = module;
        this.etudiant = etudiant;
        this.prof = prof;
    }

    @Override
    public String toString() {
        return "Enseignement [module : " + module + " ; "
                + "etudiant : " + etudiant + " ; "
                + "prof : " + prof + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignement enseignement = (Enseignement) o;
        return Objects.equals(module, enseignement.module) &&
               Objects.equals(etudiant, enseignement.etudiant) &&
               Objects.equals(prof, enseignement.prof);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, etudiant, prof);
    }
}
