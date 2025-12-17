/**
 * Package principal de l'application Blackjack.
 */
package blackjack;

import blackjack.modele.jeu.Partie;
import blackjack.vue.*;
import blackjack.controleur.Controleur;
import cartes.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale de l'application Blackjack.
 * Point d'entrée du programme, initialise l'interface graphique et lance le jeu.
 */
public class MainClass {
    
    /**
     * Point d'entrée principal de l'application.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            int nombreIA = demanderNombreIA();
            Partie partie = new Partie(nombreIA);
            
            VuePaquetDecorator decorateur = new VuePaquetDecorator();
            VueCommandes vueCommandes = new VueCommandes();
            VuePaquetVisible vueMainJoueur = new VuePaquetVisible(partie.getMainJoueur());
            VueCroupier vueMainCroupier = new VueCroupier(partie.getMainCroupier());
            
            List<VuePaquetVisible> vuesIA = new ArrayList<>();
            List<Paquet> mainsIA = partie.getMainsIA();
            for (int i = 0; i < mainsIA.size(); i++) {
                VuePaquetVisible vueIA = new VuePaquetVisible(mainsIA.get(i));
                vuesIA.add(vueIA);
                decorateur.ajouterVuePaquet(vueIA);
            }
            VuePaquetCache vuePioche = new VuePaquetCache(partie.getPioche());

            decorateur.ajouterVuePaquet(vueMainJoueur);
            decorateur.ajouterVuePaquet(vueMainCroupier);
            decorateur.ajouterVuePaquet(vuePioche);
            
            List<VuePaquet> vuesIACast = new ArrayList<>(vuesIA);
            VueTable vueTable = new VueTable(vueMainCroupier, vuePioche, vueMainJoueur, vuesIACast);
            
            new Controleur(partie, vueCommandes, decorateur, vueMainCroupier);
            
            // Calcul de la taille de la fenêtre en fonction du nombre d'IA
            int hauteurBase = 600;
            int hauteurSupplement = nombreIA * 60;
            
            JFrame frame = new JFrame("Blackjack - " + nombreIA + " IA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, hauteurBase + hauteurSupplement);
            frame.setLayout(new BorderLayout());
            
            // Titre
            JLabel titre = new JLabel("BLACKJACK", SwingConstants.CENTER);
            titre.setFont(new Font("Arial", Font.BOLD, 24));
            titre.setForeground(Color.WHITE);
            titre.setOpaque(true);
            titre.setBackground(new Color(0, 80, 0));
            titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(new Color(0, 80, 0));
            header.add(titre, BorderLayout.CENTER);
            
            frame.add(header, BorderLayout.NORTH);
            frame.add(vueTable, BorderLayout.CENTER);
            frame.add(vueCommandes, BorderLayout.SOUTH);
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    /**
     * Demande à l'utilisateur le nombre de joueurs IA.
     * @return Le nombre de joueurs IA choisi (0-3).
     */
    private static int demanderNombreIA() {
        Object[] options = {"0", "1", "2", "3"};
        int choix = JOptionPane.showOptionDialog(
            null,
            "Combien de joueurs IA voulez-vous ?",
            "Configuration du jeu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        if (choix >= 0 && choix <= 3) {
            return choix;
        }
        return 0;
    }
}