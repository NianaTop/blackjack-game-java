package cartes;

import java.util.Arrays;
import java.util.List;

public class PaquetFactory {

    private static final List<String> HAUTEURS_32 = Arrays.asList(
        "7", "8", "9", "10", "Valet", "Dame", "Roi", "As"
    );

    private static final List<String> HAUTEURS_52 = Arrays.asList(
        "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As"
    );

    private static final List<String> COULEURS = Arrays.asList(
        "Trèfle", "Carreau", "Coeur", "Pique"
    );

    public static Paquet creerPaquet32() {
        Paquet paquet = new Paquet(TypePaquet.PAQUET_32);
        for (String couleur : COULEURS) {
            for (String hauteur : HAUTEURS_32) {
                paquet.ajouter(new Carte(hauteur, couleur));
            }
        }
        return paquet;
    }

    public static Paquet creerPaquet52() {
        Paquet paquet = new Paquet(TypePaquet.PAQUET_52);
        for (String couleur : COULEURS) {
            for (String hauteur : HAUTEURS_52) {
                paquet.ajouter(new Carte(hauteur, couleur));
            }
        }
        return paquet;
    }
}