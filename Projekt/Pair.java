package Projekt;

import java.io.Serializable;
import java.util.Objects;

public class Pair implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String first;
    private final String second;

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first &&
                second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
