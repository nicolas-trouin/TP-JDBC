package fr.univ_amu.iut.beans;

import java.util.HashSet;
import java.util.Set;

public class AssociationEnseignement {
    private Set<Enseignement> enseignements = new HashSet<>();

    public void creerEnseignement(Module module, Etudiant etudiant, Prof prof){
        Enseignement enseignement = new Enseignement(module, etudiant, prof);
        enseignements.add(enseignement);
    }

    public Set<Enseignement> getEnseignements(Etudiant etudiant){
        Set<Enseignement> result = new HashSet<>();
        for (Enseignement enseignement : enseignements) {
            if(enseignement.getEtudiant().equals(etudiant)){
                result.add(enseignement);
            }
        }
        return result;
    }

    public Set<Enseignement> getEnseignements(){
        return this.enseignements;
    }
}
