package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;




/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AssignGroupCommandTest {


    @Test
    public void execute_newGroup_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Group newGroup = new Group().setGroupName("new123");
        Name name = new Name("Alice Pauline");
        AssignGroupCommand assignGroupCommand = new AssignGroupCommand(newGroup, name);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.addGroup(newGroup);
        model.addGroup(newGroup);
        CommandResult expectedCommandResult = null;
        try {
            expectedCommandResult = assignGroupCommand.execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        assertCommandSuccess(assignGroupCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        final AssignGroupCommand standardCommand = new AssignGroupCommand(new Group().setGroupName(VALID_GROUP_AMY),
                new Name(VALID_NAME_AMY));

        // same values -> returns true
        AssignGroupCommand commandWithSameValues = new AssignGroupCommand(new Group().setGroupName(VALID_GROUP_AMY),
                new Name(VALID_NAME_AMY));

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals((new ClearCommand())));


        // different name -> returns false
        assertFalse(standardCommand.equals(new AssignGroupCommand(new Group().setGroupName(GROUP_DESC_AMY),
                new Name(VALID_NAME_BOB))));
    }
}
