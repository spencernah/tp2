package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

/**
 * Represents a contact group in the address book.
 */
public class Group {

    private String groupName;

    public Group() {
        groupName = "groupless";
    };

    /**
     * Constructs a {@code Group}.
     *
     * @param groupName A valid group name.
     */
    public Group(String groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    /**
     * Set the group name.
     * s
     * @param newGroupName The new group name
     * @return The new group name
     */
    public Group setGroupName(String newGroupName) {
        this.groupName = newGroupName;
        return this;
    }

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Group
                && groupName.equals(((Group) other).groupName));
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }
}
