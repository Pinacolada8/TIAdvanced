package tiadvanced;

public class Pedidos {
    private String Comprador, Funcionario, Servico;
    boolean estado;
    private double Valor;
    
    public Pedidos(String Comprador, String Funcionario, String Servico, double Valor){
        this.Comprador = Comprador;
        this.Funcionario = Funcionario;
        this.Servico = Servico;
        this.Valor = Valor;
        estado = false;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(String Funcionario) {
        this.Funcionario = Funcionario;
    }

    public String getServico() {
        return Servico;
    }

    public void setServico(String Servico) {
        this.Servico = Servico;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }
    
    public void setEstado(boolean estado){
       this.estado = estado;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
}
