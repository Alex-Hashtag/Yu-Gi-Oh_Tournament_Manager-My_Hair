package org.alexH.commands.depricated;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;


public class ExampleCmd extends SlashExecutor
{
    @Override
    public String getName()
    {
        return "hello";
    }

    @Override
    public String getDescription()
    {
        return "Say Hello world!";
    }

    @Override
    public boolean isOwnerOnly()
    {
        return false;
    }


    @Override
    public void execute(EventData data) {
        data.reply("Hello world!", false).queue();
    }

}