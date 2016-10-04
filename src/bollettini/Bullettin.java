/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettini;

import org.opencv.core.Core;
/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Bullettin {

    /**
     * @param args the command line arguments
     */
    
    private static BullettinCompiler compiler;
    
    public static void main(String[] args) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        compiler = new BullettinCompiler();
                
    }
    
}
