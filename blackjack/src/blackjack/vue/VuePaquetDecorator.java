/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import cartes.*;
import javax.swing.*;

/**
 * Décorateur pour gérer plusieurs vues de paquets.
 * Permet de mettre à jour toutes les vues simultanément.
 */
public class VuePaquetDecorator extends JPanel {
    protected List<VuePaquet> vuePaquets;

    /**
     * Constructeur du décorateur.
     */
    public VuePaquetDecorator() {
        this.vuePaquets = new ArrayList<>();
        this.setOpaque(false); // Transparent
    }

    /**
     * Ajoute une vue de paquet au décorateur.
     * @param vuePaquet La vue à ajouter.
     */
    public void ajouterVuePaquet(VuePaquet vuePaquet) {
        this.vuePaquets.add(vuePaquet);
    }

    /**
     * Méthode de peinture (héritée de JPanel).
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Met à jour toutes les vues de paquets.
     * @param source L'objet source du changement.
     */
    public void modeleMiseAjour(Object source) {
        for (VuePaquet vue : vuePaquets) {
            vue.modeleMiseAjour(source); 
        }
        this.repaint(); 
    }

    /**
     * Retourne la liste des vues de paquets.
     * @return La liste des vues.
     */
    public List<VuePaquet> getVuePaquets() {
        return vuePaquets;
    }
}