package org.alexH;

import ca.tristan.easycommands.EasyCommands;
import net.dv8tion.jda.api.JDA;
import org.alexH.commands.Database.localStorage.CardJsonUpdate;
import org.alexH.commands.Tournament.*;
import org.alexH.listeners.SignUpButtonListener;

import java.io.IOException;
import java.util.HashMap;


public class Main
{
    public static HashMap<String, Tournament> tournaments = new HashMap<>();
    public static void main(String[] args) throws InterruptedException, IOException, Exception
    {
        EasyCommands easyCommands = new EasyCommands();

        boolean SaveData = false;
        if (SaveData) CardJsonUpdate.run();
        JDA jda = easyCommands
                .addExecutor(new NewBanlistComplete(), new HostTournament(), new DeleteCategory(), new DeleteTournament(), new GetTournamentParticipants())
                .registerListeners(new SignUpButtonListener())
                .buildJDA();

    }
}