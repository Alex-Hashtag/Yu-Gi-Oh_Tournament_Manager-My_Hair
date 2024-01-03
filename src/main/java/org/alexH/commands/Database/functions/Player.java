package org.alexH.commands.Database.functions;

import ca.tristan.easycommands.EasyCommands;
import ca.tristan.easycommands.utils.LogType;
import ca.tristan.easycommands.utils.Logger;
import net.dv8tion.jda.api.entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;


public class Player
{
    public static void create(User user)
    {
        createNewUser(user);
    }

    private static void createNewUser(User user)
    {
        try
        {
            final String query = "INSET INTO playerdata (playerDataId) values (?)";
            final PreparedStatement createUser = EasyCommands.getConnection().prepareStatement(query);
            createUser.execute();
            createUser.setString(1, user.getId());
        }
        catch (Exception exception)
        {
            Logger.log(LogType.ERROR, "Couldn't an Id in the database for user", Arrays.toString(exception.getStackTrace()));
        }

    }
}
