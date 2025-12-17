/**
 * Package contenant les classes de joueurs pour le jeu Blackjack.
 */
package blackjack.modele.joueurs;

/**
 * Représente un joueur humain dans le jeu Blackjack.
 * Gère également le solde et les mises du joueur.
 */
public class JoueurHumain extends Joueur {
    private double solde;
    private double miseActuelle;

    /**
     * Constructeur d'un joueur humain.
     * @param nom Le nom du joueur.
     * @param soldeDepart Le solde initial du joueur.
     */
    public JoueurHumain(String nom, double soldeDepart) {
        super(nom);
        this.solde = soldeDepart;
    }

    /**
     * Place une mise.
     * @param montant Le montant à miser.
     */
    public void miser(double montant) {
        if (montant <= solde) {
            this.miseActuelle = montant;
            this.solde -= montant;
        }
    }

    /**
     * Ajoute les gains au solde du joueur.
     * @param ratio Le ratio de gain (ex: 2.0 pour doubler la mise).
     */
    public void gagner(double ratio) {
        this.solde += this.miseActuelle * ratio;
        this.miseActuelle = 0;
    }

    /**
     * Rembourse la mise en cas d'égalité.
     */
    public void egalite() {
        this.solde += this.miseActuelle; 
        this.miseActuelle = 0;
    }
    
    /**
     * Le joueur perd sa mise.
     */
    public void perdre() {
        this.miseActuelle = 0;
    }

    /**
     * Retourne le solde actuel du joueur.
     * @return Le solde du joueur.
     */
    public double getSolde() { return solde; }
    
    /**
     * Modifie le solde du joueur (ajoute 10).
     */
    public void setSolde() { solde+=10; }
    
    /**
     * Le joueur humain ne décide pas automatiquement.
     * @return Toujours false car la décision est prise via l'interface.
     */
    @Override
    public boolean decider() {
        return false; 
    }
}