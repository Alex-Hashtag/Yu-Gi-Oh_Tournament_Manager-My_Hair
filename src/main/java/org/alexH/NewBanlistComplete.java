package org.alexH;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.dv8tion.jda.api.interactions.commands.OptionType.ATTACHMENT;


public class NewBanlistComplete extends SlashExecutor
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

    @Override
    public List<OptionData> getOptions()
    {
        List<OptionData> list = new ArrayList<OptionData>();
        list.add(new OptionData(ATTACHMENT, "file", "banlist file to be uploaded", true));
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


        //updateSheet(data);

        //updateFile(data);

        data.reply("My Hair has successfully updated the Banlist!", true).queue();

    }

    private void updateSheet(EventData data)
    {
        TextChannel textChannel = data.getGuild().getTextChannelsByName("banlist-document-and-updates", true).get(0);
        textChannel.sendMessage("""
                <@&1128327833811439747>
                Attention Duelists!
                My Hair has officially released a new Forbidden & Limited List, please look through all the highlighted changes!
                https://docs.google.com/spreadsheets/d/1GLn7h7UmcicL1EX0LSAb0Y2tmz7IZFH3kaH7pXJnY68/edit#gid=833698757
                """).queue();
    }

    private void updateFile(EventData data)
    {

        data.getCommand().getOptions().get(0).getAsAttachment().getProxy().downloadToPath();

        TextChannel textChannel = data.getGuild().getTextChannelsByName("banlist-files", true).get(0);

        textChannel
                .sendMessage(
                        """
                                <@&1128327833811439747>
                                Attention Duelists!
                                My Hair is reminding you to get the newest Banlist File for EDOPro!
                                """
                )
                .addFiles(FileUpload.fromData(
                        data.getCommand()
                                .getOptions()
                                .get(0)
                                .getAsAttachment()
                                .toAttachmentData(0)
                                .toETF(),
                        data.getCommand()
                                .getOptions()
                                .get(0)
                                .getAsAttachment()
                                .getFileName()))
                .queue();

        File file = new File(
                data.getCommand()
                        .getOptions()
                        .get(0)
                        .getAsAttachment()
                        .getFileName()
        );

        if (!file.exists()) {
            System.err.println("File does not exist: " + file.getPath());
        } else if (file.isDirectory() && file.list().length > 0) {
            System.err.println("File is a directory and not empty: " + file.getPath());
        } else {
            // Attempt to delete the file
            boolean isDeleted = file.delete();
            if (isDeleted) {
                System.out.println("File deleted successfully: " + file.getPath());
            } else {
                // Deletion failed
                System.err.println("Failed to delete the file: " + file.getPath());
            }
        }
    }
}
