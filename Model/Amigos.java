/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Guilherme
 */
public class Amigos{
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
    private String diaDoEmprestimo;

    public Amigos() {
    }

    public Amigos(String nome, String email, String telefone, int idade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public Amigos(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    
 
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDiaDoEmprestimo() {
        return diaDoEmprestimo;
    }

    public void setDiaDoEmprestimo(String diaDoEmprestimo) {
        this.diaDoEmprestimo = diaDoEmprestimo;
    }

    @Override
    public String toString() {
        return "Amigos{" + "nome=" + nome + '}';
    }
    
    
}
