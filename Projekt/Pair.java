package Projekt;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Pair reprezentuje parę dwóch łańcuchów znaków (Stringów).
 * Implementuje interfejs Serializable, co umożliwia zapisywanie i odczytywanie obiektów tej klasy do i z plików.
 */
public class Pair implements Serializable {
    private static final long serialVersionUID = 1L;
    // Pierwszy element pary
    private final String first;
    // Drugi element pary
    private final String second;

    /**
     * Konstruktor klasy Pair, tworzy nową parę na podstawie dwóch łańcuchów znaków.
     *
     * @param first  pierwszy element pary
     * @param second drugi element pary
     */
    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Metoda equals umożliwia porównywanie obiektów klasy Pair. Dwie pary są równe,
     * gdy ich pierwsze i drugie elementy są takie same.
     *
     * @param o obiekt do porównania
     * @return zwraca prawdę, jeśli obiekty są równe, w przeciwnym razie fałsz
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair pair = (Pair) obj;
        if (first != null ? !first.equals(first) : first != null) return false;
        return second != null ? second.equals(second) : second == null;
    }


    /**
     * Metoda hashCode zwraca hashcode obiektu, który jest wykorzystywany przy przechowywaniu obiektu w strukturach
     * danych wykorzystujących haszowanie, takich jak HashMap czy HashSet.
     *
     * @return wartość hashCode obiektu
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
