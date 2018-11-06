
package IO;
import Pessoas.*;
import java.util.Scanner;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import tiadvanced.Sistema;

public class FIleIO {
    private File users;
    private File services;
    private static final String CURRENTFOLDER = "Files/";
    private Sistema system;

    public FIleIO(Sistema system) {  
        File filesFolder = new File(CURRENTFOLDER);
        
        if (!filesFolder.exists()){
            if (!filesFolder.mkdirs()){
                JOptionPane.showMessageDialog(null,"ERRO AO CRIAR PASTA DE ARQUIVOS","ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.users = new File(CURRENTFOLDER + "users.adv");
        
        this.services = new File(CURRENTFOLDER + "services.adv");
        
        
        this.system = system;
        
    }
    
    public void load(){
        readUsers();
    }
    
    public void save(){
        writeUsers();
        System.out.println("ARQUIVOS SALVOS");
    }
    
    private void readUsers(){
        Pessoas p;
        Scanner input;
        String line;
        try {
            users.createNewFile();
            input = new Scanner(users);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        while (input.hasNextLine()){
            line = input.nextLine();            
            line = line.replace(";","");
            
            String[] parametro = line.split(":");  
            
            if (parametro.length == 7){
                system.Cadastro(parametro[0], parametro[1], parametro[2], parametro[3], parametro[4], parametro[5], parametro[6], true);
            }       

        }
    }
    
    private void writeUsers(){
        Formatter saver;
        Iterator it;
        try {        
            saver = new Formatter(users);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        it = system.getDatabase().values().iterator();
        
        while (it.hasNext()){
            saver.format("%s\n", it.next().toString());
        }        
        
        saver.flush();
        saver.close();          
    }    
 
    
    
    private void readServices(){
        Scanner input;
        Scanner campo;
        String parametros[];
        int i;
        try {
            services.createNewFile();
            input = new Scanner(services);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        input.useDelimiter("\\{\n");
        
        while(input.hasNext()){
            campo = new Scanner(input.next());
            while (campo.hasNextLine()){
                System.out.println("LINE:");
                parametros = campo.nextLine().replace(";|}","").replace("}", "").split(":");
                for(i=0;i<parametros.length;i++){
                    System.out.print(i+"->");
                    System.out.println(parametros[i]);                    
                }                
            }
            System.out.println("ENDCAMPO");            
        }
        
        
    }
}
