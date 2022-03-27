package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindRemarkCommand;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;

public class FindRemarkCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindRemarkCommand expectedFindCommand =
                new FindRemarkCommand(new RemarkContainsKeywordsPredicate(Arrays.asList("perfume", "souvenir")));
        assertParseSuccess(parser, "perfume souvenir", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n perfume \n \t souvenir  \t", expectedFindCommand);
    }

}
