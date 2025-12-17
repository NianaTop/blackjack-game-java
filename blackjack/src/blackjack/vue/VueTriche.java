/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.Paquet;
import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre affichant le contenu de la pioche en mode "triche".
 * Permet de voir toutes les cartes de la pioche face visible.
 */
public class VueTriche extends JFrame {

    VuePaquetVisible vuePioche;
    
    /**
     * Constructeur de la fenêtre de triche.
     * @param pioche La pioche à afficher.
     */
    public VueTriche(Paquet pioche) {
        super("Pioche (Vue Triche)");
        
        this.setSize(600, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        this.vuePioche = new VuePaquetVisible(pioche);
        
        JPanel content = new JPanel(new FlowLayout());
        content.add(new JLabel("Contenu de la pioche :"));
        content.add(vuePioche);

        this.add(new JScrollPane(content));
    }

    /**
     * Retourne la vue de la pioche.
     * @return La VuePaquetVisible représentant la pioche.
     */
    public VuePaquetVisible getPioche(){return vuePioche;}
}