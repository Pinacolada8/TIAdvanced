package tiadvanced;
import Pessoas.*;
import tiadvanced.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Sistema {
    Map <String,Pessoas>  Database = new HashMap <String, Pessoas>();
    ArrayList<Servicos> Servicos = new ArrayList();
    
    public boolean Login(String user, String senha){
        if(Database.containsKey(user)){
            if(senha.equals(Database.get(user).getSenha())){
                JOptionPane.showMessageDialog(null,"USUÁRIO LOGADO COM SUCESSO.","ERRO", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            JOptionPane.showMessageDialog(null,"SENHA INCORRETA","ERRO.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null,"USUARIO NÃO EXISTE","ERRO.", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public boolean Cadastro(String nome, String telefone, String endereco,  String email, String user, String tipo, String senha){
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
            case "Clientes":
                Novo = new Clientes(nome, endereco, telefone, email, user, senha, tipo);
                Database.put(user, Novo);
                break;
         }
         return true;
        }      
    }
    
    public boolean promover(String user){
        if(Database.containsKey(user)){
            Database.get(user).setTipo("ADM");
            return true;
        }
        return false;
    }
    
    public void cadastrarServico(String nome){
        Servicos novo = new Servicos(nome, false);
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
    
    public void precificarServico(String nome, double preco){
        if(buscarServico(nome) != null){
            buscarServico(nome).novoOrcamento(nome, preco);
        } 
    }
    
}
