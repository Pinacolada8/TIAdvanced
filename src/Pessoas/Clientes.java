package Pessoas;
import java.util.ArrayList;
import tiadvanced.Servicos;

public class Clientes extends Pessoas{

    public static final String ID = "Cliente";
    private ArrayList<Servicos> pedidos;
    public Clientes(String nome, String endereco, String telefone, String email, String usuario, String senha, String tipo) {
        super(nome, endereco, telefone, email, usuario, senha, tipo);
        pedidos = new ArrayList();
    }
    
    public void fazerPedido(){
        
    }
       
}
