/*
 * SeniorProj1App.java
 */

package seniorproj1;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class SeniorProj1App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new SeniorProj1View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of SeniorProj1App
     */
    public static SeniorProj1App getApplication() {
        return Application.getInstance(SeniorProj1App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(SeniorProj1App.class, args);
    }
}
