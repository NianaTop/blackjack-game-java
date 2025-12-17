/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Composant graphique affichant un paquet de cartes caché (face cachée).
 * Représente généralement la pioche.
 */
public class VuePaquetCache extends VuePaquet {

    /**
     * Constructeur d'une vue pour un paquet caché.
     * @param p Le paquet de cartes à afficher.
     */
    public VuePaquetCache(Paquet p) {
       super(p);
    }

    /**
     * Méthode de peinture personnalisée qui affiche le paquet face cachée.
     * Les cartes sont superposées avec un léger décalage.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largeur = 60;
        int hauteur = 90;
        int decalage = 2;

        int nbCartes = (paquet.getTypePaquet()==TypePaquet.PAQUET_32) ? 32: 52;

        int totalLargeur = largeur + (nbCartes - 1) * decalage;
        int x = Math.max(10, (getWidth() - totalLargeur) / 2);
        int y = (getHeight() - hauteur) / 2;

        for (int i = 0; i < nbCartes; i++) {
            int xi = x + i * decalage;

            g.setColor(Color.WHITE);
            g.fillRect(xi, y, largeur, hauteur);

            g.setColor(Color.BLACK);
            g.drawRect(xi, y, largeur, hauteur);

            g.setColor(Color.RED);
            g.drawLine(xi + 10, y + 10, xi + largeur - 10, y + hauteur - 10);
            g.drawLine(xi + largeur - 10, y + 10, xi + 10, y + hauteur - 10);
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