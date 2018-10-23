package Pessoas;

public abstract class Pessoas {
    private String nome, endereco, telefone, email, usuario, senha, tipo;

    public Pessoas(String nome, String endereco, String telefone, String email, String usuario, String senha, String tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }
       
}
