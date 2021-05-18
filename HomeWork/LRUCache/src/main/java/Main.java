public class Main {
    public static void main(String[] args) {
        MyCache<String> cache = new MyCache<>(3);
        cache.add("Vusal");
        cache.add("Abdullayev");
        cache.add("hello");
        cache.add("there");
        cache.printAllNode();
    }
}
