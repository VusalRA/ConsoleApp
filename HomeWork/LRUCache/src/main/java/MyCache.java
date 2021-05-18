public class MyCache<V> {


    private static int count = 0;
    private final int size;
    private Node<V> first;
    private Node<V> last;

    public MyCache(int size) {
        this.size = size;
    }

    public void add(V item) {
        Node<V> newNode = new Node<>(item);
        if (this.first == null) {
            this.first = newNode;
            this.last = newNode;
            count++;
            return;
        }
        if (checkSize()) {
            first = first.next;
        } else {
            count++;
        }
        last.next = newNode;
        last = newNode;
    }

    private Node<V> loop(int index) {
        Node<V> node = this.first;
        while (index > 1) {
            node = node.next;
            index--;
        }
        return node;
    }

    private boolean checkSize() {
        if (size == count) {
            return true;
        }
        return false;
    }

    private boolean checkEmpty() {
        if (this.first == null) {
            System.out.println("Empty");
            return true;
        } else {
            return false;
        }
    }

    public V getElement(int index) {
        if (checkEmpty()) {
            return null;
        }
        if (index < 0 && index > size) {
            return null;
        }
        Node<V> node = loop(index);
        return node.current;
    }


    public void printAllNode() {
        Node<V> node = this.first;
        while (node != null) {
            System.out.println(node.current);
            node = node.next;
        }
    }
}
