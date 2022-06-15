/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.frmLogin;

/**
 *
 * @author shehan
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        
       frmLogin frm_login =  new frmLogin();
       frm_login.setUndecorated(true);
       frm_login.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
       frm_login.setVisible(true);
    }

}
