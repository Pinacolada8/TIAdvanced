package tiadvanced;


public class TIAdvanced {

    public static void main(String[] args) {
        Sistema system = new Sistema();
        system.Cadastro("Teste", "1234-5678", "rua teste", "teste@teste", "teste", "ADM", "123456");
        system.inicia();
    }
    
}
