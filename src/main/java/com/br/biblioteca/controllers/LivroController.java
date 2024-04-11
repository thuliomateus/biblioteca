package com.br.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.biblioteca.entidades.Livro;
import com.br.biblioteca.servicos.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        Livro livroCadastrado = livroService.cadastrarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarLivroPorId(id);
        return livro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> alterarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Optional<Livro> livroExistente = livroService.buscarLivroPorId(id);
        if (livroExistente.isPresent()) {
            livroAtualizado.setId(id);
            Livro livroAlterado = livroService.cadastrarLivro(livroAtualizado);
            return ResponseEntity.ok(livroAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarPorTitulo")
    public ResponseEntity<List<Livro>> buscarLivroPorTitulo(@RequestParam String titulo) {
        // Implementar a lógica de busca por título no serviço
        // Por simplicidade, vamos retornar uma lista vazia por enquanto
        return ResponseEntity.ok().body(new ArrayList<>());
    }
}
