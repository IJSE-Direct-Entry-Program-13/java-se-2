import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloRegExp {

    public static void main(String[] args) {
        String text = """
                Kasun's contact number is 011-1234567. And his brother's
                contact number is 077-1234567. BTW his sister's contact is
                +94 99 1234567. His land phone number is 011 1234567. They
                have another land phone number which is +94 11 4561237.
                """;
        //language=RegExp
        String pattern = "((0\\d{2}[- ])|([+]94\\s\\d{2}\\s))\\d{7}";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);

        while(matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
    }
}
