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
        this.ativo = ativo;
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
    
    public Orcamento buscaOrcamento(String nome){
        Iterator it = orcamentos.iterator();
        Orcamento busca;
        while(it.hasNext()){
            busca = (Orcamento)it.next();
            if(busca.getNomeFuncionario().equals(nome)){
                return busca;
            }
        }
        return null;
    }
    
    public boolean novoOrcamento(String nome, double preco, boolean quiet){
        if(buscaOrcamento(nome) == null){
            Orcamento novo = new Orcamento(nome, preco);
            orcamentos.add(novo);
            if (!quiet){
                JOptionPane.showMessageDialog(null,"ORCAMENTO PARA O PRODUTO CADASTRADO COM SUCESSO.","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }            
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"FUNCIONARIO JÁ POSSUI ORCAMENTO PARA ESTE PRODUTO.","ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void alteraOrcamento(String nome, double novoPreco){
        Orcamento alterar;
        if(buscaOrcamento(nome) != null){
            alterar = buscaOrcamento(nome);
            alterar.setPreco(novoPreco);
            JOptionPane.showMessageDialog(null,"ORCAMENTO ALTERADO COM SUCESSO.","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"ERRO AO ALTERAR O ORCAMENTO.","ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deletaOrcamento(String nome){
        if(buscaOrcamento(nome) != null){
            orcamentos.remove(buscaOrcamento(nome));
            JOptionPane.showMessageDialog(null,"ORCAMENTO DELETADO COM SUCESSO.","SUCESSO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String toString(){
        return nomeServico;
    }
}
