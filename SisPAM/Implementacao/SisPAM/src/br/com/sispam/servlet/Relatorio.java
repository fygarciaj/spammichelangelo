package br.com.sispam.servlet;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import br.com.sispam.banco.ConexaoRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;


public class Relatorio extends HttpServlet {
	static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletOutputStream servletOutputStream = response.getOutputStream();

		String caminho = "/WEB-INF/relatorios/";
		
		String relatorio = null;
		
		relatorio = caminho+emiteRelatorioConvenio(request, response).get("relatorio");;
		
		InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);
		

		try {
			ConexaoRelatorio conexaoRelatorio = new ConexaoRelatorio();
			Connection connection = conexaoRelatorio.getConexao();
			HashMap<String, String> map = 
				new HashMap<String, String>();

			
			//passa o map dependendo do relatorio			
			map = emiteRelatorioConvenio(request, response);
			
			
			//para gerar o relatório em PDF
			// o método runReportToPdfStream foi usado
					
			JasperRunManager.runReportToPdfStream(reportStream, 
					servletOutputStream, map,connection);

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

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}  	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pag = request.getServletPath();
		pag = pag.substring(1, pag.length()-7);
		if(pag.equals("relatorioConvenio")){
			processRequest(request, response);
		}
	}   	  	    
}