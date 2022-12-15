package FONTS.src;

import FONTS.src.Interface.PresentationController;
import java.io.FileNotFoundException;

/**
 * @file Main.java
 * @brief Class <em>Main</em>
 */

/**
 * @brief Main class of the project
 *
 * @author Víctor Mena Doz
 */

public class Main {

    /**
     * @brief Función inicial que lanza la interfaz (en este caso por consola)
     */
    public static void main(String[] args) throws FileNotFoundException {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    PresentationController ctrlPres = PresentationController.getInstance();
                    try {
                        ctrlPres.run();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }
}
