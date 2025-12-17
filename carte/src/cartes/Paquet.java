package cartes;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Paquet extends AbstractModeleEcoutable {

    private List<Carte> paquet;
    private TypePaquet typePaquet;

    public Paquet(TypePaquet typePaquet) {
        this.typePaquet = typePaquet;
        this.paquet = new ArrayList<>();
    }

    public List<Carte> getPaquet() {
        return this.paquet;
    }

    @Override
    public String toString() {
        return "" + this.paquet;
    }

    public TypePaquet getTypePaquet() {
        return typePaquet;
    }

    public void melanger() {
        Collections.shuffle(this.paquet);
        fireChange();
    }

    public void coupe() {
        if (paquet.size() < 2) return;

        Random random = new Random();
        int index = random.nextInt(paquet.size());

        List<Carte> partie1 = new ArrayList<>(paquet.subList(0, index));
        List<Carte> partie2 = new ArrayList<>(paquet.subList(index, paquet.size()));

        paquet.clear();
        paquet.addAll(partie2);
        paquet.addAll(partie1);

        fireChange();
    }

    public Carte piocher() {
        if (paquet.isEmpty()) return null;
        
        Carte c = paquet.remove(0);
        fireChange();
        return c;
    }

    public void ajouter(Carte carte) {
        paquet.add(carte);
        fireChange();
    }

    public void retirer(Carte carte) {
        paquet.remove(carte);
        fireChange();
    }

    public void retirer() {
        if (!paquet.isEmpty()) {
            paquet.remove(0);
            fireChange();
        }
    }

    public boolean est_vide() {
        return paquet.isEmpty();
    }

    public Carte getCarte(int i) {
        if (i >= 0 && i < paquet.size()) {
            return paquet.get(i);
        }
        return null;
    }
}