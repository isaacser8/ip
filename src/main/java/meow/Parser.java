package meow;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user commands and converts them into tasks.
 */
public class Parser {

    /**
     * Parses a Todo command into a Todo task.
     *
     * @param input the full user command
     * @return the Todo task represented by the command
     * @throws MeowException if the command is invalid
     */
    private Task parseTodo(String input) throws MeowException {
        String content = input.substring(5);
        if (content.isBlank()) {
            throw new MeowException("Meow! A todo needs a description.");
        }
        return new Todo(content);
    }

    /**
     * Parses a Deadline command into a Deadline task.
     *
     * @param input the full user command
     * @return the Deadline task represented by the command
     * @throws MeowException if the command is invalid
     */
    private Task parseDeadline(String input) throws MeowException {
        String content = input.substring(9);
        if (content.isBlank()) {
            throw new MeowException("Meow! A deadline needs a description and a /by date.");
        }

        int byIndex = content.indexOf("/by");
        if (byIndex == -1) {
            throw new MeowException("Meow! A deadline needs a /by date.");
        }

        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 3).trim();

        if (description.isBlank()) {
            throw new MeowException("Meow! A deadline needs a description.");
        }
        if (by.isBlank()) {
            throw new MeowException("Meow! A deadline needs a /by date.");
        }

        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new MeowException("Meow! Please enter the date in yyyy-MM-dd format.");
        }

        return new Deadline(description, byDate);
    }

    /**
     * Parses an Event command into an Event task.
     *
     * @param input the full user command
     * @return the Event task represented by the command
     * @throws MeowException if the command is invalid
     */
    private Task parseEvent(String input) throws MeowException {
        String content = input.substring(6).trim();
        if (content.isBlank()) {
            throw new MeowException("Meow! An event needs a description, a /from date and a /to date.");
        }

        int fromIndex = content.indexOf("/from");
        int toIndex = content.indexOf("/to");
        if (fromIndex == -1) {
            throw new MeowException("Meow! An event needs a /from date.");
        }
        if (toIndex == -1) {
            throw new MeowException("Meow! An event needs a /to date.");
        }

        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 5, toIndex).trim();
        String to = content.substring(toIndex + 3).trim();
        if (description.isBlank()) {
            throw new MeowException("Meow! An event needs a description.");
        }
        if (from.isBlank()) {
            throw new MeowException("Meow! An event needs a /from date.");
        }
        if (to.isBlank()) {
            throw new MeowException("Meow! An event needs a /to date.");
        }

        return new Event(description, from, to);
    }

    /**
     * Parses a user command into the corresponding task.
     *
     * @param input the full user command
     * @return the task represented by the command
     * @throws MeowException if the command is invalid
     */
    public Task parseTask(String input) throws MeowException {
        if (input.startsWith("todo ")) {
            return parseTodo(input);
        }
        if (input.startsWith("deadline ")) {
            return parseDeadline(input);
        }
        if (input.startsWith("event ")) {
            return parseEvent(input);
        }
        throw new MeowException("Meow! I'm sorry, but I don't know what that means.");
    }

    /**
     * Extracts the keyword from a find command.
     *
     * @param input the full user command.
     * @return the keyword to search for.
     * @throws MeowException if no keyword is provided.
     */
    public String parseFindKeyword(String input) throws MeowException {
        String keyword = input.substring(5).trim();
        if (keyword.isBlank()) {
            throw new MeowException("Meow! Please specify a keyword.");
        }
        return keyword;
    }
}
