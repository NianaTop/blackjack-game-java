/**
 * Package contenant les classes de joueurs pour le jeu Blackjack.
 */
package blackjack.modele.joueurs;

import cartes.Carte;
import cartes.Paquet;
import blackjack.*;
import blackjack.modele.Regles;

/**
 * Classe abstraite représentant un joueur générique dans le jeu Blackjack.
 * Un joueur possède un nom et une main de cartes.
 */
public abstract class Joueur {
    protected String nom;
    protected Paquet main;

    /**
     * Constructeur d'un joueur.
     * @param nom Le nom du joueur.
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.main = new Paquet(null);
    }

    /**
     * Méthode abstraite pour décider si le joueur veut tirer une carte.
     * @return true si le joueur veut tirer une carte, false sinon.
     */
    public abstract boolean decider();

    /**
     * Ajoute une carte à la main du joueur.
     * @param c La carte à ajouter (ignorée si null).
     */
    public void prendreCarte(Carte c) {
        if (c != null) {
            this.main.ajouter(c);
        }
    }

    /**
     * Vide la main du joueur en retirant toutes les cartes.
     */
    public void viderMain() {
        while (!this.main.est_vide()) {
            this.main.retirer();
        }
    }

    /**
     * Calcule le score actuel de la main du joueur selon les règles du Blackjack.
     * @return Le score du joueur.
     */
    public int getScore() {
        return Regles.getScore(this.main);
    }

    /**
     * Retourne la main du joueur.
     * @return La main du joueur.
     */
    public Paquet getMain() {
        return main;
    }

    /**
     * Retourne le nom du joueur.
     * @return Le nom du joueur.
     */
    public String getNom() {
        return nom;
    }
}