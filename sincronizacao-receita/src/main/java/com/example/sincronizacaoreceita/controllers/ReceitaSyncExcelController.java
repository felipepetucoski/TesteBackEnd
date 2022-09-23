package com.example.sincronizacaoreceita.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.sincronizacaoreceita.entities.ReceitaSync;
import com.example.sincronizacaoreceita.helper.ExcelHelper;
import com.example.sincronizacaoreceita.repository.ReceitaSyncRepository;
import com.example.sincronizacaoreceita.service.ReceitaService;

/**
 *
 * A sample greetings controller to return greeting text
 */
/*ControllerResponsável por interceptar os dados de uma aplicação*/
/*Tudo que vim de uma tela/aplicativo do app vai cair num controller mapeado*/
@CrossOrigin("http://localhost:8005")
@Controller
@RequestMapping("/api/excel")
public class ReceitaSyncExcelController {
	@Autowired/*IC _ CD  ou CDI injeção de dependência*/
	private ReceitaSyncRepository receitaSyncRepository;
	

	/*Neste end point tentei simular um controller que intercepta um arquivo e manda o arquivo pro método
	na classe Receita/Service que já estava pronto pra resolver algum problema*/
	  @PostMapping("/upload")
	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	    	  File arquivo = null;
	    	  file.transferTo(arquivo);
	    	  ReceitaService.readCsvAndUpdate(arquivo);

	        message = "Enviado com sucesso: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	        message = "Não poder ler o arquivo: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	    }

	    message = "Selecione um arquivo em excel!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	  }
    // Olhar tbm por gentileza o outro controller, que fiz o teste pra salvar no banco de dados .
   
}
