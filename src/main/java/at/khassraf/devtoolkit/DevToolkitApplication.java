package at.khassraf.devtoolkit;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class DevToolkitApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(DevToolkitApplication.class, args);
    }

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString(
                "dtk:>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
        );
    }
}
