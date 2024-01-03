package org.alexH.commands.Tournament;

import ca.tristan.easycommands.commands.EventData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class Tournament
{
    private final String name;
    private final Role participantRole;
    private Category category;
    private TextChannel announcements;
    private TextChannel rules;
    private ArrayList<User> users;

    public Tournament(EventData data)
    {
        this.name = data.getCommand()
                .getOptions()
                .get(0)
                .getAsString();

        this.participantRole = data.getGuild()
                .createRole()
                .setName(data.getCommand().getOptions().get(0).getAsString() + " Tournament Participant")
                .setColor(new Color(53, 201, 145))
                .complete();

        users = new ArrayList<>();

        createTournamentCategory(data);
        sendTournamentAnnouncement(this.announcements);
        sendRules(this.rules);

    }

    private void createTournamentCategory(EventData data)
    {
        this.category = data.getGuild()
                .createCategory(this.name)
                .addPermissionOverride(data.getGuild().getRolesByName("Duelists", false).get(0), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.MESSAGE_SEND))
                .complete();

        this.announcements = data.getGuild()
                .createTextChannel("tournament-announcements", category)
                .complete();
        this.rules = data.getGuild()
                .createTextChannel("tournament-rules"       , category)
                .complete();
        data.getGuild()
                .createTextChannel("general"                , category)
                .addPermissionOverride(this.participantRole, EnumSet.of(Permission.MESSAGE_SEND), null)
                .complete();
        data.getGuild()
                .createTextChannel("scores"                 , category)
                .addPermissionOverride(this.participantRole, EnumSet.of(Permission.MESSAGE_SEND), null)
                .complete();
    }

    private void sendTournamentAnnouncement(TextChannel channel)
    {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(new Color(53, 201, 145))
                .setTimestamp(Instant.now())
                .setTitle("New Tournament Announcement")
                .setDescription("""
                        New Tourney and shit
                        Testing mostly
                        """);

        Button signUpButton = Button.primary("sign-up-button", "Sign-up for the tournament");
        Button quitButton   = Button.danger("quit-button",     "Quit the tournament before it has started");

        channel.sendMessageEmbeds(embedBuilder.build())
                .setActionRow(signUpButton, quitButton)
                .queue();


    }
    private void sendRules (TextChannel channel)
    {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(new Color(53, 201, 145))
                .setTimestamp(Instant.now())
                .setTitle("Tournament Rules")
                .setDescription("""
                        New Tourney and shit
                        Testing mostly
                        """);

        channel.sendMessage("""
                Rules
                """)
                .queue();
    }

    public Role getParticipantRole()
    {
        return participantRole;
    }

    public TextChannel getAnnouncements()
    {
        return announcements;
    }

    public void deleteTournament()
    {
        assert this.category != null;
        List<GuildChannel> channels = category.getChannels();
        for (GuildChannel channel : channels) channel.delete().queue();
        this.category.delete().queue();

        this.participantRole.delete().queue();
    }

    public String getUsers()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (User user : this.users)
        {
            stringBuilder.append('\n' + user.getGlobalName());
        }
        return stringBuilder.toString();
    }

    public ArrayList<User> getUsersAsList()
    {
        return this.users;
    }


}
