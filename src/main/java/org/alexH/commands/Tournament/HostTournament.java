package org.alexH.commands.Tournament;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class HostTournament extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "hosttournament";
    }

    @Override
    public String getDescription()
    {
        return "Host all the channels needed for a tournament";
    }

    @Override
    public List<OptionData> getOptions()
    {
        List<OptionData> list = new ArrayList<OptionData>();
        list.add(new OptionData(OptionType.STRING, "name", "The name of the tournament", true));
        return list;
    }

    @Override
    public void execute(EventData data)
    {
        if (!data.getSelfMember().hasPermission(Permission.ADMINISTRATOR))
        {
            data.reply("You do not have the permissions to send this command", true).queue();
            return;
        }

        data.getGuild().createRole().setName(data.getCommand().getOptions().get(0).getAsString() + " Tournament Participant")

        createTournamentCategory(data);

        data.reply("Your tournament \"" + data.getCommand().getOptions().get(0).getAsString() + "\" has ben created", true).queue();
    }

    private static void createTournamentCategory(EventData data)
    {
        Category category = data.getGuild()
                .createCategory(data.getCommand().getOptions().get(0).getAsString())
                .addPermissionOverride(data.getGuild().getRolesByName("Duelists", false).get(0), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.MESSAGE_SEND))
                .complete();

        data.getGuild()
                .createTextChannel("tournament-announcments", category)
                .queue();
        data.getGuild()
                .createTextChannel("tournament-rules"       , category)
                .queue();
        data.getGuild()
                .createTextChannel("general"                , category)
                .queue();
        data.getGuild()
                .createTextChannel("scores"                 , category)
                .queue();
    }
}
