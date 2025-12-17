/**
 * Package définissant les interfaces du jeu Blackjack.
 */
package blackjack.modele.jeu;

import blackjack.modele.joueurs.*;
import cartes.*;
import java.util.List;

/**
 * Interface définissant le contrat pour une partie de Blackjack.
 * Spécifie toutes les méthodes nécessaires pour gérer une partie.
 */
public interface Jeu {
    
    /**
     * Démarre une nouvelle manche de jeu.
     */
    void demarrerNouvelleManche();
    
    /**
     * Vérifie si la partie est en cours.
     * @return true si la partie est en cours, false sinon.
     */
    boolean estEnCours();
    
    /**
     * Vérifie si la manche actuelle est en cours.
     * @return true si la manche est en cours, false sinon.
     */
    boolean mancheEstEnCours();
    
    // ACTIONS DU JOUEUR 
    
    /**
     * Le joueur humain tire une carte de la pioche.
     */
    void joueurTireCarte();
    
    /**
     * Le joueur humain décide de s'arrêter (ne plus tirer de cartes).
     */
    void joueurStop();
    
    // ACCÈS AUX JOUEURS
    
    /**
     * Retourne le joueur humain.
     * @return Le joueur humain.
     */
    JoueurHumain getJoueur();
    
    /**
     * Retourne le croupier.
     * @return Le croupier.
     */
    JoueurAleatoire getCroupier();
    
    /**
     * Retourne la liste des joueurs IA.
     * @return La liste des joueurs IA.
     */
    List<JoueurIA> getJoueursIA();
    
    // ACCÈS AUX PAQUETS 
    
    /**
     * Retourne la pioche.
     * @return La pioche.
     */
    Paquet getPioche();
    
    /**
     * Retourne la main du joueur humain.
     * @return La main du joueur humain.
     */
    Paquet getMainJoueur();
    
    /**
     * Retourne la main du croupier.
     * @return La main du croupier.
     */
    Paquet getMainCroupier();
    
    /**
     * Retourne la liste des mains des joueurs IA.
     * @return La liste des mains des IA.
     */
    List<Paquet> getMainsIA();
    
    // SCORES ET SOLDE 
    
    /**
     * Retourne le score du joueur humain.
     * @return Le score du joueur humain.
     */
    int getScoreJoueur();
    
    /**
     * Retourne le score du croupier.
     * @return Le score du croupier.
     */
    int getScoreCroupier();
    
    /**
     * Retourne le solde du joueur humain.
     * @return Le solde du joueur humain.
     */
    double getSoldeJoueur();
    
    /**
     * Vérifie si le joueur peut tirer une carte.
     * @return true si le joueur peut tirer une carte, false sinon.
     */
    boolean joueurPeutTirer();
    
    /**
     * Vérifie si le joueur a sauté (score > 21).
     * @return true si le joueur a sauté, false sinon.
     */
    boolean joueurASaute();
    
    /**
     * Vérifie si le joueur a un blackjack (21 avec 2 cartes).
     * @return true si le joueur a un blackjack, false sinon.
     */
    boolean joueurABlackjack();
    
    /**
     * Modifie le solde du joueur (méthode spécifique).
     */
    void setSolde();
    
    /**
     * Fait jouer le tour du croupier selon les règles officielles
     * (tire jusqu'à atteindre au moins 17).
     */
    void jouerTourCroupier();
}