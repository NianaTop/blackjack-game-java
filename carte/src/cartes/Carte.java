package cartes;

public class Carte {

    private String valeur,couleur;

    public Carte(String valeur, String couleur) {
        this.valeur=valeur;
        this.couleur=couleur;
    }

    public String getCouleur(){
        return this.couleur;
    }

    public String getValeur(){
        return this.valeur;
    }

    public void setCouleur(String c){
        couleur=c;
    }

    public void setValeur(String v){
        valeur=v;
    }
    
    @Override
    public String toString(){
        return this.valeur+" de "+this.couleur;
    }


}

