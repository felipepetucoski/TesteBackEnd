package com.example.sincronizacaoreceita;

import com.example.sincronizacaoreceita.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

  @Autowired private static ReceitaService service;

  public static void main(String[] args) {
    SpringApplication.run(SincronizacaoReceitaApplication.class, args);

   // File file = new File(args[0]);
   // service.readCsvAndUpdate(file);
    
    //Detalhe - Tentei fazer chamar este método através de uma api -  controller
    // Fiz também um controller pra mostrar algo na url e outro pra testar o spring data(Banco de dados)
  }
}
