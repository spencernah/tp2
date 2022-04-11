
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.GroupContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose group's name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "findgroup";
    public static final String COMMAND_WORD_1 = "findg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "/" + COMMAND_WORD_1
            + ": Finds all persons that are assigned to groups "
            + " with group name that contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " NUS OR\n"
            + COMMAND_WORD_1 + " NUS";

    private final GroupContainsKeywordsPredicate predicate;

    /**
     *
     * @param predicate list of Person that matches the condition
     */
    public FindGroupCommand(GroupContainsKeywordsPredicate predicate) {

        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof FindGroupCommand)) {
            return false;
        }
        return other == this // short circuit if same object
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate)); // state check
    }
}
