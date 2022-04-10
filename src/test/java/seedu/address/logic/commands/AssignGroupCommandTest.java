package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AssignGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_newGroup_success() {

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


}
