package tiadvanced;
import Pessoas.*;
import tiadvanced.*;
import IO.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Sistema{
    Interface inter;
    String usuarioLogado;
    Map <String,Pessoas>  Database;
    ArrayList<Servicos> Servicos;
    FIleIO  data;
    
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
    
    public void deslogar(){
        usuarioLogado = "";
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
    
    public void modificarServico(List itens){
        System.out.println(itens);
        Iterator it = itens.iterator();
        String nomeAtual, novoNome;
        while(it.hasNext()){
            nomeAtual = (String)it.next();
            if(buscarServico(nomeAtual)!= null){
                novoNome = JOptionPane.showInputDialog(null, "Novo nome para " + nomeAtual, "Renomear", JOptionPane.QUESTION_MESSAGE);
                buscarServico(nomeAtual).setNomeServico(novoNome);
            }            
        }                
    }
    
    public void validarServico(List itens){
        Iterator it = itens.iterator();
        String nome;
        while(it.hasNext()){
            nome = (String)it.next();
            if(buscarServico(nome) != null){
                buscarServico(nome).setAtivo(true);
            }
        }        
    }
    
    public void DeletarServico(List itens){
        Iterator it = itens.iterator();
        String nome;
        while(it.hasNext()){
            nome = (String)it.next();
            if(buscarServico(nome) != null){
            Servicos.remove(buscarServico(nome));
            }
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
    
    public void alterarOrcamento(String nomeServico){
        double novoPreco;
        novoPreco = Double.parseDouble(JOptionPane.showInputDialog(null, "Novo valor para " + nomeServico, "Alterar Valor", JOptionPane.QUESTION_MESSAGE));
        buscarServico(nomeServico).alteraOrcamento(usuarioLogado, novoPreco);
    }
    
    public ArrayList<String> getServicosValidos(){
        ArrayList<String> validos = new ArrayList();
        Iterator it = Servicos.iterator();
        Servicos busca;
        while(it.hasNext()){
            busca = (Servicos)it.next();
            if(busca.isAtivo()){
                validos.add(busca.getNomeServico());
            }
        }
        return validos;
    }
    
    public String[] getServicosInvalidos(){
        Iterator it = Servicos.iterator();
        Servicos busca;
        int contador = 0;
        while(it.hasNext()){
            busca = (Servicos)it.next();
            if(!busca.isAtivo()){
                contador++;
            }
        }
        String[] retorno = new String[contador];
        it = Servicos.iterator();//Resetando o Iterator
        contador = 0;
        while(it.hasNext()){
           busca = (Servicos)it.next();
            if(!busca.isAtivo()){
                retorno[contador] = busca.getNomeServico();
                contador++;
            } 
        }
        return retorno;
    }
    
    public ArrayList<String> getValidosNaoOrcamentados(){
        ArrayList<String> naoOrcamentados = new ArrayList();
        Iterator it = Servicos.iterator();
        Servicos busca;
        while(it.hasNext()){
            busca = (Servicos)it.next();
            Iterator it2 = busca.getOrcamentos().iterator();
            boolean orcamentado = false;
            while(it2.hasNext()){
                Orcamento procura = (Orcamento)it2.next();
                if(procura.getNomeFuncionario().equals(usuarioLogado)){
                    orcamentado = true;
                }
            }
            if(orcamentado == false){
                naoOrcamentados.add(busca.getNomeServico());
            }
            orcamentado = false;
        }
        return naoOrcamentados;
    }
    
    public String[] getOrcamentosFunc(){
        ArrayList<String> parcial = new ArrayList();
        String[] retorno;
        Iterator it = Servicos.iterator();
        Servicos busca;
        while(it.hasNext()){//Itera pelos Servicos
            busca = (Servicos)it.next();
            Iterator it2 = busca.getOrcamentos().iterator();
            while(it2.hasNext()){//Itera pelos Orcamentos de cada servico em busca de orcamentos do usuario logado.
                Orcamento procura = (Orcamento)it2.next();
                if(procura.getNomeFuncionario().equals(usuarioLogado)){
                    String array = busca.getNomeServico() + "-R$:" + procura.getPreco();
                    parcial.add(array);
                }
            }
        }
        retorno = parcial.toArray(new String[1]);
        return retorno;
    }
    
    public Servicos[] getValidosOrcamentados(){
        ArrayList<Servicos> servicos = new ArrayList();
        Servicos[] retorno;
        Iterator it = Servicos.iterator();
        Servicos busca;
        while(it.hasNext()){
            busca = (Servicos)it.next();
            if(busca.getOrcamentos().size() > 0){
                servicos.add(busca);
            }
        }
        retorno = servicos.toArray(new Servicos[1]);
        return retorno;
    }
    
    
    public void realizarPedido(String comprador, String funcionario, String servico, double valor){
        Pedidos pedido = new Pedidos(comprador, funcionario, servico, valor);
        ((Clientes)Database.get(comprador)).novoPedido(pedido);       
    }
    
    public void executarServico(String nomeServico){
        buscarServico(nomeServico).setAtivo(true);
    }
    
    public Pedidos[] getPedidosCliente(){
        Pedidos[] pedidos = ((Clientes)Database.get(usuarioLogado)).getPedidos().toArray(new Pedidos[1]);
        return pedidos;
    }
    
    public Pedidos[] getPedidosFuncionario(){
        
    }

    public Map<String, Pessoas> getDatabase() {
        return Database;
    }

    public ArrayList<Servicos> getServicos() {
        return Servicos;
    }

    public void setData(FIleIO data) {
        this.data = data;
    }

    public FIleIO getData() {
        return data;
    }
    
   
}
