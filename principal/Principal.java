/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import DAO.Conexao;
import View.TelaPrincipal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Principal {

    public static void main(String[] args) {
        TelaPrincipal TP = new TelaPrincipal();
        TP.setVisible(true);
       Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH) + 1; // Mês começa em 0, então adicionamos 1
        int ano = calendar.get(Calendar.YEAR);

        System.out.println("Dia: " + dia);
        System.out.println("Mês: " + mes);
        System.out.println("Ano: " + ano);
        
     
        
     //   System.out.println(data);
    }
}
