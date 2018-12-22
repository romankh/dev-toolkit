package at.khassraf.devtoolkit.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.springframework.shell.standard.FileValueProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
public class UtilityCommands {
    @ShellMethod("Prettify a JSON string")
    public String prettifyJson(@ShellOption(valueProvider = FileValueProvider.class) String input,
                               @ShellOption(valueProvider = FileValueProvider.class) String output)
            throws IOException {

        // Todo: check that file exists
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        String result = gson.toJson(
                jsonParser.parse(new String(Files.readAllBytes(Paths.get(input))))
        );
        if (output != null) {
            PrintWriter writer = new PrintWriter(output);
            writer.print(result);
            writer.flush();
            writer.close();
            return "OK";
        }
        return result;
    }
}
