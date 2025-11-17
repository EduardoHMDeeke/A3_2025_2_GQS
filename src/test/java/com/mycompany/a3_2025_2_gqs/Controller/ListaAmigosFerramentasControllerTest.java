package com.mycompany.a3_2025_2_gqs.Controller;

import com.mycompany.a3_2025_2_gqs.Model.Amigos;
import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para métodos auxiliares da classe ListaAmigosFerramentasController
 */
public class ListaAmigosFerramentasControllerTest {

    private ListaAmigosFerramentasController controller;
    private ArrayList<Amigos> listaAmigos;
    private ArrayList<Ferramentas> listaFerramentas;

    @BeforeEach
    void setUp() {
        // Criar controller com view null (só testamos métodos privados)
        controller = new ListaAmigosFerramentasController(null);
        
        listaAmigos = new ArrayList<>();
        listaAmigos.add(criarAmigo(1, "João"));
        listaAmigos.add(criarAmigo(2, "Maria"));
        listaAmigos.add(criarAmigo(3, "Pedro"));

        listaFerramentas = new ArrayList<>();
        listaFerramentas.add(criarFerramenta(1, "Martelo"));
        listaFerramentas.add(criarFerramenta(2, "Chave"));
        listaFerramentas.add(criarFerramenta(3, "Serrote"));
    }

    private Amigos criarAmigo(int id, String nome) {
        Amigos amigo = new Amigos();
        amigo.setId(id);
        amigo.setNome(nome);
        amigo.setEmail(nome.toLowerCase() + "@email.com");
        amigo.setTelefone("11999999999");
        return amigo;
    }

     private Ferramentas criarFerramenta(int id, String nome) {
        Ferramentas ferramenta = new Ferramentas();
        ferramenta.setId(id);
        ferramenta.setNome(nome);
        ferramenta.setMarca("Marca");
        ferramenta.setValor("10.00");
        return ferramenta;
    }

    @Test
    void testBusqueAmigoEncontrado() throws Exception {
        Method method = ListaAmigosFerramentasController.class.getDeclaredMethod(
            "busqueAmigo", int.class, ArrayList.class);
        method.setAccessible(true);
        
        Amigos resultado = (Amigos) method.invoke(controller, 2, listaAmigos);
        
        assertNotNull(resultado);
        assertEquals(2, resultado.getId());
        assertEquals("Maria", resultado.getNome());
    }
}

