public class Node<V> {
    public V current;
    Node<V> next;

    public Node(V current) {
        this.current = current;
        this.next = null;
    }

}
