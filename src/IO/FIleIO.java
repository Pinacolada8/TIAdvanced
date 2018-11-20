
package IO;
import Pessoas.*;
import java.util.Scanner;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tiadvanced.Orcamento;
import tiadvanced.Servicos;
import tiadvanced.Sistema;

public class FIleIO {
    private File usersFile;
    private File servicesFile;
    private static final String CURRENTFOLDER = "Files/";
    private Sistema system;
    private static String servicoAtual = "";

    public FIleIO(Sistema system) {  
        File filesFolder = new File(CURRENTFOLDER);
        
        if (!filesFolder.exists()){
            if (!filesFolder.mkdirs()){
                JOptionPane.showMessageDialog(null,"ERRO AO CRIAR PASTA DE ARQUIVOS","ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.usersFile = new File(CURRENTFOLDER + "users.adv");
        
        this.servicesFile = new File(CURRENTFOLDER + "services.adv");
        
        
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
            usersFile.createNewFile();
            input = new Scanner(usersFile);
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
            saver = new Formatter(usersFile);
            usersFile.createNewFile();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
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
            servicesFile.createNewFile();
            input = new Scanner(servicesFile);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER SERVICOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER SERVICOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        input.useDelimiter("\\{\n|\\}\n");
        
        while(input.hasNext()){ 
            campo = new Scanner(input.next());
            while (campo.hasNextLine()){                
                parametros = campo.nextLine().replace(";","").replace("\t", "").split(":");
                if (parametros[0].equals("}")) break;                
                servicesParameters(parametros);
            }                    
        }       
        
    }
    
    private void servicesParameters (String[] parametros){
        int i;
        
        if (parametros[0].equals("N")) {
            servicoAtual = parametros[1];
            system.s
            return;
        } 
        
        for (i=0;i<parametros.length;i++){
            switch (parametros[i]){
                
            }
        }
    }
    
    private void writeServices(){ 
        Servicos servico;
        Orcamento orcamento;
        Iterator<Servicos> it = system.getServicos().iterator();  
        Iterator<Orcamento> itOrcamentos;
        Formatter saver;
        
        try {        
            servicesFile.createNewFile();
            saver = new Formatter(servicesFile);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR USUARIOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        while (it.hasNext()){
            servico = it.next();
            itOrcamentos = servico.getOrcamentos().iterator();
            saver.format("\t%s:%b{",servico.getNomeServico(),servico.isAtivo());
            while (itOrcamentos.hasNext()){
                orcamento = itOrcamentos.next();
                saver.format("\nPrice:%s:%f",orcamento.getNomeFuncionario(),orcamento.getPreco());
            }
            saver.format("\n}");
        }
        saver.flush();
        saver.close();    
    }
}
