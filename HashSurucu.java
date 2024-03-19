
public class HashSurucu {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(1, "One");
        hashMap.put(22, "Two");
        hashMap.put(35, "Three");
        hashMap.put(28, "yirmi sekiz");
        System.out.println("HashMap after initial entries:");
        System.out.println(hashMap);
        System.out.println(hashMap.size());
        hashMap.put(46, "Four");
        hashMap.put(58, "One");
        hashMap.put(60, "Two");
        hashMap.put(91, "Three");
        System.out.println(hashMap.size());

        System.out.println("HashMap after resize:");
        System.out.println(hashMap);

        System.out.println("\nValue for key 2: " + hashMap.get(2));
        System.out.println("\nValue for key 91: " + hashMap.get(91));


        System.out.println(hashMap.size());
        hashMap.put(102, "bir sifir iki");
        hashMap.remove(60);
        System.out.println("\nHashMap after removing key 60:");
        System.out.println(hashMap);
        System.out.println((int) Math.ceil(8 / 2.0));


    }


}