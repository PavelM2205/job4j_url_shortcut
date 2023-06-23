package ru.job4j.url.shortcut.util;

public interface RandomGenerator {

    String generateRandomExpression(int expressionLength);

    String generateRandomPassword(int expressionLength);
}
