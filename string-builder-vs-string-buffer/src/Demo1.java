public class Demo1 {

    public static void main(String[] args) {
        System.out.println("String Builder vs. String Buffer");

        // A
        String s1 = "hello";
        String s2 = "world";
        String s3 = s1 + s2 + "!";
        System.out.println(s3);

        // B
        String s4 = "";
        int x = 0;
        while (x++ < 5){
            s4 = s4 + x + "-";
        }
        System.out.println(s4);
    }
}
