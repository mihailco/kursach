package harakiri.model.test.question;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String id; // Уникальный идентификатор вопроса
    private QuestionType type; // Тип вопроса
    private String questionText; // Текст вопроса

    private List<String> options; // Варианты ответов (для SINGLE_CHOICE, MULTIPLE_CHOICE, TRUE_FALSE, JUXTAPOSITION)
    private List<Integer> correctOptionIndexes; // Индексы правильных ответов (используется для SINGLE_CHOICE и MULTIPLE_CHOICE)
    private List<Integer> sequence; // Правильная последовательность (для типа SEQUENSE)
    private List<Pair<Integer, Integer>> matches; // Пары индексов для JUXTAPOSITION типа
    private String correctText; // Правильный текстовый ответ (для TEXT)

}
