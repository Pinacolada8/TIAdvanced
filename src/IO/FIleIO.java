
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
import tiadvanced.Pedidos;
import tiadvanced.Servicos;
import tiadvanced.Sistema;

public class FIleIO {
    private File usersFile;
    private File servicesFile;
    private static final String CURRENTFOLDER = "Files/";
    private Sistema system;
    private static Servicos servicoAtual = null;

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
        readServices();
    }
    
    public void save(){
        writeUsers();
        writeServices();
        writeOrders();
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
                if (parametro[6].equals("Cliente")){
                    readOrders(parametro[4]);//Le as Ordens deste Cliente
                }
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
        switch (parametros[0]){                
            case "N":
                servicoAtual = new Servicos(parametros[1],Boolean.parseBoolean(parametros[2]));   
                system.getServicos().add(servicoAtual);                
                break;
            case "Price":
                servicoAtual.novoOrcamento(parametros[1],Double.parseDouble(parametros[2]), true);
                break;
            default:
                break;
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
            saver.format("\tN:%s:%b{",servico.getNomeServico(),servico.isAtivo());
            while (itOrcamentos.hasNext()){
                orcamento = itOrcamentos.next();
                saver.format("\nPrice:%s:%s",orcamento.getNomeFuncionario(),orcamento.getPreco());
            }
            saver.format("\n}");
        }
        saver.flush();
        saver.close();    
    }
    
    private void readOrders(String ClientUsername){
        File ordersFile = new File(CURRENTFOLDER + ClientUsername +".advClient");
        Scanner input;
        String parametros[];
        Pedidos aux;
        try {
            ordersFile.createNewFile();
            input = new Scanner(ordersFile);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER OS PEDIDOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO LER OS PEDIDOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        while (input.hasNextLine()){
            parametros = input.nextLine().split(":");
            aux = new Pedidos(parametros[0],parametros[1],parametros[2],Double.parseDouble(parametros[3]));
            aux.setEstado(Boolean.parseBoolean(parametros[4]));
            ((Clientes)system.getDatabase().get(ClientUsername)).novoPedido(aux);            
        }
    }
    
    
    
    private void writeOrder(Clientes cliente){
        Formatter saver;
        File ordersFile = new File(CURRENTFOLDER + cliente.getUsuario() +".advClient");
        Iterator<Pedidos> it = cliente.getPedidos().iterator();
        Pedidos aux;
        try {        
            ordersFile.createNewFile();
            saver = new Formatter(ordersFile);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR PEDIDOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR PEDIDOS","ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        while (it.hasNext()){
            aux = it.next();
            saver.format("%s:%s:%s:%s:%s\n", aux.getComprador(),aux.getFuncionario(),aux.getServico(),aux.getValor(),aux.getEstado());
        }
        
        saver.flush();
        saver.close(); 
    }
    
    private void writeOrders(){
        Iterator<Clientes> it = system.getClientes().iterator();
        
        while (it.hasNext()){
            writeOrder(it.next());
        }
    }
    
}
