package com.fpt.vinmartauth.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextUtils {
    public static String formatDiacriticalMarks(String original) {
        String nfdNormalizedString = Normalizer.normalize(original, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
