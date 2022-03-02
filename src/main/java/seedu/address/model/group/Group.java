package seedu.address.model.group;

public class Group {

    private String groupName;

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
