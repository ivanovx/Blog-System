package org.ivanovx.bloggable.util;

public class SlugGenerator {
    static String[] latLow = {"a", "b", "v", "g", "d", "e", "yo", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "kh", "ts", "ch", "sh", "shch", "\"", "y", "'", "e", "yu", "ya"};

    static String[] rusLow = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};

    public static String toSlug(String uglyString) {
        char ugly[] = cyrillicToLatin(uglyString.toLowerCase()).toCharArray();

        StringBuffer result = new StringBuffer();

        boolean isLastCharacterDash = false;

        for (char c : ugly) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
                isLastCharacterDash = false;
            } else if (!isLastCharacterDash) {
                result.append('-');
                isLastCharacterDash = true;
            }
        }

        return result.toString().replace(' ', '-');
    }

    private static String cyrillicToLatin(String str) {
        for (int i = 0; i <= 32; i++) {
            str = str.replace(rusLow[i], latLow[i]);
        }

        return str;
    }
}
