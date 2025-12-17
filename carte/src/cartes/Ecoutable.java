package cartes;

/**
 * Interface définissant le contrat pour les objets écoutables (observables) dans le modèle.
 * Permet à des écouteurs de s'enregistrer pour être notifiés des changements.
 *  @author TOP
 */
public interface Ecoutable {
    /**
     * Enregistre un nouvel écouteur qui sera notifié des changements de l'objet observable.
     * @param e l'écouteur à enregistrer (ne doit pas être null)
     */
    public void ajouterEcouteur(Ecouteur e);
}