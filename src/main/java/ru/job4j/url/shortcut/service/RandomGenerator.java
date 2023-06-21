package ru.job4j.url.shortcut.service;

public interface RandomGenerator {

    String generateRandomExpression(int expressionLength);

    String generateRandomPassword(int expressionLength);
}
