package com.springapp.projeto.repository;

import org.springframework.data.repository.CrudRepository;

import com.springapp.projeto.model.Vacina;

public interface VacinaRepository extends CrudRepository<Vacina, String> {
	Vacina findByCodigoVacina(long codigoVacina);
}
