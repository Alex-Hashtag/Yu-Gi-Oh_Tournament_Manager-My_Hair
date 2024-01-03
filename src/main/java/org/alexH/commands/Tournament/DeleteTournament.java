package org.alexH.commands.Tournament;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.internal.handle.ScheduledEventCreateHandler;
import org.alexH.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class DeleteTournament extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "deletetournament";
    }

    @Override
    public String getDescription()
    {
        return "Deletes an available tournament";
    }

    @Override
    public List<OptionData> getOptions()
    {
        List<OptionData> list = new ArrayList<>();
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

        String name = data.getCommand()
                .getOptions()
                .get(0)
                .getAsString();

        List<GuildChannel> channels = data.getGuild().getCategoriesByName(name, true).get(0).getChannels();

        for (Channel channel : channels)
        {
            if (channel.getName().equals("tournament-announcements"))
            {
                System.out.println("SuccessfullyDeleted");
                Main.tournaments.get(channel.getId()).deleteTournament();
                Main.tournaments.remove(channel.getId());

                data.reply("Tournament successfully deleted", true).queue();
                break;
            }
        }
    }
}
