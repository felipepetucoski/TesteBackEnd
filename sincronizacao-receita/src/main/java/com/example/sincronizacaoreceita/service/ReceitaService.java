package com.example.sincronizacaoreceita.service;

import com.example.sincronizacaoreceita.dto.ReceitaSyncDTO;
import com.example.sincronizacaoreceita.entities.ReceitaSync;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReceitaService {

  private static Logger logger =
      java.util.logging.Logger.getLogger(String.valueOf(ReceitaService.class));

  public static boolean atualizarConta(String agencia, String conta, String status) {

    // Formato agencia: 0000
    if (agencia == null || agencia.length() != 4) {
      return false;
    }

    // Formato conta: 000000
    if (conta == null || conta.length() != 6) {
      return false;
    }

    // Tipos de status validos:
    List tipos = new ArrayList();
    tipos.add("A");
    tipos.add("I");
    tipos.add("B");
    tipos.add("P");

    if (status == null || !tipos.contains(status)) {
      return false;
    }

    // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
    long wait = Math.round(Math.random() * 4000) + 1000;

    try {
      Thread.sleep(wait);
    } catch (InterruptedException e) {
      logger.severe("Erro na thread" + e.getMessage());
    }

    // Simula cenario de erro no serviço (0,1% de erro)
    long randomError = Math.round(Math.random() * 1000);

    if (randomError == 500) {
      throw new RuntimeException("Erro na obtenção dos dados");
    }

    return true;
  }

  public static void readCsvAndUpdate(File file) {

    List<ReceitaSync> inputData;
    try {
      String currentPath = new java.io.File(".").getCanonicalPath();
      logger.info("Lendo Arquivo!!!");

      inputData =
          new CsvToBeanBuilder(new FileReader(file))
              .withSeparator(';')
              .withSkipLines(0)
              .withType(ReceitaSync.class)
              .build()
              .parse();

      if (inputData.size() != 0) {
        Writer writer = new FileWriter(currentPath + "\\retornoReceita.csv");

        StatefulBeanToCsv beanToCsv =
            new StatefulBeanToCsvBuilder<ReceitaSync>(writer)
                .withSeparator(';')
                .withApplyQuotesToAll(false)
                .build();

        inputData.forEach(
            conta -> {
              boolean valida;
              valida =
                  atualizarConta(
                      conta.getAgencia(), conta.getConta().replace("-", ""), conta.getStatus());
              try {
                beanToCsv.write(fromEntity(conta, valida));
              } catch (CsvDataTypeMismatchException e) {
                logger.severe("Erro na leitura dos dados");
              } catch (CsvRequiredFieldEmptyException e) {
                logger.severe("Campo obrigatorios vazios");
              }
            });
        writer.close();
        logger.info("Arquivo criado!!!");
      } else {
        logger.severe("Arquivo vazio");
      }
    } catch (IOException e) {
      logger.severe("Arquivo Invalido");
    }
  }

  public static ReceitaSyncDTO.ReceitaSyncDTOBuilder fromEntity(
      ReceitaSync receitaSync, boolean valida) {
    return ReceitaSyncDTO.builder()
        .agencia(receitaSync.getAgencia())
        .conta(receitaSync.getConta())
        .saldo(receitaSync.getSaldo())
        .status(receitaSync.getStatus())
        .valida(valida);
  }
}
