package tiadvanced;

public class Orcamento {
    private String nomeFuncionario;
    private double preco;
    
    public Orcamento(String nome, double preco){
        this.nomeFuncionario = nome;
        this.preco = preco;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return nomeFuncionario + " - " + preco;
    }
}
