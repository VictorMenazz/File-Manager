package FONTS.src;

import FONTS.src.Interface.PresentationController;
import java.io.FileNotFoundException;

public class Main {

    /**
     * @brief Función inicial que lanza la interfaz (en este caso por consola)
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (Integer.parseInt(args[0]) == 1)
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    PresentationController ctrlPres = PresentationController.getInstance();
                    ctrlPres.run();
                }
            });
    }
}
