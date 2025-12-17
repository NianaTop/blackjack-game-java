/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.*;
import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale historique du Blackjack (classe de compatibilité).
 * Cette classe est conservée pour la compatibilité mais n'est plus utilisée
 * dans la version actuelle de l'application.
 */
public class VueBlackjack extends JFrame {
    private Paquet paquet;
    private Paquet main;
    private VuePaquet vueCache;
    private VuePaquet vueVisible;
    private VuePaquetDecorator decorateur;
    
    /**
     * Constructeur historique de la fenêtre principale.
     * @param paquet La pioche.
     * @param main La main du joueur.
     * @param vueCache Vue du paquet caché.
     * @param vueVisible Vue du paquet visible.
     * @param decorateur Le décorateur de vues.
     */
    public VueBlackjack(Paquet paquet, Paquet main, VuePaquet vueCache, VuePaquet vueVisible, VuePaquetDecorator decorateur) {
        this.paquet = paquet;
        this.main = main;
        this.vueCache = vueCache;
        this.vueVisible = vueVisible;
        this.decorateur = decorateur;
        
        initialiserInterface();
    }
    
    /**
     * Initialise l'interface graphique historique.
     */
    private void initialiserInterface() {
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); 

        setLayout(new BorderLayout());
        
        decorateur.ajouterVuePaquet(vueVisible);
        decorateur.ajouterVuePaquet(vueCache);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
        panelPrincipal.add(vueVisible);
        panelPrincipal.add(vueCache);
        panelPrincipal.add(decorateur);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        setVisible(true);
    }
}