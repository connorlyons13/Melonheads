package main;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nick
 */
public class Main {
    public static void main(String[] args) {
        
       // NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainjFrame.start();
                
                
                
                
            }
        });
        NativeInterface.initialize();
        NativeInterface.runEventPump();
    }
}
