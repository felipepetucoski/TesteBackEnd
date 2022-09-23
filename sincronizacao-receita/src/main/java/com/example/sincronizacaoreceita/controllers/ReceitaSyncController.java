package com.example.sincronizacaoreceita.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sincronizacaoreceita.entities.ReceitaSync;
import com.example.sincronizacaoreceita.repository.ReceitaSyncRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController/*Responsável por interceptar os dados de uma aplicação*/
/*Tudo que vim de uma tela/aplicativo do app vai cair num controller mapeado*/
public class ReceitaSyncController {
	@Autowired/*IC _ CD  ou CDI injeção de dependência*/
	private ReceitaSyncRepository receitaSyncRepository;

	
	//Testando END POINT e pra ver se o spring está funcionando
    @RequestMapping(value = "/mostrarNomeSicredi/{name}", method = RequestMethod.GET)/*Mapeamento end point*/
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "A vaga já minha  " + name + ", rs!";
    }
    
    //TESTANDO END POINT e consequentemente salvar no banco de dados - Salvar no banco
    /*
     Usei o banco mysql, pois era o banco de dados que tinha na minha máquina. Mais, configurando no application.properties
     pode-se usar qualquer banco de dados.
     */
    @RequestMapping(value="/testeSalvarSicredi/{conta}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String metodoRetornoOlaMundo(@PathVariable String conta){
    	
    	ReceitaSync receitaSync = new ReceitaSync();
    	receitaSync.setAgencia("12345");
    	receitaSync.setConta(conta);
    	receitaSync.setSaldo("30");
    	receitaSyncRepository.save(receitaSync);
    	
    	return "Salvar com Sucesso conta: "+conta;
    	
    }
}
