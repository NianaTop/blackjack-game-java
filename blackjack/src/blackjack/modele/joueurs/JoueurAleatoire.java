/**
 * Package contenant les classes de joueurs pour le jeu Blackjack.
 */
package blackjack.modele.joueurs;

import java.util.Random;

/**
 * Représente le croupier dans le jeu Blackjack.
 * Le croupier suit une stratégie fixe : tire à 16 ou moins, s'arrête à 17 ou plus.
 */
public class JoueurAleatoire extends Joueur {
    
    private Random random;

    /**
     * Constructeur du croupier.
     */
    public JoueurAleatoire() {
        super("Croupier");
        this.random = new Random();
    }

    /**
     * Décide si le croupier doit tirer une carte selon les règles officielles.
     * @return true si le score est inférieur à 17, false sinon.
     */
    @Override
    public boolean decider() {
        int score = getScore();
        // Le croupier tire à 16 ou moins, s'arrête à 17 ou plus
        return score < 17;
    }
}