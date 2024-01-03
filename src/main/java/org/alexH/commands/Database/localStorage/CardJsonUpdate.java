package org.alexH.commands.Database.localStorage;



import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CardJsonUpdate
{
    private static String ygoprodeckDbApi = "https://db.ygoprodeck.com/api/v7/cardinfo.php";
    private static String filePath = "src\\main\\java\\org\\alexH\\commands\\Database\\localStorage\\ygoprodeckDbLocal.json";

    public static void run () throws IOException
    {
        URL db = new URL(ygoprodeckDbApi);
        HttpURLConnection connection = (HttpURLConnection) db.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String inputLine;
            StringBuilder builtString = new StringBuilder();

            while ((inputLine = in.readLine())!= null)
                builtString.append(inputLine);
            in.close();

            String builtStringFormatted = builtString.toString();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = gson.fromJson(builtStringFormatted, com.google.gson.JsonObject.class);

            FileWriter writer = new FileWriter(filePath);
            writer.write(gson.toJson(jsonObject));
            writer.close();
        }

    }
}
