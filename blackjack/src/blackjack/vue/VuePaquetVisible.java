/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.*;
import javax.swing.*;
import java.awt.*;

/**
 * Composant graphique affichant un paquet de cartes visible (toutes les cartes face visible).
 * Hérite de VuePaquet et implémente un affichage personnalisé des cartes.
 */
public class VuePaquetVisible extends VuePaquet {

    /**
     * Constructeur d'une vue pour un paquet visible.
     * @param p Le paquet de cartes à afficher.
     */
    public VuePaquetVisible(Paquet p) {
       super(p);
    }

    /**
     * Méthode de peinture personnalisée qui affiche toutes les cartes du paquet.
     * Les cartes sont affichées avec leur valeur et couleur, avec un code couleur
     * (rouge pour cœur/carreau, noir pour pique/trèfle).
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largeur = 60;
        int hauteur = 90;
        int espacement = 15; 
        int nbCartes = paquet.getPaquet().size();
        int totalLargeur = nbCartes * (largeur + espacement) - espacement;
        int x = Math.max(10, (getWidth() - totalLargeur) / 2);
        int y = (getHeight() - hauteur) / 2;

        for (int i = 0; i < nbCartes; i++) {
            int xi = x + i * (largeur + espacement);
            Carte carte = paquet.getCarte(i);

            g.setColor(Color.WHITE);
            g.fillRect(xi, y, largeur, hauteur);

            String valeur = carte.getValeur();
            String couleur = carte.getCouleur();
            
            if (couleur.equals("Pique") || couleur.equals("Trèfle"))
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.RED);
  
            g.drawRect(xi, y, largeur, hauteur);

            g.setColor(Color.BLACK);
           
            g.setFont(new Font("Arial", Font.BOLD, 10));
            g.drawString(valeur, xi + 10, y + 25);
            g.drawString(couleur, xi + 10, y + 50);
        }
    }
    
    /**
     * Met à jour l'affichage lorsque le modèle change.
     * @param source L'objet source du changement.
     */
    @Override
    public void modeleMiseAjour(Object source) {
        repaint();
    }
}