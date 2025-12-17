/**
 * Package contenant les classes de joueurs pour le jeu Blackjack.
 */
package blackjack.modele.joueurs;

import cartes.Paquet;
import cartes.Carte;
import java.util.List;

/**
 * Représente un joueur IA avec une stratégie intelligente.
 * Prend des décisions basées sur le score actuel, la présence d'As et le nombre de cartes.
 */
public class JoueurIA extends Joueur {
    
    /**
     * Constructeur d'un joueur IA.
     * @param nom Le nom de l'IA.
     */
    public JoueurIA(String nom) {
        super(nom);
    }
    
    /**
     * Décide si l'IA doit tirer une carte.
     * Implémente une stratégie sophistiquée basée sur le score et la composition de la main.
     * @return true si l'IA doit tirer une carte, false sinon.
     */
    @Override
    public boolean decider() {
        int score = getScore();
        
        // Si score > 21, on a déjà sauté
        if (score > 21) {
            return false;
        }
        
        // Vérifier si on a un As dans la main
        boolean asDansMain = false;
        int nombreAs = 0;
        
        for (Carte carte : getMain().getPaquet()) {
            if (carte.getValeur() == "As") {
                asDansMain = true;
                nombreAs++;
            }
        }
        
        // Calculer le score "souple" (avec As = 11)
        int scoreSouple = score;
        if (asDansMain && score <= 11) {
            scoreSouple = score + 10; // On compte un As comme 11
        }
        
        // STRATÉGIE SIMPLE MAIS INTELLIGENTE
        
        // 1. Si on a un blackjack (21 avec 2 cartes)
        if (scoreSouple == 21 && getMain().getPaquet().size() == 2) {
            return false; // On reste
        }
        
        // 2. Si on a un score souple de 19-21, on reste
        if (scoreSouple >= 19) {
            return false;
        }
        
        // 3. Si on a un score dur de 17-18, on reste
        if (score >= 17) {
            return false;
        }
        
        // 4. Si on a un As et un score souple de 18, on reste
        if (asDansMain && scoreSouple == 18) {
            return false;
        }
        
        // 5. Si on a un score ≤ 11, on tire toujours
        if (score <= 11) {
            return true;
        }
        
        // 6. Pour les scores 12-16, stratégie selon le nombre de cartes
        if (score >= 12 && score <= 16) {
            // Si on a beaucoup de cartes, on est plus prudent
            if (getMain().getPaquet().size() >= 4) {
                return false; // Beaucoup de cartes, on s'arrête
            }
            
            // Si on a un As (main souple), on peut être plus agressif
            if (asDansMain) {
                return scoreSouple < 18; // Tire jusqu'à 17 souple
            }
            
            // Score dur 12-16 : tire sauf si on a exactement 12-13 avec peu de cartes
            return score < 14 || getMain().getPaquet().size() < 3;
        }
        
        // Par défaut, on tire
        return true;
    }
    
    /**
     * Fait jouer l'IA avec des pauses pour un effet visuel.
     * @param pioche La pioche depuis laquelle tirer des cartes.
     */
    public void jouer(Paquet pioche) {  
        // Simulation réaliste avec pauses
        while (decider() && !pioche.est_vide()) {
            prendreCarte(pioche.piocher());
            
            // Petite pause pour que l'humain puisse voir
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Version optimisée qui joue tout son tour d'un coup (sans pauses).
     * @param pioche La pioche depuis laquelle tirer des cartes.
     */
    public void jouerTourComplet(Paquet pioche) {
        // Prendre une décision finale
        while (decider() && !pioche.est_vide()) {
            prendreCarte(pioche.piocher());
        }
    }
}