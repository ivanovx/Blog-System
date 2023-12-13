package org.blogy.util;

import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SlugGenerator {
    private static final String[][] chars = new String[][]{
            {"а", "a"},
            {"б", "b"},
            {"в", "v"},
            {"г", "g"},
            {"д", "d"},
            {"е", "e"},
            {"ж", "zh"},
            {"з", "z"},
            {"и", "i"},
            {"й", "y"},
            {"к", "k"},
            {"л", "l"},
            {"м", "m"},
            {"н", "n"},
            {"о", "o"},
            {"п", "p"},
            {"р", "r"},
            {"с", "s"},
            {"т", "t"},
            {"у", "u"},
            {"ф", "f"},
            {"х", "kh"},
            {"ц", "ts"},
            {"ч", "ch"},
            {"ш", "sh"},
            {"щ", "shch"},
            {"ъ", "\""},
            {"ы", "y"},
            {"ь", "'"},
            {"ю", "yu"},
            {"я", "ya"}
    };

    private static final Map<Character, String> characters = Arrays.stream(chars).collect(Collectors.toMap(e -> e[0].toCharArray()[0], e -> e[1]));

    public static String toSlug(String uglyString) {
        char ugly[] = uglyString.toLowerCase().toCharArray();

        StringBuffer result = new StringBuffer();

        boolean isLastCharacterDash = false;

        for (char c : ugly) {
            if (Character.isLetterOrDigit(c)) {
                if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                    result.append(characters.get(c));
                } else {
                    result.append(c);
                }
                isLastCharacterDash = false;
            } else if (!isLastCharacterDash) {
                result.append('-');
                isLastCharacterDash = true;
            }
        }

        return result.toString();
    }
}
