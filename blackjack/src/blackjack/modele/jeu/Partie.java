/**
 * Package contenant les classes de jeu du Blackjack.
 */
package blackjack.modele.jeu;

import cartes.*;
import blackjack.modele.joueurs.*;
import java.util.*;

/**
 * Implémentation concrète d'une partie de Blackjack.
 * Gère le déroulement complet d'une partie avec joueur humain, croupier et IA.
 */
public class Partie implements Jeu{
    private Paquet pioche;
    private JoueurHumain joueur;
    private JoueurAleatoire croupier;
    private List<JoueurIA> joueursIA;
    private boolean enCours;
    private boolean mancheEnCours;
    
    /**
     * Constructeur d'une nouvelle partie.
     * @param nombreIA Le nombre de joueurs IA à inclure.
     */
    public Partie(int nombreIA) {
        this.pioche = PaquetFactory.creerPaquet52();
        this.pioche.melanger();
        this.joueur = new JoueurHumain("Joueur", 200.0);
        this.croupier = new JoueurAleatoire();
        
        this.joueursIA = new ArrayList<>();
        for (int i = 0; i < nombreIA; i++) {
            joueursIA.add(new JoueurIA("IA-" + (i + 1)));
        }
        
        this.enCours = true;
        this.mancheEnCours = false;
    }
    
    /**
     * Démarre une nouvelle manche.
     */
    public void demarrerNouvelleManche() {
        if (joueur.getSolde() < 10) {
            enCours = false;
            return;
        }
        
        joueur.miser(10.0);
        viderToutesLesMains();
        
        if (pioche.getPaquet().size() < 15) {
            recreerPioche();
        }
        
        distribuerCartesInitiales();
        mancheEnCours = true;
    }
    
    /**
     * Vide toutes les mains des joueurs.
     */
    private void viderToutesLesMains() {
        joueur.viderMain();
        croupier.viderMain();
        for (JoueurIA ia : joueursIA) {
            ia.viderMain();
        }
    }
    
    /**
     * Recrée la pioche en ajoutant un nouveau paquet mélangé.
     */
    private void recreerPioche() {
        Paquet neuf = PaquetFactory.creerPaquet52();
        neuf.melanger();
        while (!neuf.est_vide()) {
            pioche.ajouter(neuf.piocher());
        }
        pioche.melanger();
    }
    
    /**
     * Distribue 2 cartes à chaque joueur.
     */
    private void distribuerCartesInitiales() {
        for (int i = 0; i < 2; i++) {
            joueur.prendreCarte(pioche.piocher());
            croupier.prendreCarte(pioche.piocher());
            for (JoueurIA ia : joueursIA) {
                ia.prendreCarte(pioche.piocher());
            }
        }
    }
    
    /**
     * Le joueur humain tire une carte.
     */
    public void joueurTireCarte() {
        if (mancheEnCours && !pioche.est_vide()) {
            joueur.prendreCarte(pioche.piocher());
        }
    }
    
    /**
     * Le joueur humain décide de s'arrêter.
     */
    public void joueurStop() {
        if (mancheEnCours) {
            jouerTourIA();
            jouerTourCroupier();
            determinerResultat();
            mancheEnCours = false;
        }
    }

    /**
     * Calcule le résultat de la manche et met à jour les soldes.
     */
    public void calculerResultat() {
        if (mancheEnCours) {
            determinerResultat();
            mancheEnCours = false;
        }
    }
    
    /**
     * Fait jouer tous les joueurs IA.
     */
    private void jouerTourIA() {
        for (JoueurIA ia : joueursIA) {
            while (ia.decider() && !pioche.est_vide()) {
                ia.prendreCarte(pioche.piocher());
            }
        }
    }
    
    /**
     * Détermine le résultat de la manche et met à jour les soldes.
     */
    private void determinerResultat() {
        int scoreJoueur = joueur.getScore();
        int scoreCroupier = croupier.getScore();
        
        if (scoreJoueur > 21) {
            joueur.perdre();
        } else if (scoreCroupier > 21) {
            joueur.gagner(2.0);
        } else if (scoreJoueur > scoreCroupier) {
            joueur.gagner(2.0);
        } else if (scoreJoueur < scoreCroupier) {
            joueur.perdre();
        } else {
            joueur.egalite();
        }
    }

    /**
     * Fait jouer le tour du croupier selon les règles officielles.
     */
    public void jouerTourCroupier() {
        while (croupier.getScore() < 17 && !pioche.est_vide()) {
            croupier.prendreCarte(pioche.piocher());
        }
    }

    // IMPLÉMENTATION DES MÉTHODES DE L'INTERFACE 
    
    public boolean estEnCours() { return enCours; }
    public boolean mancheEstEnCours() { return mancheEnCours; }
    public boolean joueurPeutTirer() { return mancheEnCours && !pioche.est_vide(); }
    public boolean joueurASaute() { return joueur.getScore() > 21; }
    public boolean joueurABlackjack() { return joueur.getScore() == 21 && joueur.getMain().getPaquet().size() == 2; }
    
    public int getScoreJoueur() { return joueur.getScore(); }
    public int getScoreCroupier() { return croupier.getScore(); }
    public double getSoldeJoueur() { return joueur.getSolde(); }
    public Paquet getPioche() { return pioche; }
    public JoueurHumain getJoueur() { return joueur; }
    public JoueurAleatoire getCroupier() { return croupier; }
    public List<JoueurIA> getJoueursIA() { return joueursIA; }

    public Paquet getMainJoueur() { return joueur.getMain(); }
    public Paquet getMainCroupier() { return croupier.getMain(); }
    public List<Paquet> getMainsIA() {
        List<Paquet> mains = new ArrayList<>();
        for (JoueurIA ia : joueursIA) {
            mains.add(ia.getMain());
        }
        return mains;
    }
    
    /**
     * Modifie le solde du joueur (ajoute 10).
     */
    public void setSolde(){this.joueur.setSolde();}
}