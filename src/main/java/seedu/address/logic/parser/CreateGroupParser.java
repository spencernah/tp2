package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

public class CreateGroupParser {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns an CreateGroupCommand object for execution.
     *
     * @param args The given name of group
     * @return An CreateGroupCommand object
     * @throws ParseException If the user input does not conform the expected format
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_GROUP)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }

        Group group;
        try {
            group = ParserUtil.parseGroup(argumentMultimap.getValue(PREFIX_GROUP).get());
        } catch (NoSuchElementException e) {
            group = new Group();
            group.setGroupName("N/A");
        } catch (ParseException e) {
            throw e;
        }

        return new CreateGroupCommand(group);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
