package br.com.sispam.servlet;
import java.io.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import br.com.sispam.banco.ConexaoRelatorio;
import br.com.sispam.util.DataUtil;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperRunManager;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;



public class Relatorio extends HttpServlet {
	static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String relatorioChamado = request.getParameter("relatorioChamado");
		
		ServletOutputStream servletOutputStream = response.getOutputStream();

		String caminho = "/WEB-INF/relatorios/";
		
		String relatorio = null;
		
		if(relatorioChamado.equals("convenio")){
			relatorio = caminho+emiteRelatorioConvenio(request, response).get("relatorio");;
		}else if(relatorioChamado.equals("receita")){
			relatorio = caminho+emiteReceita(request, response).get("relatorio");
		}else if(relatorioChamado.equals("relatorioLog")){
			relatorio = caminho+emiteRelatorioLog(request, response).get("relatorio");
		}else if(relatorioChamado.equals("usuario")){
			relatorio = caminho+emiteRelatorioUsuario(request, response).get("relatorio");
		}
		
		InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);
		

		try {
			ConexaoRelatorio conexaoRelatorio = new ConexaoRelatorio();
			Connection connection = conexaoRelatorio.getConexao();
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, Object> mapaReceita = new HashMap<String, Object>();

			if(relatorioChamado.equals("convenio")){
				//passa o map dependendo do relatorio			
				map = emiteRelatorioConvenio(request, response);
			}else if(relatorioChamado.equals("receita")){
				mapaReceita = emiteReceita(request, response);
			}else if(relatorioChamado.equals("relatorioLog")){
				map = emiteRelatorioLog(request, response);
			}else if(relatorioChamado.equals("usuario")){
				mapaReceita = emiteRelatorioUsuario(request, response);
			}
			
			//para gerar o relatório em PDF
			// o método runReportToPdfStream foi usado
			if(relatorioChamado.equals("convenio")){	
				JasperRunManager.runReportToPdfStream(reportStream, 
						servletOutputStream, map,connection);
			}else if(relatorioChamado.equals("receita")){
				JasperRunManager.runReportToPdfStream(reportStream, 
						servletOutputStream, mapaReceita,connection);
			}else if(relatorioChamado.equals("relatorioLog")){
				JasperRunManager.runReportToPdfStream(reportStream, 
						servletOutputStream, map,connection);
			}else if(relatorioChamado.equals("usuario")){
				JasperRunManager.runReportToPdfStream(reportStream, 
						servletOutputStream, mapaReceita,connection);
			}
				

			// envia o relatório em formato PDF para o browser
			response.setContentType("application/pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
			if(connection!=null)connection.close();
		} catch (SQLException ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
		catch (JRException e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		}


	} 
	
	protected HashMap<String, String> emiteRelatorioConvenio(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> parameterMap;
		parameterMap = new HashMap<String, String>();
		
		String tipoRelatorio = request.getParameter("tipoRelatorio");
		String filtro = request.getParameter("convenio.nome");
		
		parameterMap.put("TIPORELATORIO", tipoRelatorio);
		parameterMap.put("NOME", filtro+"%");
		
		String relatorio = null;
		try {
		
		if(parameterMap.get("TIPORELATORIO").equals("1") && parameterMap.get("NOME").isEmpty()){
			relatorio = "RelatorioConvenioSemFiltroSintetico.jasper";
			parameterMap.put("relatorio", relatorio);
		}else if(parameterMap.get("TIPORELATORIO").equals("1") && !parameterMap.get("NOME").isEmpty()){
			relatorio = "RelatorioConvenioComFiltroNomeSintetico.jasper";			
			parameterMap.put("relatorio", relatorio);
		}else if(parameterMap.get("TIPORELATORIO").equals("2") && parameterMap.get("NOME").isEmpty()){
			relatorio = "RelatorioConvenioSemFiltroAnalitico.jasper";			
			parameterMap.put("relatorio", relatorio);
		}else if(parameterMap.get("TIPORELATORIO").equals("2") && !parameterMap.get("NOME").isEmpty()){
			relatorio = "RelatorioConvenioComFiltroNomeAnalitico.jasper";			
			parameterMap.put("relatorio", relatorio);
		}	
		
		} catch (Exception e) {
			e.getStackTrace();
		}
		return parameterMap;
	}
	
	protected HashMap<String, Object> emiteRelatorioUsuario(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> parameterMap;
		parameterMap = new HashMap<String, Object>();
				
		String relatorio = null;
		try {
			int perfil = Integer.parseInt(request.getParameter("perfil"));
			parameterMap.put("PERFIL", perfil);
		if(parameterMap.get("PERFIL").toString().equals("0")){
			relatorio = "RelatorioUsuarioSemFiltroSintetico.jasper";
			parameterMap.put("relatorio", relatorio);
		}else {
			
			relatorio = "RelatorioUsuarioComFiltroSintetico.jasper";			
			parameterMap.put("relatorio", relatorio);
		}
		
		} catch (Exception e) {
			e.getStackTrace();
		}
		return parameterMap;
	}
	
	protected HashMap<String, Object> emiteReceita(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> parameterMap;
		parameterMap = new HashMap<String, Object>();
		
		int paciente = Integer.parseInt(request.getParameter("paciente"));
		String dataString = request.getParameter("data");
		String data;
		try {
			data = DataUtil.stringToDateFormatoPdf(dataString);
			int hora = Integer.parseInt(request.getParameter("hora"));
			parameterMap.put("PCTIDFSEG", paciente);
			parameterMap.put("AGDDAT", (data));
			parameterMap.put("AGDHOR", hora);
			parameterMap.put("relatorio", "RelatorioReceita.jasper");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parameterMap;
	}

	protected HashMap<String, String> emiteRelatorioLog(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> parameterMap;
		parameterMap = new HashMap<String, String>();
		
		String dataString = request.getParameter("data");
		String data;
		try {
			data = DataUtil.stringToDateFormatoPdf(dataString);		
			parameterMap.put("DATA_REFERENCIA", (data));
			parameterMap.put("relatorio", "RelatorioLogComFiltroSintetico.jasper");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parameterMap;
	}


	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pag = request.getServletPath();
		pag = pag.substring(1, pag.length()-7);
		if(pag.equals("relatorioConvenio")){
			processRequest(request, response);
		}else if(pag.equals("emiteReceita")){
			processRequest(request, response);
		}else if(pag.equals("relatorioLog")){
			processRequest(request, response);
		}else if(pag.equals("relatorioUsuario")){
			processRequest(request, response);
		}
	}  	

	/* (non-Java-doc)relatorioUsuario
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pag = request.getServletPath();
		pag = pag.substring(1, pag.length()-7);
		if(pag.equals("relatorioConvenio")){
			processRequest(request, response);
		}else if(pag.equals("emiteReceita")){
			processRequest(request, response);
		}else if(pag.equals("relatorioLog")){
			processRequest(request, response);
		}else if(pag.equals("relatorioUsuario")){
			processRequest(request, response);
		}	
	}   	  	    
}