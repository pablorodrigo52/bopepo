package br.com.nordestefomento.jrimum.bopepo.exemplo.guia;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import br.com.nordestefomento.jrimum.bopepo.guia.BancoSuportado;
import br.com.nordestefomento.jrimum.bopepo.guia.Guia;
import br.com.nordestefomento.jrimum.bopepo.view.BoletoViewer;
import br.com.nordestefomento.jrimum.bopepo.view.guia.GuiaViewer;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.Arrecadacao;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.Contribuinte;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.Convenio;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.IdentificacaoSeguimento;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.IdentificacaoValorReferencia;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.guia.OrgaoRecebedor;
import br.com.nordestefomento.jrimum.utilix.DateUtil;

public class MinhaPrimeiraGuia {

	public static void main(String[] args) throws ParseException {
		
		
		Contribuinte contribuinte = new Contribuinte("Misael Barreto de Queiroz", "01054377430");
		OrgaoRecebedor orgaoRecebedor = new OrgaoRecebedor("ESMARN", "08546459000105", IdentificacaoSeguimento.USO_EXCLUSIVO_BANCO);
		Convenio convenio = new Convenio(BancoSuportado.BANCO_DO_BRASIL.create(), 105595);
		
		Arrecadacao arrecadacao = new Arrecadacao(convenio, orgaoRecebedor, contribuinte);
		arrecadacao.setTitulo("Recibo do candidato");
		arrecadacao.setDescricao("Pagamento de inscrição do concurso ESMARN Estagiários 2010");
		arrecadacao.setNossoNumero("555666");
		arrecadacao.setValor(new BigDecimal(20.00));
		arrecadacao.setIdentificacaoValorReferencia(IdentificacaoValorReferencia.VALOR_COBRADO_EM_REAL_COM_DV_MODULO_10);
		arrecadacao.setDataDoDocumento(DateUtil.FORMAT_DD_MM_YYYY.parse("26/06/2010"));				
		arrecadacao.setDataDoVencimento(DateUtil.FORMAT_DD_MM_YYYY.parse("26/06/2010"));
	
		
		
		Guia guia = new Guia(arrecadacao);
		guia.setInstrucao1("PAGÁVEL SOMENTE NO BANCO DO BRASIL.");
		guia.setInstrucao2("PREFERENCIAMENTE DEVE SER PAGA NOS TERMINAIS DE AUTO-ATENDIMENTO,");
		guia.setInstrucao3("CORRESPONDENTES BANCÁRIOS E INTERNET");

		guia.addTextosExtras("txtCampoExtraOpcaoCargo", "Estagiário");
		
	
		GuiaViewer guiaViewer = new GuiaViewer(guia);

		// Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
		// pasta do projeto. Outros exemplos:
		// WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
		// LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
		File arquivoPdf = guiaViewer.getPdfAsFile("MinhaPrimeiraGuia.pdf");

		// Mostrando o boleto gerado na tela.
		mostreGuiaNaTela(arquivoPdf);		
	}
	
	private static void mostreGuiaNaTela(File arquivoPDF) {

		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		
		try {
			desktop.open(arquivoPDF);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	
	
}
