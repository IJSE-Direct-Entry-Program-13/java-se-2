public class Demo3 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello")
                .append("IJSE")
                .insert(5, " ")
                .replace(0, 5, "Hi").append("!");
        System.out.println(sb.toString());
    }
}
