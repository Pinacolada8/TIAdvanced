package tiadvanced;

import IO.FIleIO;


public class TIAdvanced {

    public static void main(String[] args) {
        Sistema system = new Sistema();
        FIleIO  data = new FIleIO(system);
        system.Cadastro("Teste", "rua teste", "1234-5678", "teste@teste", "teste", "123456", "ADM");
        
        system.inicia();
        
        data.save();        
    }
    
}
