package tiadvanced;
import Pessoas.*;
import tiadvanced.*;
import IO.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Sistema{
    Interface inter;
    String usuarioLogado;
    Map <String,Pessoas>  Database;
    ArrayList<Servicos> Servicos;
    
    public Sistema(){
        inter = new Interface();
        usuarioLogado = "";
        Database = new HashMap <String,Pessoas>();
        Servicos = new ArrayList();
    }
    
    public void inicia(){
        inter.setSystem(this);
        inter.inicio();        
    }
    
    public boolean Login(String user, String senha){
        if(Database.containsKey(user)){
            if(senha.equals(Database.get(user).getSenha())){
                JOptionPane.showMessageDialog(null,"USUÁRIO LOGADO COM SUCESSO.","ERRO", JOptionPane.INFORMATION_MESSAGE);
                usuarioLogado = user;
                return true;
            }
            JOptionPane.showMessageDialog(null,"SENHA INCORRETA","ERRO.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null,"USUARIO NÃO EXISTE","ERRO.", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public boolean Cadastro(String nome, String endereco, String telefone, String email, String user, String senha, String tipo){
        Pessoas Novo;
        if(Database.containsKey(user)){
            JOptionPane.showMessageDialog(null,"USUARIO JA ESTÁ SENDO UTILIZADO","ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
         switch(tipo){
            case "Funcionario":
                Novo = new Funcionario(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
            case "Cliente":
                Novo = new Clientes(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
            case "ADM":
                Novo = new Administrador(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
            default:
                JOptionPane.showMessageDialog(null,"TIPO INEXISTENTE","ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
         }
         JOptionPane.showMessageDialog(null,"USUARIO CADASTRADO COM SUCESSO.","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
         return true;
        }      
    }
    
    public boolean Cadastro(String nome, String endereco, String telefone, String email, String user, String senha, String tipo,boolean quiet){
        Pessoas Novo;
        if(Database.containsKey(user)){
            if (!quiet){
                JOptionPane.showMessageDialog(null,"USUARIO JA ESTÁ SENDO UTILIZADO","ERRO", JOptionPane.ERROR_MESSAGE);
            }            
            return false;
        }
        else{
         switch(tipo){
            case "Funcionario":
                Novo = new Funcionario(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
            case "Cliente":
                Novo = new Clientes(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
            case "ADM":
                Novo = new Administrador(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
         }
         if (!quiet){
            JOptionPane.showMessageDialog(null,"USUARIO CADASTRADO COM SUCESSO.","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
         }         
         return true;
        }      
    }
    
    public int getNivelAcesso(){

        switch(Database.get(usuarioLogado).getTipo()){            
            case Funcionario.ID:
                return 1;                
            case Administrador.ID:
                return 2;
            case Clientes.ID:
            default:
                return 0;                
        }
    }
    
    public boolean promover(String user){
        if(Database.containsKey(user)){
            Database.get(user).setTipo("ADM");
            JOptionPane.showMessageDialog(null,"USUARIO PROMOVIDO COM SUCESSO","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        JOptionPane.showMessageDialog(null,"ERRO AO PROMOVER USUARIO.","ERRO", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public void cadastrarServico(String nome){
        Servicos novo = new Servicos(nome, false);
        Servicos.add(novo);
        JOptionPane.showMessageDialog(null,"SERVICO CADASTRADO COM SUCESSO.","SUCESSO", JOptionPane.ERROR_MESSAGE);
    }
    
    public Servicos buscarServico(String nome){
        Iterator it = Servicos.iterator();
        Servicos busca;
        while(it.hasNext()){
            busca = (Servicos)it.next();
            if(busca.getNomeServico().equals(nome)){
                return busca;
            }
        }
        
        return null;
    }
    
    public void modificarServico(String nomeAtual, String novoNome){
        if(buscarServico(nomeAtual)!= null){
            buscarServico(nomeAtual).setNomeServico(novoNome);
        }
    }
    
    public void DeletarServico(String nome){
        if(buscarServico(nome) != null){
            Servicos.remove(buscarServico(nome));
        }
    }
    
    public void precificarServico(String nome, double preco){
        if(buscarServico(nome) != null){
            buscarServico(nome).novoOrcamento(usuarioLogado, preco);
        } 
    }
    
    public void deletarOrcamento(String nomeServico){
        buscarServico(nomeServico).deletaOrcamento(usuarioLogado);
    }
    
    public void alterarOrcamento(String nomeServico, double novoPreco){
        buscarServico(nomeServico).alteraOrcamento(usuarioLogado, novoPreco);
    }

    public Map<String, Pessoas> getDatabase() {
        return Database;
    }

    public ArrayList<Servicos> getServicos() {
        return Servicos;
    }
   
    
}
