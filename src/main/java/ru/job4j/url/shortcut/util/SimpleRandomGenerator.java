package ru.job4j.url.shortcut.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SimpleRandomGenerator implements RandomGenerator {
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGITS = "1234567890";
    private static final String SPECIAL_CHARS = "@#&$";
    private static final String EXPRESSION_SEQUENCE =
            CHAR_UPPERCASE + CHAR_LOWERCASE + DIGITS;
    private static final String PASSWORD_SEQUENCE = CHAR_UPPERCASE + CHAR_LOWERCASE
            + SPECIAL_CHARS + DIGITS;

    private final SecureRandom random = new SecureRandom();

    private String generateRandomString(String input, int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(input.length());
            result.append(input.charAt(randomIndex));
        }
        return result.toString();
    }

    public String generateRandomExpression(int expressionLength) {
        return generateRandomString(EXPRESSION_SEQUENCE, expressionLength);
    }

    public String generateRandomPassword(int expressionLength) {
        return generateRandomString(PASSWORD_SEQUENCE, expressionLength);
    }
}
