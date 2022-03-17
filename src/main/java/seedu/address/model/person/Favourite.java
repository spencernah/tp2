package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Favourite {
    public final Boolean favourite;

    public static final String MESSAGE_CONSTRAINTS = "Favourite should either be True or False and not NULL";

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
                || (other instanceof Name // instanceof handles nulls
                && favourite.equals(((Favourite) other).favourite)); // state check
    }

    @Override
    public int hashCode() {
        return favourite.hashCode();
    }
}
