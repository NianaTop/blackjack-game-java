/**
 * Package contenant le modèle principal du jeu Blackjack.
 */
package blackjack.modele;

import cartes.*;

/**
 * Représente une main de joueur dans le jeu Blackjack.
 * Hérite de Paquet avec un constructeur spécifique.
 */
public class Main extends Paquet {

    /**
     * Constructeur d'une main vide.
     */
    public Main() {
        super(null);
    }
}