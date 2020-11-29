/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectogimnasio;

import GUI.Login;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matías
 */
public class ProyectoGimnasio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> new Login());
    }
    
}
