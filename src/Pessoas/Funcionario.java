package Pessoas;

import java.util.ArrayList;
import tiadvanced.Pedidos;

public class Funcionario extends Pessoas{

    public static final String ID = "Funcionario";
    private ArrayList<Pedidos> pendentes;
    public Funcionario(String nome, String endereco, String telefone, String email, String usuario, String senha, String tipo) {
        super(nome, endereco, telefone, email, usuario, senha, tipo);
        pendentes = new ArrayList();
    }
    
    public void novoPedido(){
        
    }
   
}
