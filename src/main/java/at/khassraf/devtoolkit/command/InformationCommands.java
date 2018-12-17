package at.khassraf.devtoolkit.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@ShellComponent
public class InformationCommands {

    @ShellMethod("Display an ASCII table")
    public String ascii() throws IOException {
        StringBuilder builder = new StringBuilder("\n");
        File file = ResourceUtils.getFile("classpath:data/ascii.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }

    @ShellMethod("Display import network ports")
    public String ports() throws IOException {
        StringBuilder builder = new StringBuilder("\n");
        File file = ResourceUtils.getFile("classpath:data/ports.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }
}
