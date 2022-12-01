package FONTS.src.Interface;

import FONTS.src.Domain.Controllers.DomainController;

import javax.swing.*;
import java.util.Scanner;

public class PresentationController {
    private DomainController ctrlDomain;

    private Scanner reader = new Scanner(System.in);

    private JFrame mainFrame = new JFrame();

    private static PresentationController ctrlPresent;

    public static PresentationController getInstance() {
        if(ctrlPresent == null) {
            ctrlPresent = new PresentationController();
        }
        return ctrlPresent;
    }

    private MainView main;

    /**
     * @brief Default create of PresentationController
     */
    public PresentationController() {
        ctrlDomain = new DomainController();
    }

    public void run() {
        //...
        toMain();
    }

    public void toMain() {

    }

    public void toAddNewDocument() {

    }

    public void toModifyContent() {

    }
}
