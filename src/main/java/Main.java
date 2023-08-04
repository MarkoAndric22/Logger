public class Main {
    public static void main(String[] args) {
        String a = "aaa";
        String b = "bbb";

        System.out.println("aaa"=="aaa");
        System.out.println("aaa"==new String("aaa"));
        System.out.println("aaa".equals("aaa"));
        System.out.println("aaa".equals(new String("aaa")));
    }
}
