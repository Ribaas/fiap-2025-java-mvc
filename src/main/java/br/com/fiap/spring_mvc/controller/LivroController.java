package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.entity.Categoria;
import br.com.fiap.spring_mvc.entity.Livro;
import br.com.fiap.spring_mvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @GetMapping("/lista")
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.readLivros();
        model.addAttribute("listaLivros", livros);
        return "livroLista";
    }

    @GetMapping("/cadastro")
    public String cadastroLivros(Model model) {
        List< Map<String, Object>> opcoes = new ArrayList<>();
        Arrays.stream(Categoria.values()).forEach(categoria -> {
            Map<String, Object> opcao = new HashMap<>();
            opcao.put("label", categoria.getDescricao());
            opcao.put("value", categoria);
            opcoes.add(opcao);
        });
        model.addAttribute("opcoes", opcoes);
        model.addAttribute("livro", new Livro());
        return "livroCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro (Livro livro, Model model) {
        livroService.createLivro(livro);
        return listarLivros(model);
    }
}
