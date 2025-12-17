/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.Ecouteur;
import cartes.Paquet;
import javax.swing.*;
import java.awt.*;

/**
 * Classe abstraite représentant une vue générique d'un paquet de cartes.
 * Implémente l'interface Ecouteur pour être notifiée des changements du modèle.
 */
public abstract class VuePaquet extends JPanel implements Ecouteur {

    protected Paquet paquet;

    /**
     * Constructeur d'une vue de paquet.
     * @param p Le paquet de cartes à afficher.
     */
    public VuePaquet(Paquet p) {
        this.paquet = p;
        setPreferredSize(new Dimension(400, 150));
        setBackground(new Color(0, 100, 0)); // Fond vert de table de blackjack
    }
   
    /**
     * Met à jour l'affichage lorsque le modèle change.
     * @param source L'objet source du changement.
     */
    public void modeleMiseAjour(Object source) {
        repaint();
    }
}