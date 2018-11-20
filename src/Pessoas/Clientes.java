package Pessoas;
import java.util.ArrayList;
import tiadvanced.Pedidos;

public class Clientes extends Pessoas{

    public static final String ID = "Cliente";
    private ArrayList<Pedidos> pedidos;
    public Clientes(String nome, String endereco, String telefone, String email, String usuario, String senha, String tipo) {
        super(nome, endereco, telefone, email, usuario, senha, tipo);
        pedidos = new ArrayList();
    }
    
    public void novoPedido(Pedidos pedido){
        pedidos.add(pedido);
    }

    public ArrayList<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }
    
    
       
}
