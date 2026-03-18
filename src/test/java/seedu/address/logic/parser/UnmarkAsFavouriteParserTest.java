package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAsFavouriteCommand;

public class UnmarkAsFavouriteParserTest {

    private final UnmarkAsFavouriteParser parser = new UnmarkAsFavouriteParser();

    @Test
    public void parse_validArgument() {
        assertParseSuccess(parser, "1",
                new UnmarkAsFavouriteCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UnmarkAsFavouriteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nullArgument_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UnmarkAsFavouriteCommand.MESSAGE_USAGE));
    }
}
