package org.alexH;

import ca.tristan.easycommands.EasyCommands;
import net.dv8tion.jda.api.JDA;
import org.alexH.commands.Database.localStorage.CardJsonUpdate;
import org.alexH.commands.Tournament.DeleteCategory;
import org.alexH.commands.Tournament.HostTournament;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws InterruptedException, IOException, Exception
    {
        EasyCommands easyCommands = new EasyCommands();

        boolean SaveData = false;
        if (SaveData) CardJsonUpdate.run();

        JDA jda = easyCommands.addExecutor(new NewBanlistComplete(), new HostTournament(), new DeleteCategory())
                .registerListeners().buildJDA();

    }
}