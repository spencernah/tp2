package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class AssignGroupCommand extends Command {

    public static final String COMMAND_WORK = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORK + ": assign a contact to the group. "
            + "Parameters: " + PREFIX_NAME + "Contact name " + PREFIX_GROUP + "Group name";

    public static final String MESSAGE_SUCCESS = "Contact %1$s assigned to Group %2$s. ";
    public static final String MESSAGE_NO_EXIST_GROUP = "The group is not in the address book. ";
    public static final String MESSAGE_NO_EXIST_PERSON = "The person is not in the address book. ";

    private final Group group;
    private final Name name;

    /**
     * Assign a contact to a group.
     *
     * @param group The group, which the person to be assigned to.
     * @param name The name to be assigned
     */
    public AssignGroupCommand(Group group, Name name) {
        requireNonNull(group);
        requireNonNull(name);
        this.group = group;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(this.group)) {
            throw new CommandException(MESSAGE_NO_EXIST_GROUP);
        }

        assert model.hasGroup((this.group));
        Person person = model.getPerson(this.name);

        if (person == null || !model.hasPerson(person)) {
            throw new CommandException(MESSAGE_NO_EXIST_PERSON);
        }

        model.assignToGroup(this.group, person);

        if (model.countPersonInGroup(Model.predicateShowAllPersonsInGroup(this.group)) > 0) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, name, group));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AssignGroupCommand)) {
            return false;
        }

        return other == this // short circuit if same object
                || (other instanceof AssignGroupCommand // instanceof handles nulls
                && group.toString().equals(((AssignGroupCommand) other).group.toString())
                && name.toString().equals((((AssignGroupCommand) other).name).toString()));
    }
}
