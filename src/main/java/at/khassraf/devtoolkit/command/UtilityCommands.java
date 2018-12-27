package at.khassraf.devtoolkit.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.springframework.shell.standard.FileValueProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

@ShellComponent
public class UtilityCommands {
    @ShellMethod("Prettify a JSON string")
    public String prettifyJson(@ShellOption(valueProvider = FileValueProvider.class) File input,
                               @ShellOption(valueProvider = FileValueProvider.class) File output)
            throws IOException {

        // Todo: check that file exists
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        String result = gson.toJson(
                jsonParser.parse(new String(Files.readAllBytes(input.toPath())))
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

    @ShellMethod("Check internet connection")
    public String onlineStatus() {
        String host = "8.8.8.8";
        int port = 53;
        int timeout = 2500;
        return isHostReachable(host, port, timeout) ? "\nOnline" : "\nOffline";
    }

    @ShellMethod("Check internet connection")
    public String testService(@ShellOption String host, @ShellOption Integer port) {
        int timeout = 2500;
        return isHostReachable(host, port, timeout) ? "\nService is reachable" : "\nService is NOT reachable";
    }

    private boolean isHostReachable(String host, Integer port, Integer timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
