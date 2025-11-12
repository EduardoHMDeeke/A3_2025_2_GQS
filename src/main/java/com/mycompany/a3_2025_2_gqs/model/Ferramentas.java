package com.mycompany.a3_2025_2_gqs.backend.model;

/*


author @Ryan / @Caio
*/
public class Ferramentas {

    private int id;
    private String nome;
    private String marca;
    private int estaEmprestada = 0;
    private String valor; //Para buscar valor use getPreco()
   
    
    

    public Ferramentas() {
    }

    public Ferramentas(String nome, String marca, String valor) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
       
    }

    public Ferramentas(int id, String nome, String marca, String valor) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    //POR ALGUM MOTIVO DDESCONHECIDO getValor tava bugando todo o código, então coloquei getPreco
    public String getPreco() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
   

    
    
    // Cadastra nova Ferramenta
   

    // Deleta uma Ferramenta específico pelo seu campo ID
   
    // Edita uma Ferramenta específico pelo seu campo ID
    
    // procura o INDICE de objeto da MinhaLista que contem o ID enviado.
  

    // carrega dados de Ferramenta específico pelo seu ID
    
    
    // retorna o maior ID da nossa base de dados

    public int getEstaEmprestada() {
        return estaEmprestada;
    }

    public void setEstaEmprestada(int estaEmprestada) {
        this.estaEmprestada = estaEmprestada;
    }

   
}
