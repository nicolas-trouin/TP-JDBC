package fr.univ_amu.iut.beans;

import java.io.Serializable;
import java.util.Objects;

public class Etudiant implements Serializable {
    private int numEt;
    private String nomEt;
    private String prenomEt;
    private String cpEt;
    private String villeEt;
    private int annee;
    private int groupe;

    public Etudiant() {
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getCpEt() {
        return cpEt;
    }

    public void setCpEt(String cpEt) {
        this.cpEt = cpEt;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public String getNomEt() {
        return nomEt;
    }

    public void setNomEt(String nomEt) {
        this.nomEt = nomEt;
    }

    public int getNumEt() {
        return numEt;
    }

    public void setNumEt(int numEt) {
        this.numEt = numEt;
    }

    public String getPrenomEt() {
        return prenomEt;
    }

    public void setPrenomEt(String prenomEt) {
        this.prenomEt = prenomEt;
    }

    public String getVilleEt() {
        return villeEt;
    }

    public void setVilleEt(String villeEt) {
        this.villeEt = villeEt;
    }

    @Override
    public String toString() {
        return "Etudiant [numEt=" + numEt + ", "
                + (nomEt != null ? "nomEt=" + nomEt + ", " : "")
                + (prenomEt != null ? "prenomEt=" + prenomEt + ", " : "")
                + (cpEt != null ? "cpEt=" + cpEt + ", " : "")
                + (villeEt != null ? "villeEt=" + villeEt + ", " : "")
                + "annee=" + annee + ", groupe=" + groupe + ", "
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return numEt == etudiant.numEt &&
                annee == etudiant.annee &&
                groupe == etudiant.groupe &&
                Objects.equals(nomEt, etudiant.nomEt) &&
                Objects.equals(prenomEt, etudiant.prenomEt) &&
                Objects.equals(cpEt, etudiant.cpEt) &&
                Objects.equals(villeEt, etudiant.villeEt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numEt, nomEt, prenomEt, cpEt, villeEt, annee, groupe);
    }
}
