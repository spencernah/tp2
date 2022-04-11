package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private Group group = new Group();
    private final Favourite favourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                  Group group, Favourite favourite) throws GroupNotFoundException {
        requireAllNonNull(name, phone, email, address, tags, group, favourite);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        if (!GroupList.hasGroup(group) && !group.toString().equals("N/A")) {
            throw new GroupNotFoundException("The group does not exist");
        }
        assert GroupList.hasGroup(group) || group.toString().equals("N/A");
        this.group.setGroupName(group.toString());
        this.favourite = favourite;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Group getGroup() {
        return group;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setGroup(Group group) {
        this.group.setGroupName(group.toString());
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameName(Person otherPerson) {
        if (otherPerson.getName().toString().equalsIgnoreCase(this.name.toString())) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().toString().equals(getName().toString())
                && otherPerson.getPhone().toString().equals(getPhone().toString())
                && otherPerson.getEmail().toString().equals(getEmail().toString())
                && otherPerson.getAddress().toString().equals(getAddress().toString())
                && otherPerson.getTags().toString().equals(getTags().toString())
                && otherPerson.getGroup().toString().equals(getGroup().toString())
                && otherPerson.getFavourite().toString().equals(getFavourite().toString());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, group, favourite);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());
        if (getRemark().equals("")) {
            builder.append("; Remark: ")
                 .append(getRemark());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Group: ").append(getGroup().toString());

        if (getFavourite().equals("")) {
            builder.append("; Favourite: ")
                    .append(getFavourite().toString());
        }

        return builder.toString();
    }

}
