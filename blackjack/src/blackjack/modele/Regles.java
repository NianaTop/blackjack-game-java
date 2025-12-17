/**
 * Package contenant le modèle principal du jeu Blackjack.
 */
package blackjack.modele;

import cartes.Carte;
import cartes.Paquet;

/**
 * Classe utilitaire contenant les règles du jeu Blackjack.
 * Fournit des méthodes pour calculer les valeurs des cartes et les scores.
 */
public class Regles {

    /**
     * Retourne la valeur d'une carte selon les règles du Blackjack.
     * @param c La carte dont on veut la valeur.
     * @return La valeur de la carte (As=11, figures=10, autres=leur valeur numérique).
     */
    public static int getValeurCarte(Carte c) {
        String h = c.getValeur();
        if (h.equals("Valet") || h.equals("Dame") || h.equals("Roi")) {
            return 10;
        }
        if (h.equals("As")) {
            return 11;
        }
        try {
            return Integer.parseInt(h);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Calcule le score d'un paquet en gérant les As (soit 1 ou 11).
     * Les As valent 11 par défaut, mais valent 1 si le score dépasse 21.
     * @param p Le paquet dont on veut calculer le score.
     * @return Le score optimal du paquet (≤ 21 si possible).
     */
    public static int getScore(Paquet p) {
        int score = 0;
        int nbAs = 0;

        for (Carte c : p.getPaquet()) {
            int val = getValeurCarte(c);
            score += val;
            if (val == 11) nbAs++;
        }

        // Si on dépasse 21, on transforme les As de 11 en 1
        while (score > 21 && nbAs > 0) {
            score -= 10;
            nbAs--;
        }
        return score;
    }
}