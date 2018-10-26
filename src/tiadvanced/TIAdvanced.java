package tiadvanced;

import IO.FIleIO;


public class TIAdvanced {

    public static void main(String[] args) {
        Sistema system = new Sistema();
        FIleIO  data = new FIleIO(system);
        data.load();    
        
        system.inicia();
        
        data.save();        
    }
    
}
