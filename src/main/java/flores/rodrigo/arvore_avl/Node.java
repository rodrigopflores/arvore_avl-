package flores.rodrigo.arvore_avl;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
public class Node {
    private Integer key;
    private Node left;
    private Node right;

    public Node(Integer key) {
        this.key = key;
    }

    public boolean isGreaterThan(Node node) {
        return key.compareTo(node.getKey()) > 0;
    }

    public boolean contains(Integer key) {
        return this.key.equals(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key.equals(node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
