/**
 * Package contenant le modèle principal du jeu Blackjack.
 */
package blackjack.modele;

import cartes.*;
import java.util.*;

/**
 * Proxy pour la main d'un joueur qui restreint certaines opérations.
 * Empêche les opérations inappropriées comme mélanger ou couper une main.
 */
public class ProxyMain extends Paquet {
    private Main main;

    /**
     * Constructeur du proxy de main.
     */
    public ProxyMain() {
        super(null);
        this.main = new Main(); 
    }

    /**
     * Empêche de mélanger une main.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public void melanger() {
        throw new UnsupportedOperationException("Impossible de mélanger une main de joueur");
    }

    /**
     * Empêche de couper une main.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public void coupe() {
        throw new UnsupportedOperationException("Impossible de couper une main de joueur");
    }

    /**
     * Pioche une carte de la main.
     * @return La carte piochée.
     */
    @Override
    public Carte piocher() {
        return main.piocher();
    }

    /**
     * Ajoute une carte à la main.
     * @param carte La carte à ajouter.
     */
    @Override
    public void ajouter(Carte carte) {
        main.ajouter(carte);
    }

    /**
     * Retourne la liste des cartes de la main.
     * @return La liste des cartes.
     */
    @Override
    public List<Carte> getPaquet(){
        return main.getPaquet();
    }

    /**
     * Retire une carte spécifique de la main.
     * @param carte La carte à retirer.
     */
    @Override
    public void retirer(Carte carte) {
        main.retirer(carte);
    }

    /**
     * Retire la dernière carte de la main.
     */
    @Override
    public void retirer() {
        main.retirer();
    }

    /**
     * Vérifie si la main est vide.
     * @return true si la main est vide, false sinon.
     */
    @Override
    public boolean est_vide() {
        return main.est_vide();
    }

    /**
     * Retourne la carte à l'index spécifié.
     * @param i L'index de la carte.
     * @return La carte à l'index i.
     */
    @Override
    public Carte getCarte(int i) {
        return main.getCarte(i);
    }
 
    /**
     * Retourne une représentation textuelle de la main.
     * @return La représentation textuelle.
     */
    @Override
    public String toString() {
        return main.toString();
    }
}