package com.compasso.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findBynomeCompletoLikeIgnoreCase(String nome);
	
	List<Pessoa> findByNomeCompletoContainingIgnoreCase(String nome);
	
}
