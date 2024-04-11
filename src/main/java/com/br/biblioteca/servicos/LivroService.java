package com.br.biblioteca.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.biblioteca.entidades.Livro;
import com.br.biblioteca.repositorio.LivroRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrarLivro(Livro livro) {
        //usar try catch para tratamento de exceçâo
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
