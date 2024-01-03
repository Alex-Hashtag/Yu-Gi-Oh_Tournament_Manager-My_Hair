package org.alexH.commands.depricated;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.alexH.commands.Database.functions.Player;

import java.util.List;


public class registerUser extends SlashExecutor
{


    @Override
    public String getName()
    {
        return "register";
    }

    @Override
    public String getDescription()
    {
        return "Registers a user in the database";
    }

    @Override
    public boolean isOwnerOnly()
    {
        return false;
    }

    @Override
    public void execute(EventData data)
    {
        data.deferReply();
        Player.create(data.getCommandSender().getUser());
        data.getHook().sendMessage(data.getCommandSender().getAsMention() + " you have been registered").queue();
    }
}
