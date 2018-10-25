package tiadvanced;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Servicos {
    private String nomeServico;
    private boolean ativo;
    private ArrayList<Orcamento> orcamentos;
    
    public Servicos(String nome, boolean ativo){
        nomeServico = nome;
        ativo = ativo;
        orcamentos = new ArrayList();
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ArrayList<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(ArrayList<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }
    
    public boolean buscaOrcamento(String nome){
        Iterator it = orcamentos.iterator();
        Orcamento busca;
        while(it.hasNext()){
            busca = (Orcamento)it.next();
            if(busca.getNomeFuncionario().equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    public boolean novoOrcamento(String nome, double preco){
        if(!(buscaOrcamento(nome))){
            Orcamento novo = new Orcamento(nome, preco);
            orcamentos.add(novo);
            JOptionPane.showMessageDialog(null,"ORCAMENTO PARA O PRODUTO CADASTRADO COM SUCESSO.","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"FUNCIONARIO JÁ POSSUI ORCAMENTO PARA ESTE PRODUTO.","ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
