package com.compasso.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	List<Cidade> findByEstadoCodigo(Long estadoCodigo);
	
	List<Cidade> findByNomeContainingIgnoreCase(String nome);
	
}
