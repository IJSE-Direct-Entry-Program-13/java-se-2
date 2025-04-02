public class Demo2 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int x = 0;
        while (x++ < 5) {
            sb.append(x).append("-");
        }
        System.out.println(sb.toString());
    }
}
