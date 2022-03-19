package seedu.address.model.person;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Person's Favourite in the address book.
 *  * Guarantees: immutable; is always valid
 */
public class Favourite {

    public static final String MESSAGE_CONSTRAINTS = "Favourite should either be True or False and not NULL";

    public final Boolean favourite;

    /**
     * Constructs a {@code Favourite}.
     *
     * @param favourite A boolean if favourite.
     */
    public Favourite(Boolean favourite) {
        requireNonNull(favourite);
        this.favourite = favourite;
    }

    public Boolean getBoolean() {
        return favourite;
    }

    @Override
    public String toString() {
        return favourite.toString();
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Favourite // instanceof handles nulls
                && (favourite == ((Favourite) other).favourite)); // state check
    }

    @Override
    public int hashCode() {
        return favourite.hashCode();
    }
}
