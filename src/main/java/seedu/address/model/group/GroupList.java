package seedu.address.model.group;

import java.util.ArrayList;

public class GroupList {

    private static ArrayList<Group> groupList = new ArrayList<>();

    GroupList() {

        Group defaultGroup = new Group();
        defaultGroup.setGroupName("N/A");

        addGroup(defaultGroup);
        assert !groupList.isEmpty();
    }

    public static void addGroup (Group group) {
        groupList.add(group);
    }

    /**
     * Check if group already exist in the listGroup
     * @param group name to be checked
     * @return true if exist, false otherwise
     */
    public static boolean hasGroup (Group group) {

        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).equals(group)) {
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
        groupList.remove(index - 1);
    }

    /**
     * Get a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public static Group getGroup (int index) {
        return groupList.get(index - 1);
    }

    /**
     * Get all {@code Group}.
     */
    public static ArrayList<Group> getGroupList () {
        return groupList;
    }

    /**
     * Get all {@code Group}.
     */
    public static void clearGroupList () {
        groupList.clear();
        Group defaultGroup = new Group();
        defaultGroup.setGroupName("N/A");

        addGroup(defaultGroup);
        assert !groupList.isEmpty();
    }

    /**
     * List all {@code Groups} as String.
     *
     */
    public static String listGroups () {
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < groupList.size(); i++) {
            if (!groupList.get(i).toString().equals("N/A")) {
                output.append(i);
                output.append(". " + groupList.get(i).toString());
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    public static int getGroupListSize() {
        return groupList.size();
    }

}
