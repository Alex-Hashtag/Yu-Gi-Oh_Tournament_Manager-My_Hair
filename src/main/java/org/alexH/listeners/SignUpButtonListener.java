package org.alexH.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.alexH.Main;
import org.alexH.commands.Tournament.HostTournament;

import java.util.Objects;


public class SignUpButtonListener extends ListenerAdapter
{
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event)
    {
        String id = event.getChannel().getId();

        if (Objects.equals(event.getButton().getId(), "sign-up-button"))
        {
            event.getGuild()
                    .addRoleToMember(event.getMember().getUser(), Main.tournaments.get(id).getParticipantRole())
                    .queue();
            event.getUser()
                    .openPrivateChannel()
                    .flatMap(privateChannel -> privateChannel.sendMessage("You have signed up for a tournament"))
                    .queue();
            Main.tournaments
                    .get(id)
                    .getUsersAsList()
                    .add(event.getUser());

            return;
        }

        if (Objects.equals(event.getButton().getId(), "quit-button"))
        {
            if (event.getMember().getRoles().contains(Main.tournaments.get(id).getParticipantRole()))
                event.getUser()
                        .openPrivateChannel()
                        .flatMap(privateChannel -> privateChannel.sendMessage("You aren't signed up for this tournament"))
                        .queue();
            else event.getGuild()
                    .removeRoleFromMember(event.getMember().getUser(), Main.tournaments.get(id).getParticipantRole())
                    .queue();
        }
    }
}
