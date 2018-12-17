package at.khassraf.devtoolkit.command;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HashCommands {

    @ShellMethod("Encrypt a password with bcrypt")
    public String bcrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @ShellMethod("Verify that a password matches a bcrypt hash")
    public String bcryptVerify(String hash, String password) {
        // Todo: Validate that hash is a bcrypt hash
        if (BCrypt.checkpw(password, hash)) {
            return "Password matches";
        }
        return "Password does not match";
    }
}
