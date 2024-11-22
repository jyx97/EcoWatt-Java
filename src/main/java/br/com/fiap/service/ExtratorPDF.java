package br.com.fiap.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import br.com.fiap.beans.ContaEnergia;

public class ExtratorPDF {

    public ContaEnergia processarPDF(File pdf) throws IOException {
        // Extrair texto do PDF
        String textoExtraido = extrairTextoDePDF(pdf);

        // Extrair dados específicos do texto
        return extrairDadosConta(textoExtraido);
    }

    private String extrairTextoDePDF(File pdf) throws IOException {
        try (PDDocument document = PDDocument.load(pdf)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document); // Retorna o texto extraído
        }
    }

    private ContaEnergia extrairDadosConta(String texto) {
        // Padrões para extração
        Pattern patternCusto = Pattern.compile("R\\$\\s?(\\d+[.,]?\\d{2})");  // Ajustado para capturar valores em reais
        Pattern patternKwh = Pattern.compile("(\\d+\\s?kWh)");  // Captura kWh
        Pattern patternData = Pattern.compile("(\\d{2}[/-]\\d{4})"); // Formato de data dd/MM/yyyy ou dd-MM-yyyy

        // Busca pelos dados
        Matcher matcherCusto = patternCusto.matcher(texto);
        Matcher matcherKwh = patternKwh.matcher(texto);
        Matcher matcherData = patternData.matcher(texto);

        // Objeto ContaEnergia
        ContaEnergia conta = new ContaEnergia();

        if (matcherCusto.find()) {
            // Substitui a vírgula por ponto, caso o formato de moeda seja "R$ 100,00"
            conta.setValorConta(Double.parseDouble(matcherCusto.group(1).replace(",", ".")));
        }

        if (matcherKwh.find()) {
            // Remove o "kWh" e converte o valor
            conta.setConsumoKwh(Double.parseDouble(matcherKwh.group(1).replace("kWh", "").trim()));
        }

        if (matcherData.find()) {
            // Ajusta a data para o formato LocalDateTime
            String dataExtraida = matcherData.group().replace("-", "/"); // Para lidar com "dd-MM-yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            conta.setData(LocalDateTime.parse(dataExtraida + "T00:00:00", formatter)); // Adiciona hora para converter para LocalDateTime
        }

        return conta;
    }
}
