package edu.centrale.tp4medev;

import java.util.List;

public class Dame extends Pion{
    public Dame(){
        super();
    }

    /**
     * Constructeur d'une nouvelle Dame (pour la couleur : 1 = blanc, 0 = noir).
     * @param couleur Couleur de la dame créée (1 = blanc, 0 = noir)
     */
    public Dame(boolean couleur) {
        super(couleur);
    }

    /**
     * Teste si le déplacement d'une case à une autre est possible, pour une dame.
     * @param i Case du pion qu'on cherche à déplacer.
     * @param f Case où on cherche à déplacer le pion.
     * @param p Plateau sur lequel on évolue.
     * @return Vrai si le déplacement est possible, faux sinon.
     */
    public boolean deplace_possible(Case i, Case f, List<List<Case>> p) {

        if (f.getPion() == null) {
            if (i.getX()+i.getY() == f.getX()+f.getY()) {
                // Cas d'une diagonale /
                if (i.getX()<=f.getX()) {
                    for (int j=i.getX()+1 ; j<f.getX() ; j++) {
                        if (p.get(j).get(i.getX()+i.getY()-j).getPion() != null) {
                            if (p.get(j).get(i.getX()+i.getY()-j).getPion().getCouleur()==i.getPion().getCouleur()) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int j=f.getX()+1 ; j<i.getX() ; j++) {
                        if (p.get(j).get(i.getX()+i.getY()-j).getPion() != null) {
                            if (p.get(j).get(i.getX()+i.getY()-j).getPion().getCouleur()==i.getPion().getCouleur()) {
                                return false;
                            }
                        }
                    }
                }
                return false;
            } else if(i.getX()-i.getY() == f.getX()-f.getY()){
                // Cas d'une diagonale \
                if (i.getX()<=f.getX()) {
                    for (int j=i.getX()+1 ; j<f.getX() ; j++) {
                        if (p.get(j).get(j-i.getX()+i.getY()).getPion() != null) {
                            if (p.get(j).get(j-i.getX()+i.getY()).getPion().getCouleur()==i.getPion().getCouleur()) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int j=f.getX()+1 ; j<i.getX() ; j++) {
                        if (p.get(j).get(j-i.getX()+i.getY()).getPion() != null) {
                            if (p.get(j).get(j-i.getX()+i.getY()).getPion().getCouleur()==i.getPion().getCouleur()) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Permet de manger le pion de la case passée en paramètre.
     * @param c Case sur laquelle on passe manger (qu'il y ait un pion ou pas).
     */
    @Override
    public void manger(Case c) {
        if (c.getPion() != null){
            c.setPion(null);
        }
    }

    /**
     * Déplace le pion d'une case donnée vers une autre case.
     * @param i Case du pion qu'on cherche à déplacer.
     * @param f Case où on cherche à déplacer le pion.
     * @param p Plateau sur lequel on évolue.
     */
    @Override
    public void deplacer(Case i, Case f, Plateau pla) {

        List<List<Case>> p = pla.getPlateau();

        if (deplace_possible(i,f,p)) {
            if (i.getX()+i.getY() == f.getX()+f.getY()) {
                // Cas d'une diagonale /
                if (i.getX()<=f.getX()) {
                    for (int j=i.getX()+1 ; j<f.getX() ; j++) {
                        this.manger(p.get(j).get(i.getX()+i.getY()-j));
                    }
                } else {
                    for (int j=f.getX()+1 ; j<i.getX() ; j++) {
                        this.manger(p.get(j).get(i.getX()+i.getY()-j));
                    }
                }
            } else if(i.getX()-i.getY() == f.getX()-f.getY()){
                // Cas d'une diagonale \
                if (i.getX()<=f.getX()) {
                    for (int j=i.getX()+1 ; j<f.getX() ; j++) {
                        this.manger(p.get(j).get(j-i.getX()+i.getY()));
                    }
                } else {
                    for (int j=f.getX()+1 ; j<i.getX() ; j++) {
                        this.manger(p.get(j).get(j-i.getX()+i.getY()));
                    }
                }
            }
        } else {
            System.out.println("Le déplacement n'est pas possible.");
            System.out.println(" ");
        }
    }
}
