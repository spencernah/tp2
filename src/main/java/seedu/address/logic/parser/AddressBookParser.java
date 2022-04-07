package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignGroupCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.FindRemarkCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFavCommand;
import seedu.address.logic.commands.ListGroupNameCommand;
import seedu.address.logic.commands.ListPersonFromGroupCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.RenameGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindGroupCommand.COMMAND_WORD:
        case FindGroupCommand.COMMAND_WORD_1:
            return new FindGroupCommandParser().parse(arguments);

        case FindRemarkCommand.COMMAND_WORD:
        case FindRemarkCommand.COMMAND_WORD_1:
            return new FindRemarkCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case FavouriteCommand.COMMAND_WORD:
        case FavouriteCommand.COMMAND_WORD2:
            return new FavouriteCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListFavCommand.COMMAND_WORD:
            return new ListFavCommand();

        case CreateGroupCommand.COMMAND_WORD:
            return new CreateGroupParser().parse(arguments);

        case ListGroupNameCommand.COMMAND_WORD:
        case ListGroupNameCommand.COMMAND_WORD2:
            return new ListGroupNameCommand();

        case ListPersonFromGroupCommand.COMMAND_WORD:
            return new ListPersonFromGroupCommandParser().parse(arguments);

        case AssignGroupCommand.COMMAND_WORK:
            return new AssignGroupCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case RenameGroupCommand.COMMAND_WORD:
            return new RenameGroupCommandParser().parse(arguments);



        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD2:
        case ExitCommand.COMMAND_WORD3:
        case ExitCommand.COMMAND_WORD4:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
