package at.khassraf.devtoolkit.command;

import fi.iki.elonen.SimpleWebServer;
import org.springframework.shell.standard.FileValueProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.net.BindException;

@ShellComponent
public class HttpServerCommands {
    private SimpleWebServer server;

    @ShellMethod("start web server")
    public String httpStart(@ShellOption Integer port,
                            @ShellOption(valueProvider = FileValueProvider.class) File wwwRoot)
            throws IOException {
        if (!wwwRoot.exists() || !wwwRoot.isDirectory()) {
            return "Invalid directory for www-root. Specify an existing directory.";
        }

        if (server != null && server.isAlive()) {
            return "A server is already running on port " + server.getListeningPort();
        }

        server = new SimpleWebServer("localhost", port, wwwRoot, true);

        try {
            server.start();
        } catch (BindException ex) {
            return "Could not bind to the specified port";
        }

        return "HTTP server running on http://localhost:" + String.valueOf(port);
    }

    @ShellMethod("Stop a server")
    public String httpStop() {
        if (server != null && server.isAlive()) {
            server.stop();
            return "HTTP server stopped";
        }

        return "HTTP server not running";
    }
}

