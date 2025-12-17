/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panneau principal représentant la table de Blackjack.
 * Organise l'affichage de tous les joueurs et de la pioche.
 */
public class VueTable extends JPanel{
    
    /**
     * Constructeur principal de la table.
     * @param vueCroupier Vue de la main du croupier.
     * @param vuePioche Vue de la pioche.
     * @param vueJoueur Vue de la main du joueur humain.
     * @param vuesIA Liste des vues des mains des IA.
     */
    public VueTable(VuePaquet vueCroupier, VuePaquet vuePioche, 
                    VuePaquet vueJoueur, List<VuePaquet> vuesIA){
        
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 120, 0)); // Fond vert de table

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        int nbIA = vuesIA.size();
    
        // Panel pioche
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = Math.max(2, nbIA);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        
        JPanel piochePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        piochePanel.setOpaque(false);
        piochePanel.add(vuePioche);
        
        JLabel lblPioche = new JLabel("PIOCHE");
        lblPioche.setForeground(Color.WHITE);
        lblPioche.setFont(new Font("Arial", Font.BOLD, 14));
        lblPioche.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel piocheContainer = new JPanel(new BorderLayout());
        piocheContainer.setOpaque(false);
        piocheContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); 
        piocheContainer.add(lblPioche, BorderLayout.NORTH);
        piocheContainer.add(piochePanel, BorderLayout.CENTER);
        
        this.add(piocheContainer, gbc);

        // Panel croupier
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = Math.max(2, nbIA);
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelCroupier = createPlayerPanel("CROUPIER", vueCroupier, Color.WHITE);
        this.add(panelCroupier, gbc);

        // Panels pour chaque joueur IA
        for (int i = 0; i < nbIA; i++) {
            gbc.gridy = 2;
            gbc.gridx = i;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0 / Math.max(1, nbIA);
            
            JPanel panelIA = createPlayerPanel("IA " + (i+1), vuesIA.get(i), Color.YELLOW);
            this.add(panelIA, gbc);
        }

        // Panel joueur
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = Math.max(2, nbIA);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1.0; 
        
        JPanel panelJoueur = createPlayerPanel("VOUS", vueJoueur, Color.CYAN);
        this.add(panelJoueur, gbc);
    }
    
    /**
     * Crée un panneau pour un joueur.
     * @param title Le titre du panneau (nom du joueur).
     * @param vue La vue du paquet du joueur.
     * @param textColor La couleur du texte.
     * @return Le panneau créé.
     */
    private JPanel createPlayerPanel(String title, VuePaquet vue, Color textColor){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel label = new JLabel(title);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Panel pour les cartes
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        cardPanel.setOpaque(false);
        cardPanel.add(vue);

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 2, true));
        
        container.add(label, BorderLayout.NORTH);
        container.add(cardPanel, BorderLayout.CENTER);
        
        panel.add(container, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Constructeurs de compatibilité
    
    /**
     * Constructeur sans joueurs IA.
     * @param vueCroupier Vue de la main du croupier.
     * @param vuePioche Vue de la pioche.
     * @param vueJoueur Vue de la main du joueur humain.
     */
    public VueTable(VuePaquet vueCroupier, VuePaquet vuePioche, VuePaquet vueJoueur){
        this(vueCroupier, vuePioche, vueJoueur, new java.util.ArrayList<>());
    }
    
    /**
     * Constructeur avec un seul joueur IA.
     * @param vueCroupier Vue de la main du croupier.
     * @param vuePioche Vue de la pioche.
     * @param vueJoueur Vue de la main du joueur humain.
     * @param vueIA Vue de la main de l'IA.
     */
    public VueTable(VuePaquet vueCroupier, VuePaquet vuePioche, 
                    VuePaquet vueJoueur, VuePaquet vueIA){
        this(vueCroupier, vuePioche, vueJoueur, java.util.Arrays.asList(vueIA));
    }
}