package org.alexH.commands.Tournament;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;


public class DeleteCategory extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "deletecategory";
    }

    @Override
    public String getDescription()
    {
        return "Deletes a category";
    }

    @Override
    public List<OptionData> getOptions()
    {
        List<OptionData> list = new ArrayList<>();
        list.add(new OptionData(OptionType.STRING, "id", "Id of the category.", true));
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

        Category category = data.getGuild()
                        .getCategoryById(data.getCommand().getOptions().get(0).getAsString());

        assert category != null;
        List<GuildChannel> channels = category.getChannels();
        for (GuildChannel channel : channels) channel.delete().queue();
        category.delete().queue();

        data.reply("The category has been removed", true).queue();
    }
}
