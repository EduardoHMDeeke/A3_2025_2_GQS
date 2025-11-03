package com.mycompany.a3_2025_2_gqs.Model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author NEX
 */
public class Emprestimos {
    private int id;
    private int idAmigos;
    private int idFerramentas;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int estaEmprestada;
    private LocalDate dataDevolvida;

    public Emprestimos() {
    }

    public Emprestimos(int id, int idAmigos, int idFerramentas, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.idAmigos = idAmigos;
        this.idFerramentas = idFerramentas;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        
    }

    public Emprestimos(int idAmigos, int idFerramentas, LocalDate dataEmprestimo, LocalDate dataDevolucao, int estaEmprestada) {
        this.idAmigos = idAmigos;
        this.idFerramentas = idFerramentas;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.estaEmprestada = estaEmprestada;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAmigos() {
        return idAmigos;
    }

    public void setIdAmigos(int idAmigos) {
        this.idAmigos = idAmigos;
    }

    public int getIdFerramentas() {
        return idFerramentas;
    }

    public void setIdFerramentas(int idFerramentas) {
        this.idFerramentas = idFerramentas;
    }

    public int getEstaEmprestada() {
        return estaEmprestada;
    }

    public void setEstaEmprestada(int estaEmprestada) {
        this.estaEmprestada = estaEmprestada;
    }

    public LocalDate getDataDevolvida() {
        return dataDevolvida;
    }

    public void setDataDevolvida(LocalDate dataDevolvida) {
        this.dataDevolvida = dataDevolvida;
    }

    
    
    
}
