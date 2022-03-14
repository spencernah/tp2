package seedu.address.model.group;

import java.util.ArrayList;

public class GroupList {

    private static ArrayList<Group> listGroup = new ArrayList<>();

    GroupList() {
        Group defaultGroup = new Group();
        defaultGroup.setGroupName("N/A");

        addGroup(defaultGroup);
        assert !listGroup.isEmpty();
    }

    public static void addGroup (Group group) {
        listGroup.add(group);
    }

    /**
     * Check if group already exist in the listGroup
     * @param group name to be checked
     * @return true if exist, false otherwise
     */
    public static boolean hasGroup (Group group) {

        for (int i = 0; i < listGroup.size(); i++) {
            if (listGroup.get(i).equals(group)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Delete a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public static void deleteGroup (int index) {
        listGroup.remove(index - 1);
    }

    /**
     * Get a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public static Group getGroup (int index) {
        return listGroup.get(index - 1);
    }

    /**
     * Get all {@code Group}.
     */
    public static ArrayList<Group> getGroupList () {
        return listGroup;
    }

    /**
     * List all {@code Groups} as String.
     *
     */
    public static String listGroups () {
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < listGroup.size(); i++) {
            if (!listGroup.get(i).toString().equals("N/A")) {
                output.append(i);
                output.append(". " + listGroup.get(i).toString());
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    public static int getGroupListSize() {
        return listGroup.size();
    }

}
