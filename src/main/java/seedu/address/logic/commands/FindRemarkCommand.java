
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose remarks contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRemarkCommand extends Command {

    public static final String COMMAND_WORD = "findr";
    public static final String COMMAND_WORD_1 = "findremark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "/" + COMMAND_WORD_1
            + ": Finds all persons whose remark contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " swim OR"
            + COMMAND_WORD_1 + "swim";

    private final RemarkContainsKeywordsPredicate predicate;

    /**
     *
     * @param predicate list of Person that matches the condition
     */
    public FindRemarkCommand(RemarkContainsKeywordsPredicate predicate) {

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

        if (!(other instanceof FindRemarkCommand)) {
            return false;
        }
        return other == this // short circuit if same object
                || (other instanceof FindRemarkCommand // instanceof handles nulls
                && predicate.equals(((FindRemarkCommand) other).predicate)); // state check
    }
}
