package fr.univ_amu.iut.beans;


import java.util.HashSet;
import java.util.Set;

public class AssociationNotation {
    private Set<Lien> liens = new HashSet<>();
    private AssociationNotation instance;

    public void creerLien(Module module, Etudiant etudiant, Notation notation){
        Lien lien = new Lien(module, etudiant);
        lien.setNotation(notation);
        liens.add(lien);
    }

    public void supprimerLien(Module module, Etudiant etudiant){
        Lien tmpLien = new Lien(module, etudiant);
        for (int i = 0; i < liens.size(); i++) {
            if(liens.toArray()[i].equals(tmpLien)){
                liens.remove(liens.toArray()[i]);
            }
        }
    }

    public void supprimerLien(Lien lien){
        liens.remove(lien);
    }

    public Lien getLien(Module module, Etudiant etudiant){
        Lien tmpLien = new Lien(module, etudiant);
        for (int i = 0; i < liens.size(); i++) {
            if(liens.toArray()[i].equals(tmpLien)){
                return (Lien) liens.toArray()[i];
            }
        }
        return null;
    }

    public Set<Lien> getLiens(Etudiant etudiant){
        Set<Lien> result = null;
        for (int i = 0; i < liens.size(); i++) {
            Lien tmpLien = (Lien) liens.toArray()[i];
            if(tmpLien.getEtudiant().equals(etudiant)){
                result.add(tmpLien);
            }
        }
        return result;
    }

    public Set<Lien> getLiens(Module module){
        Set<Lien> result = new HashSet<>();
        for (Lien lien : liens) {
            if(lien.getModule().equals(module)){
                result.add(lien);
            }
        }
        return result;
    }

    public Set<Lien> getLiens(){
        return liens;
    }

    public Set<Module> getModules(Etudiant etudiant){
        Set<Module> result = null;
        for (int i = 0; i < liens.size(); i++) {
            Lien tmpLien = (Lien) liens.toArray()[i];
            if(tmpLien.getEtudiant().equals(etudiant)){
                result.add(tmpLien.getModule());
            }
        }
        return result;
    }

    public Set<Etudiant> getEtudiants(Module module){
        Set<Etudiant> result = null;
        for (int i = 0; i < liens.size(); i++) {
            Lien tmpLien = (Lien) liens.toArray()[i];
            if(tmpLien.getEtudiant().equals(module)){
                result.add(tmpLien.getEtudiant());
            }
        }
        return result;
    }

    public AssociationNotation getInstance() {
        return instance;
    }
}
