package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.address.model.Model;
import seedu.address.model.group.GroupList;

public class ListGroupNameCommand extends Command {

    public static final String COMMAND_WORD = "listgn";
    public static final String COMMAND_WORD2 = "listgrp";

    public static final String MESSAGE_NO_GROUP = "There are no group created";

    private static Logger logger = Logger.getLogger("LIST GROUP NAME");

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);

        FileHandler fh;

        try {
            fh = new FileHandler("./log/ListGroupName.log");
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.log(Level.INFO, "System start to list the group name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (GroupList.getGroupListSize() <= 1) {
            return new CommandResult(MESSAGE_NO_GROUP);
        }

        CommandResult commandResult = new CommandResult(GroupList.listGroups(), false, false);
        return commandResult;
    }
}
