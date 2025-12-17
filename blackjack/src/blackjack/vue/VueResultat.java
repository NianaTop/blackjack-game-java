/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Fenêtre modale affichant les résultats détaillés d'une manche de Blackjack.
 * Affiche les scores, les résultats et le nouveau solde du joueur.
 */
public class VueResultat extends JDialog{
    
    /**
     * Constructeur de la fenêtre de résultats.
     * @param parent La fenêtre parente.
     * @param scoreJoueur Le score du joueur humain.
     * @param scoreCroupier Le score du croupier.
     * @param joueurABlackjack Indique si le joueur a un blackjack.
     * @param scoresIA Liste des scores des joueurs IA.
     * @param resultatsIA Liste des résultats textuels des IA.
     * @param soldeJoueur Le nouveau solde du joueur.
     * @param messageJoueur Le message détaillé pour le joueur.
     * @param messageCroupier Le message détaillé pour le croupier.
     * @param resultatFinal Le résultat final de la manche.
     */
    public VueResultat(JFrame parent, int scoreJoueur, int scoreCroupier, boolean joueurABlackjack, List<Integer> scoresIA, List<String> resultatsIA,
                      double soldeJoueur, String messageJoueur, String messageCroupier, String resultatFinal){ 
        
        super(parent, "Résultat de la manche", true);
        
        setLayout(new BorderLayout());
        setSize(450, 400);
        setLocationRelativeTo(parent);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Titre
        JLabel titre = new JLabel("FIN DE LA MANCHE");
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(titre);
        
        // Joueur
        panel.add(createSection("VOUS", messageJoueur, scoreJoueur > 21, joueurABlackjack));
        panel.add(Box.createVerticalStrut(10));
        
        // Croupier
        panel.add(createSection("CROUPIER", messageCroupier, scoreCroupier > 21, false));
        panel.add(Box.createVerticalStrut(15));
        
        // Résultat final
        JPanel panelResultat = new JPanel();
        panelResultat.setLayout(new BoxLayout(panelResultat, BoxLayout.Y_AXIS));
        panelResultat.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panelResultat.setBackground(new Color(240, 240, 240));
        
        JLabel labelResultat = new JLabel(resultatFinal);
        labelResultat.setFont(new Font("Arial", Font.BOLD, 16));
        
        if (resultatFinal.contains("GAGNÉ")){
            labelResultat.setForeground(new Color(0, 100, 0)); 
        } else if (resultatFinal.contains("PERDU")){
            labelResultat.setForeground(Color.RED);
        } else {
            labelResultat.setForeground(Color.ORANGE);
        }
        
        labelResultat.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelResultat.add(Box.createVerticalStrut(10));
        panelResultat.add(labelResultat);
        panelResultat.add(Box.createVerticalStrut(10));
        
        panel.add(panelResultat);
        panel.add(Box.createVerticalStrut(15));
        
        // Résultats des joueurs IA
        if (scoresIA != null && !scoresIA.isEmpty()){
            panel.add(createIAPanel(scoresIA, resultatsIA));
            panel.add(Box.createVerticalStrut(10));
        }
        
        // Solde
        JLabel labelSolde = new JLabel("Nouveau solde : " + String.format("%.1f", soldeJoueur) + " €");
        labelSolde.setFont(new Font("Arial", Font.BOLD, 14));
        labelSolde.setForeground(new Color(0, 100, 0));
        labelSolde.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelSolde);
        
        // Bouton OK
        JButton btnOK = new JButton("OK");
        btnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOK.addActionListener(e -> dispose());
        
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnOK);
        
        add(panel, BorderLayout.CENTER);
    }
    
    /**
     * Crée un panneau pour une section (joueur ou croupier).
     * @param titre Le titre de la section.
     * @param message Le message à afficher.
     * @param aSaute Indique si le joueur a sauté (score > 21).
     * @param blackjack Indique si le joueur a un blackjack.
     * @return Le panneau créé.
     */
    private JPanel createSection(String titre, String message, boolean aSaute, boolean blackjack){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(titre));
        
        String[] lignes = message.split("\n");
        for (String ligne : lignes) {
            JLabel label = new JLabel(ligne);
            
            if (ligne.contains("BLACKJACK") || ligne.contains("GAGNÉ")){
                label.setForeground(Color.GREEN);
                label.setFont(new Font("Arial", Font.BOLD, 12));
            } else if (ligne.contains("PERDU") || ligne.contains("a dépassé 21")){
                label.setForeground(Color.RED);
                label.setFont(new Font("Arial", Font.ITALIC, 12));
            } else {
                label.setFont(new Font("Arial", Font.PLAIN, 12));
            }
            
            panel.add(label);
        }
        
        return panel;
    }
    
    /**
     * Crée un panneau pour afficher les résultats des IA.
     * @param scoresIA Liste des scores des IA.
     * @param resultatsIA Liste des résultats textuels des IA.
     * @return Le panneau créé.
     */
    private JPanel createIAPanel(List<Integer> scoresIA, List<String> resultatsIA){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Résultats des IA"));
        
        for (int i = 0; i < scoresIA.size(); i++){
            int scoreIA = scoresIA.get(i);
            String resultatIA = resultatsIA.get(i);
            
            JPanel panelIA = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel labelIA = new JLabel("IA" + (i+1) + " : " + scoreIA + " points - " + resultatIA);
            labelIA.setFont(new Font("Arial", Font.PLAIN, 12));
            
            if (resultatIA.contains("GAGNÉ")){
                labelIA.setForeground(Color.GREEN);
            } else if (resultatIA.contains("PERDU")){
                labelIA.setForeground(Color.RED);
            } else {
                labelIA.setForeground(Color.ORANGE);
            }
            
            panelIA.add(labelIA);
            panel.add(panelIA);
        }
        
        return panel;
    }
    
    /**
     * Affiche la fenêtre de résultats.
     */
    public void afficher(){
        setVisible(true);
    }
}