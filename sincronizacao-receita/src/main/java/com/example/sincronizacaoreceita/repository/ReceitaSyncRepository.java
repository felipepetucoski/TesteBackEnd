package com.example.sincronizacaoreceita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sincronizacaoreceita.entities.ReceitaSync;


@Repository/*Banco de dados desta entidade - Felipe*/
public interface ReceitaSyncRepository extends JpaRepository<ReceitaSync, Long>{

}
