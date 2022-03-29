package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Person;

public class RenameGroupCommand extends Command {

    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Renaming the existing group at the index to another name "
            + "new name. \n"
            + "Parameters: INDEX (must be a positive integer from group list) "
            + PREFIX_GROUP + " GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_GROUP + "NEW_GROUP_NAME";

    public static final String MESSAGE_SUCCESS = "Group index %1$d rename successfully: %2$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "The group name is duplicated.";

    private final Index targetIndex;
    private final Group group;

    /**
     * @param targetIndex The target index
     * @param group The new group name
     */
    public RenameGroupCommand(Index targetIndex, Group group) {
        this.targetIndex = targetIndex;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getOneBased() <= 0 || targetIndex.getOneBased() >= model.getGroupSize()) {
            throw new CommandException(MESSAGE_USAGE);
        }

        if (model.hasGroup(this.group)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        assert model.hasGroup(this.group);

        Group groupName = GroupList.getGroup(targetIndex.getOneBased() + 1);
        if (model.countPersonInGroup(Model.predicateShowAllPersonsInGroup(groupName)) > 0) {
            ArrayList<Person> personInGroup = model.getPersonListInThisGroup(groupName);
            for (int i = 0; i < personInGroup.size(); i++) {
                Person person = personInGroup.get(i);
                model.assignToGroup(this.group, person);
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        model.renameGroup(targetIndex.getOneBased() + 1, group.toString());
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(), group.toString()));
    }


}
