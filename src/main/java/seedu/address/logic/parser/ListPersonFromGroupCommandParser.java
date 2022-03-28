package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.ListPersonFromGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;


public class ListPersonFromGroupCommandParser implements Parser<ListPersonFromGroupCommand> {

    /**
     * Return a ListPersonFromGroupCommand object that contains the value of user inputted group name
     * @param args contains group name
     * @return an ListPersonFromGroupCommand object
     * @throws ParseException if parsing of group value fails
     */
    public ListPersonFromGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        Group group = null;
        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        }

        return new ListPersonFromGroupCommand(group);

    }
}
