package cartes;
import java.util.*;

/**
 * Classe abstraite de base implémentant le patron Observateur pour les modèles écoutables.
 * Fournit une implémentation basique de la gestion des écouteurs.
 *  @author TOP
 */
public abstract class AbstractModeleEcoutable implements Ecoutable {
    /**
     * Liste des écouteurs enregistrés
     */
    List<Ecouteur> ecouteurs;

    /**
     * Constructeur initialisant la liste des écouteurs
     */
    public AbstractModeleEcoutable() {
        ecouteurs = new ArrayList<Ecouteur>();
    }

    /**
     * Ajoute un écouteur à la liste des écouteurs
     * @param e L'écouteur à ajouter
     */
    public void ajouterEcouteur(Ecouteur e) {
        this.ecouteurs.add(e);
    }

    /**
     * Retire un écouteur de la liste des écouteurs
     * @param e L'écouteur à retirer
     */
    public void retraitEcouteur(Ecouteur e) {
        this.ecouteurs.remove(e);
    }
   

    protected void fireChange()
    {
        for (Ecouteur e : ecouteurs)
            e.modeleMiseAjour(this);
    }

    
}