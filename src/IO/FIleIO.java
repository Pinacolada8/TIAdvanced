
package IO;
import java.util.Scanner;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FIleIO {
    private File users;
    private File services;
    private File currentUser;
    private static final String CURRENTFOLDER = "Files/";

    public FIleIO() {
        this.users = new File(CURRENTFOLDER + "users.adv");
        this.services = new File(CURRENTFOLDER + "services.adv");
        
    }
    
    public void readUsers(){
        Scanner input;
        String line;
        try {
            input = new Scanner(users);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        while (input.hasNextLine()){
            line = input.nextLine();
            String[] parametro = line.split(":");
            
            
        }
    }
    
    public 
    
    
}
