package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.group.GroupList;

public class ListGroupNameCommand extends Command {
    public static final String COMMAND_WORD = "listgn";

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);
        CommandResult commandResult = new CommandResult(GroupList.listGroups(), false, false);
        return commandResult;
    }
}
