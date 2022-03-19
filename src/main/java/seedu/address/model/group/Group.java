package seedu.address.model.group;

/**
 * Represents a contact group in the address book.
 */
public class Group {

    private String groupName;

    /**
     * Set the group name.
     *
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
