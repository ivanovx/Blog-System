package org.blogy.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class SlugGenerator {
    private static Map<Character, String> getCharacters() {
        return Map.ofEntries(
                Map.entry('а', "a"),
                Map.entry('б', "b"),
                Map.entry('в', "v"),
                Map.entry('г', "g"),
                Map.entry('д', "d"),
                Map.entry('е', "e"),
                Map.entry('ж', "zh"),
                Map.entry('з', "z"),
                Map.entry('и', "i"),
                Map.entry('й', "y"),
                Map.entry('к', "k"),
                Map.entry('л', "l"),
                Map.entry('м', "m"),
                Map.entry('н', "n"),
                Map.entry('о', "o"),
                Map.entry('п', "p"),
                Map.entry('р', "r"),
                Map.entry('с', "s"),
                Map.entry('т', "t"),
                Map.entry('у', "u"),
                Map.entry('ф', "f"),
                Map.entry('х', "h"),
                Map.entry('ц', "ts"),
                Map.entry('ч', "ch"),
                Map.entry('ш', "sh"),
                Map.entry('щ', "sht"),
                Map.entry('ъ', "a"),
                Map.entry('ю', "yu"),
                Map.entry('я', "ya")
        );
    }

    public static String toSlug(String uglyString) {
        Map<Character, String> characters = getCharacters();

        return uglyString.toLowerCase().chars().mapToObj(c -> (char) c).filter(c -> Character.isLetterOrDigit(c) || Character.isWhitespace(c)).map(c -> {
            if (Character.isWhitespace(c)) {
                return "-";
            }

            if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                return characters.get(c);
            }

            return String.valueOf(c);
       }).collect(Collectors.joining());
    }

    public static String toSlug(String uglyStr, boolean isExists) {
        if (!isExists) {
            return toSlug(uglyStr);
        }

        String slug = uglyStr.concat("-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return slug;
    }
}
