package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a {@code MeetingCommand}.
 */
public class MeetingCommandParser implements Parser<MeetingCommand> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Parses a meeting command in the form {@code INDEX DD/MM/YYYY HH:MM}.
     *
     * @param args Raw user input following the {@code meeting} command word.
     * @return A {@code MeetingCommand} with the parsed index, date and time.
     * @throws ParseException if the input does not match the expected meeting command format.
     */
    @Override
    public MeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] parts = trimmedArgs.split("\\s+");

        if (parts.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetingCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(parts[0]);
            LocalDate date = LocalDate.parse(parts[1], DATE_FORMATTER);
            LocalTime time = LocalTime.parse(parts[2], TIME_FORMATTER);
            return new MeetingCommand(index, date, time);
        } catch (ParseException | DateTimeParseException exception) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetingCommand.MESSAGE_USAGE), exception);
        }
    }
}
