package org.alexH.commands.depricated;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

import static net.dv8tion.jda.api.interactions.commands.OptionType.ATTACHMENT;

@Deprecated
public class NewBanlist extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "banlist";
    }

    @Override
    public String getDescription()
    {
        return "Release a new Banlist";
    }

    @Override
    public boolean isOwnerOnly()
    {
        return true;
    }

    public List<OptionData> getOptions()
    {
        List<OptionData> list = new ArrayList<OptionData>();
        list.add(new OptionData(ATTACHMENT, "file", "banlist file to be uploaded", true));
        return list;
    }

    @Override
    public void execute(EventData data)
    {

        data.getChannel().sendMessage("""
                <@&1128327833811439747>
                Attention Duelists!
                My Hair has officially released a new Forbidden & Limited List, please look through all the highlighted changes!
                https://docs.google.com/spreadsheets/d/1GLn7h7UmcicL1EX0LSAb0Y2tmz7IZFH3kaH7pXJnY68/edit#gid=833698757
                """).queue();


    }
}
