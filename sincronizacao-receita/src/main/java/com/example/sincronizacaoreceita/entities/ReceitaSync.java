package com.example.sincronizacaoreceita.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity/*Anotação da entidade/tabela banco de dados - Felipe*/
@SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita", allocationSize = 1, initialValue = 1)/*Sequência para gerar PK dinâmica - Felipe*/
public class ReceitaSync implements Serializable/*Colocado por mim - Felipe*/ {

	private static final long serialVersionUID = 1L;/*Colocado por mim - Felipe*/

	@Id/*Colocado por mim - PK - Felipe*/
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_receita")/*Colocado por mim - Felipe*/
	private Long id;

	@CsvBindByName
	String agencia;

	@CsvBindByName
	String conta;

	@CsvBindByName
	String saldo;

	@CsvBindByName
	String status;
}
/*
Detalhe: Utilizei o eclipse pra fazer o projeto, tive que instalar o plugin do lombok no mesmo para reconhecer 
os getters e setters automático desta biblioteca
*/