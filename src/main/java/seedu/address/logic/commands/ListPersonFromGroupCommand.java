package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.predicateShowAllPersonsInGroup;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Lists all persons in the specific group.
 */
public class ListPersonFromGroupCommand extends Command {

    public static final String COMMAND_WORD = "listpfg";

    public static final String MESSAGE_SUCCESS = "List all persons from the group: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List persons in the group. "
            + "Parameters: " + PREFIX_GROUP + "NAME ";

    public static final String MESSAGE_NO_CONTACT = "Listed all persons in the group (but you do not have "
            + "anyone assigned to this group)";

    private final Group toList;

    /**
     * Creates a ListPersonFromGroupCommand to list all the person from the specified {@code Group}
     */
    public ListPersonFromGroupCommand(Group group) {
        requireNonNull(group);
        toList = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasGroup(toList)) {
            throw new CommandException("This group does not exist.");
        }
        model.updateFilteredPersonList(predicateShowAllPersonsInGroup(toList));
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(MESSAGE_NO_CONTACT);
        }
        return new CommandResult(MESSAGE_SUCCESS + toList.toString());
    }
}
