package seedu.address.model.group;

import java.util.ArrayList;

public class GroupList {

    private static ArrayList<Group> groupList = new ArrayList<>();

    public static void addGroup(Group group) {
        groupList.add(group);
    }

    /**
     *
     * @param group
     * @return
     */
    public static boolean hasGroup(Group group) {
        boolean result = false;

        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).equals(group)) {
                result = true;
            }
        }

        return result;
    }

    /**
     *
     * @param index
     * @return
     */
    public static Group getGroup (int index) {
        return groupList.get(index - 1);
    }

    /**
     *
     * @return
     */
    public static String listGroups() {
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < groupList.size(); i++) {
            output.append(i + 1);
            output.append(". " + groupList.get(i).toString());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }


}
