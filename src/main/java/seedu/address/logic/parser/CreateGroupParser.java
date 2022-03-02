package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

public class CreateGroupParser {

    /**
     *
     * @param args xx
     * @return xx
     * @throws ParseException xx
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        Group group = null;
        if (argumentMultimap.getValue(PREFIX_GROUP).isPresent()) {
            group = ParserUtil.parseGroup(argumentMultimap.getValue(PREFIX_GROUP).get());
        }

        return new CreateGroupCommand(group);
    }
}
