/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
<<<<<<< Updated upstream
=======
import views.frmAdminHome;
import views.frmInvoice;
>>>>>>> Stashed changes
import views.frmLogin;

/**
 *
 * @author buddh
 */
public class RunApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }

<<<<<<< Updated upstream
        frmLogin login = new frmLogin();
        login.setUndecorated(true);
        login.setVisible(true);
=======
        new frmLogin().setVisible(true);
>>>>>>> Stashed changes
    }

}
