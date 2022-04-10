package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Person;


/**
 * Favourite a person.
 */
public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    public static final String COMMAND_WORD2 = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " OR "
            + COMMAND_WORD2
            + ": Favourite the contact identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ADD_FAVOURITE_SUCCESS = "Added Favourite to Person: %1$s";
    public static final String MESSAGE_DELETE_FAVOURITE_SUCCESS = "Removed Favourite from Person: %1$s";

    private final Index index;

    /**
     * @param index  of the person in the filtered person list to edit the remark
     */
    public FavouriteCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Favourite favourite = new Favourite(!personToEdit.getFavourite().getBoolean());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(),
                personToEdit.getGroup(), favourite);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (!favourite.getBoolean()) {
            return new CommandResult(String.format(MESSAGE_DELETE_FAVOURITE_SUCCESS , personToEdit));
        }

        return new CommandResult(String.format(MESSAGE_ADD_FAVOURITE_SUCCESS , personToEdit));
    }



    @Override
    public boolean equals(Object other) {
        // instanceof handles nulls
        if (!(other instanceof FavouriteCommand)) {
            return false;
        }
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // state check
        FavouriteCommand e = (FavouriteCommand) other;
        return index.equals(e.index);
    }
}
