package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.entity.Categoria;
import br.com.fiap.spring_mvc.entity.Livro;
import br.com.fiap.spring_mvc.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("opcoes", Arrays.asList(Categoria.values()));
        model.addAttribute("livro", new Livro());
        return "livroCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro (@Valid Livro livro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("opcoes", Arrays.asList(Categoria.values()));
            model.addAttribute("livro", livro);
            return "livroCadastro";
        }
        livroService.createLivro(livro);
        return listarLivros(model);
    }
}
