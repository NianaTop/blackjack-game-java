/**
 * Package contenant les classes de vue pour l'interface graphique du Blackjack.
 */
package blackjack.vue;

import cartes.*;
import java.awt.*;

/**
 * Composant graphique affichant la main du croupier avec une carte cachée.
 * La deuxième carte est cachée jusqu'à la fin du tour des joueurs.
 */
public class VueCroupier extends VuePaquetVisible{
 
    private boolean carteCachee = true;
    
    /**
     * Constructeur de la vue du croupier.
     * @param p La main du croupier à afficher.
     */
    public VueCroupier(Paquet p){
        super(p);
    }

    /**
     * Cache la deuxième carte du croupier.
     */
    public void cacherCarte(){
        this.carteCachee = true;
        repaint();
    }

    /**
     * Révèle la deuxième carte du croupier.
     */
    public void revelerCarte(){
        this.carteCachee = false;
        repaint();
    }

    /**
     * Méthode de peinture personnalisée qui cache/révèle la deuxième carte.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        int largeur = 60;
        int hauteur = 90;
        int espacement = 15; 
        int nbCartes = paquet.getPaquet().size();
        
        int totalLargeur = nbCartes * (largeur + espacement) - espacement;
        int x = Math.max(10, (getWidth() - totalLargeur) / 2);
        int y = (getHeight() - hauteur) / 2;

        for (int i = 0; i < nbCartes; i++) {
            int xi = x + i * (largeur + espacement);
        
            // Si c'est la 2ème carte (index 1) et qu'elle doit être cachée
            if (i == 1 && carteCachee) {
                // Style pour carte cachée
                g.setColor(Color.WHITE);
                g.fillRect(xi, y, largeur, hauteur);
                g.setColor(Color.BLACK);
                g.drawRect(xi, y, largeur, hauteur);
                g.setColor(Color.RED);
                g.drawLine(xi + 10, y + 10, xi + largeur - 10, y + hauteur - 10);
                g.drawLine(xi + largeur - 10, y + 10, xi + 10, y + hauteur - 10);
        
            } else {
                // Style pour carte visible
                Carte carte = paquet.getCarte(i);
                g.setColor(Color.WHITE);
                g.fillRect(xi, y, largeur, hauteur);

                String valeur = carte.getValeur();
                String couleur = carte.getCouleur();
                
                if (couleur.equals("Pique ") || couleur.equals("Trèfle"))
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
    }
}