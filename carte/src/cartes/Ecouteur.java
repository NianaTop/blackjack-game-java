package cartes;

/**
 * Interface définissant le contrat pour les écouteurs (observateurs) dans le pattern Observateur.
 * Permet à un objet d'être notifié des changements d'un objet écoutable.
 *  @author TOP
 */
public interface Ecouteur {
    /**
     * Méthode appelée lorsque l'objet écouté notifie un changement.
     * @param source l'objet écoutable qui a déclenché la notification (ne doit pas être null)
     */
    public void modeleMiseAjour(Object source);
}