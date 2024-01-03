package org.alexH.commands.Tournament;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.internal.entities.RoleImpl;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.alexH.Main;

import java.awt.*;
import java.time.Instant;
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

        Tournament tournament = new Tournament(data);
        Main.tournaments.put(tournament.getAnnouncements().getId(),tournament);

        data.reply("Tournament successfully hosted", true).queue();
    }
}
