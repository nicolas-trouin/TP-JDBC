package fr.univ_amu.iut.beans;

import java.util.Objects;

public class Lien {
    private Module module;
    private Etudiant etudiant;
    private Notation notation;

    public Lien(Module module, Etudiant etudiant){
        this.module = module;
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Notation getNotation() {
        return notation;
    }

    public void setNotation(Notation notation) {
        this.notation = notation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lien lien = (Lien) o;
        return Objects.equals(this.module, lien.module) &&
               Objects.equals(this.etudiant, lien.etudiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, etudiant, notation);
    }

    @Override
    public String toString(){
        return "Lien [ module : " + this.getModule() + " ; etudiant : " + this.getEtudiant() + " ; notation : " + this.getNotation() + "] ;";
    }



}
