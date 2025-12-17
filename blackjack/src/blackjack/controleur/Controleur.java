/**
 * Package contenant le contrôleur de l'application Blackjack.
 */
package blackjack.controleur;

import blackjack.modele.jeu.Partie;
import blackjack.modele.joueurs.*;
import blackjack.vue.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Contrôleur principal de l'application Blackjack.
 * Gère les interactions entre la vue et le modèle.
 */
public class Controleur implements ActionListener{
    private Partie partie;
    private VueCommandes vueCommandes;
    private VuePaquetDecorator decorateur;
    private VueCroupier vueCroupier;
    
    /**
     * Constructeur du contrôleur.
     * @param partie La partie à contrôler.
     * @param vueCommandes La vue des commandes.
     * @param decorateur Le décorateur des vues de paquets.
     * @param vueCroupier La vue de la main du croupier.
     */
    public Controleur(Partie partie, VueCommandes vueCommandes, 
                     VuePaquetDecorator decorateur, VueCroupier vueCroupier){
        this.partie = partie;
        this.vueCommandes = vueCommandes;
        this.decorateur = decorateur;
        this.vueCroupier = vueCroupier;
        vueCommandes.getBtnTirer().addActionListener(this);
        vueCommandes.getBtnRester().addActionListener(this);
        vueCommandes.getBtnRejouer().addActionListener(this);
        
        demarrerManche();
    }
    
    /**
     * Gère les actions sur les boutons.
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vueCommandes.getBtnTirer()){
            onTirer();
        } else if (e.getSource() == vueCommandes.getBtnRester()){
            onRester();
        } else if (e.getSource() == vueCommandes.getBtnRejouer()){
            onRejouer();
        }
    }
    
    /**
     * Gère l'action "Tirer une carte".
     */
    private void onTirer() {
        if (partie.joueurPeutTirer()) {
            partie.joueurTireCarte();
            mettreAJourAffichage();
            
            if (partie.joueurASaute()) {
                finirManche("PERDU ! Vous avez dépassé 21 (" + partie.getScoreJoueur() + ")");
                revelerCartesCroupier();
            } else {
                vueCommandes.afficherMessage("A vous ! Score : " + partie.getScoreJoueur());
            }
        }
    }
    
    /**
     * Gère l'action "Rester/Arrêter".
     */
    private void onRester() {
        vueCommandes.desactiverBoutonsJeu();
        
        // D'abord, les IA jouent
        for (JoueurIA ia : partie.getJoueursIA()) {
            ia.jouer(partie.getPioche()); 
            mettreAJourAffichage(); 
        }
        
        revelerCartesCroupier();
        partie.jouerTourCroupier();
        partie.calculerResultat();
        
        afficherResultat();
        vueCommandes.activerBoutonRejouer(true);
        mettreAJourAffichage();
    }
    
    /**
     * Gère l'action "Rejouer une manche".
     */
    private void onRejouer() {
        if (partie.estEnCours()) {
            demarrerManche();
        }
    }
    
    /**
     * Démarre une nouvelle manche.
     */
    private void demarrerManche() {
        partie.demarrerNouvelleManche();
        
        if (!partie.estEnCours()) {
            vueCommandes.afficherMessage("Solde insuffisant ! Game Over.");
            vueCommandes.desactiverTousBoutons();
            return;
        }
        
        vueCroupier.cacherCarte();
        vueCommandes.activerBoutonsJeu(true);
        vueCommandes.activerBoutonRejouer(false);
        
        if (partie.joueurABlackjack()) {
            vueCommandes.afficherMessage("BLACKJACK ! Attente du croupier");
            revelerCartesCroupier();
            partie.jouerTourCroupier();
            partie.calculerResultat();
            
            afficherResultat();
            vueCommandes.desactiverBoutonsJeu();
            vueCommandes.activerBoutonRejouer(true);
        } else {
            vueCommandes.afficherMessage("A vous ! Score : " + partie.getScoreJoueur());
        }
        
        mettreAJourAffichage();
    }
    
    /**
     * Termine la manche en cours.
     * @param message Le message à afficher.
     */
    private void finirManche(String message) {
        partie.calculerResultat();
        vueCommandes.afficherMessage(message);
        vueCommandes.desactiverBoutonsJeu();
        vueCommandes.activerBoutonRejouer(true);
        mettreAJourAffichage();
    }
    
    /**
     * Affiche les résultats de la manche.
     */
    private void afficherResultat() {
        int scoreJoueur = partie.getScoreJoueur();
        int scoreCroupier = partie.getScoreCroupier();
        double soldeJoueur = partie.getSoldeJoueur();

        // Message pour le joueur
        String messageJoueur = "VOUS : " + scoreJoueur + " points";
        if (scoreJoueur > 21){
            messageJoueur += "\nPERDU - Vous avez dépassé 21 !";
        } else if (partie.joueurABlackjack()){
            messageJoueur += "\nBLACKJACK !";
        }
        
        // Message pour le croupier
        String messageCroupier = "CROUPIER : " + scoreCroupier + " points";
        if (scoreCroupier > 21){
            messageCroupier += "\nCroupier a dépassé 21 !";
        }
        
        // Résultat final
        String resultatFinal;
        if (scoreJoueur > 21){
            resultatFinal = "VOUS AVEZ PERDU";
        } else if (scoreCroupier > 21){
            resultatFinal = "VOUS AVEZ GAGNÉ!";
        } else if (scoreJoueur > scoreCroupier){
            resultatFinal = "VOUS AVEZ GAGNÉ! (" + scoreJoueur + " > " + scoreCroupier + ")";
        } else if (scoreJoueur < scoreCroupier){
            resultatFinal = "VOUS AVEZ PERDU (" + scoreJoueur + " < " + scoreCroupier + ")";
        } else{
            resultatFinal = "ÉGALITÉ";
        }
        
        // Données pour les IA
        List<Integer> scoresIA = new ArrayList<>();
        List<String> resultatsIA = new ArrayList<>();
        
        List<JoueurIA> joueursIA = partie.getJoueursIA();
        if (!joueursIA.isEmpty()) {
            for (int i = 0; i < joueursIA.size(); i++){
                JoueurIA ia = joueursIA.get(i);
                int scoreIA = ia.getScore();
                scoresIA.add(scoreIA);
                
                String resultatIA;
                if (scoreIA > 21){
                    resultatIA = "PERDU (a dépassé 21)";
                } else if (scoreCroupier > 21){
                    resultatIA = "GAGNÉ (croupier a dépassé 21)";
                } else if (scoreIA > scoreCroupier){
                    resultatIA = "GAGNÉ";
                } else if (scoreIA < scoreCroupier){
                    resultatIA = "PERDU";
                } else{
                    resultatIA = "ÉGALITÉ";
                }
                resultatsIA.add(resultatIA);
            }
        }
        
        JFrame framePrincipale = null;
        if (vueCommandes != null){
            framePrincipale = (JFrame) SwingUtilities.getWindowAncestor(vueCommandes);
        }
        
        // Pop-up des résultats
        VueResultat popupResultat = new VueResultat(
            framePrincipale,
            scoreJoueur,
            scoreCroupier,
            partie.joueurABlackjack(),
            scoresIA,
            resultatsIA,
            soldeJoueur,
            messageJoueur,
            messageCroupier,
            resultatFinal
        );
        
        popupResultat.afficher();
        
        String messageSimple = "Manche terminée. Solde: " + 
                              String.format("%.1f", soldeJoueur) + " €";
        vueCommandes.afficherMessage(messageSimple);
    }
    
    /**
     * Révèle les cartes du croupier.
     */
    private void revelerCartesCroupier(){
        vueCroupier.revelerCarte();
    }
    
    /**
     * Met à jour tous les éléments d'affichage.
     */
    private void mettreAJourAffichage(){
        vueCommandes.mettreAJourSolde(partie.getSoldeJoueur());
        decorateur.modeleMiseAjour(null);
    }
}