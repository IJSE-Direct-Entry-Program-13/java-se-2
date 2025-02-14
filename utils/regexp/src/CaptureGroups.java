import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptureGroups {
    public static void main(String[] args) {
        String text = """
                First student kasun: 011-1234567, Galle and
                second student details nuwan: 056-4567891, Panadura and
                Third place student ruwan: 033-7894561, Matara and
                Last place supun: 077-1551234, Moratuwa have been selected
                """;

        String pattern = "(?<name>[A-Z][a-z]+):\\s(?<contact>(?<area>0\\d{2})-\\d{7}),\\s(?<address>[A-Z][A-z]+)";

        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(text);
        while(matcher.find()) {
//            System.out.println(text.substring(matcher.start(), matcher.end()));
//            String name = matcher.group(1);
            String name = matcher.group("name");
//            String contact = matcher.group(2);
            String contact = matcher.group("contact");
//            String areaCode = matcher.group(3);
            String areaCode = matcher.group("area");
//            String address = matcher.group(4);
            String address = matcher.group("address");
            System.out.println("Name: " + name);
            System.out.println("Contact: " + contact);
            System.out.println("AreaCode: " + areaCode);
            System.out.println("Address: " + address);
            System.out.println("-----------------");
        }
    }
}
