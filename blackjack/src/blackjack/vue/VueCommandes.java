/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau de commandes contenant les boutons d'action et les informations du jeu.
 */
public class VueCommandes extends JPanel {

    private JButton btnTirer;
    private JButton btnRester;
    private JButton btnRejouer;
    private JLabel labelInfo;
    private JLabel labelSolde;

    /**
     * Constructeur du panneau de commandes.
     */
    public VueCommandes() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelInfo = new JLabel("Bienvenue !");
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
   
        JPanel panelBoutons = new JPanel(new FlowLayout());
        
        btnTirer = new JButton("TIRER");
        btnRester = new JButton("RESTER");
        btnRejouer = new JButton("JOUER (10€)");
        btnRejouer.setBackground(new Color(50, 150, 255));
        btnRejouer.setForeground(Color.WHITE);

        panelBoutons.add(btnTirer);
        panelBoutons.add(btnRester);
        panelBoutons.add(btnRejouer);

        labelSolde = new JLabel("Solde : ?");
        labelSolde.setForeground(new Color(0, 100, 0));

        this.add(labelInfo, BorderLayout.NORTH);
        this.add(panelBoutons, BorderLayout.CENTER);
        this.add(labelSolde, BorderLayout.EAST);
        
        // Désactive les boutons de jeu au départ
        btnTirer.setEnabled(false);
        btnRester.setEnabled(false);
    }

    /**
     * Affiche un message d'information.
     * @param message Le message à afficher.
     */
    public void afficherMessage(String message) {
        labelInfo.setText(message);
    }
    
    /**
     * Met à jour l'affichage du solde.
     * @param solde Le nouveau solde à afficher.
     */
    public void mettreAJourSolde(double solde) {
        labelSolde.setText(String.format("Solde : %.1f €", solde));
    }
    
    /**
     * Active ou désactive les boutons de jeu.
     * @param actif true pour activer, false pour désactiver.
     */
    public void activerBoutonsJeu(boolean actif) {
        btnTirer.setEnabled(actif);
        btnRester.setEnabled(actif);
    }
    
    /**
     * Désactive les boutons de jeu.
     */
    public void desactiverBoutonsJeu() {
        btnTirer.setEnabled(false);
        btnRester.setEnabled(false);
    }
  
    /**
     * Active ou désactive le bouton rejouer.
     * @param actif true pour activer, false pour désactiver.
     */
    public void activerBoutonRejouer(boolean actif) {
        btnRejouer.setEnabled(actif);
    }
    
    /**
     * Désactive tous les boutons.
     */
    public void desactiverTousBoutons() {
        btnTirer.setEnabled(false);
        btnRester.setEnabled(false);
        btnRejouer.setEnabled(false);
    }
    
    /**
     * Réinitialise l'affichage à l'état initial.
     */
    public void reinitialiser() {
        labelInfo.setText("Bienvenue !");
        labelSolde.setText("Solde : ?");
        btnTirer.setEnabled(false);
        btnRester.setEnabled(false);
        btnRejouer.setEnabled(true);
    }
    
    /**
     * Prépare l'interface pour une nouvelle manche.
     */
    public void preparerNouvelleManche() {
        btnTirer.setEnabled(true);
        btnRester.setEnabled(true);
        btnRejouer.setEnabled(false);
    }
    
    /**
     * Prépare l'interface pour la fin d'une manche.
     */
    public void preparerFinManche() {
        btnTirer.setEnabled(false);
        btnRester.setEnabled(false);
        btnRejouer.setEnabled(true);
    }

    // Getters pour les composants
    
    /**
     * Retourne le bouton "Tirer".
     * @return Le bouton "Tirer".
     */
    public JButton getBtnTirer() { return btnTirer; }
    
    /**
     * Retourne le bouton "Rester".
     * @return Le bouton "Rester".
     */
    public JButton getBtnRester() { return btnRester; }
    
    /**
     * Retourne le bouton "Rejouer".
     * @return Le bouton "Rejouer".
     */
    public JButton getBtnRejouer() { return btnRejouer; }
    
    /**
     * Retourne le label d'information.
     * @return Le label d'information.
     */
    public JLabel getLabelInfo() { return labelInfo; }
    
    /**
     * Retourne le label du solde.
     * @return Le label du solde.
     */
    public JLabel getLabelSolde() { return labelSolde; }
}