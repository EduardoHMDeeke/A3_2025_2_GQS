/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.mycompany.a3_2025_2_gqs;

import javax.swing.SwingUtilities;

import com.mycompany.a3_2025_2_gqs.frontend.view.TelaPrincipal;

/**
 * Ponto de entrada da aplicação.
 */
public final class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
