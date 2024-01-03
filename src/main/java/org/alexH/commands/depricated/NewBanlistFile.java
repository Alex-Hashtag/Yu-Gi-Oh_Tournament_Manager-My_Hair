package org.alexH.commands.depricated;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.AttachedFile;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.MultipartBody;
import okio.Path;

import java.io.File;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static net.dv8tion.jda.api.interactions.commands.OptionType.ATTACHMENT;

@Deprecated
public class NewBanlistFile extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "banlistfile";
    }

    @Override
    public String getDescription()
    {
        return "Posts a new banlist file";
    }

    @Override
    public boolean isOwnerOnly()
    {
        return false;
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
        data.getCommand().getOptions().get(0).getAsAttachment().getProxy().downloadToPath();
        data.getChannel()
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
