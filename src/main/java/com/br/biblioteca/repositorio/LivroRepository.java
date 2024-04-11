package com.br.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.biblioteca.entidades.Livro;
import java.util.List;


public interface LivroRepository extends JpaRepository<Livro, Long> {
    public List<Livro> findByTitulo(String titulo);
}
