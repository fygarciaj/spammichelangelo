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
		String tipoRelatorio = request.getParameter("tipoRelatorio");
		String relatorio = null;
		if(tipoRelatorio.equals("1")){
			relatorio = caminho+"Relatorio_Convenio.jasper";
		}else{
			relatorio = caminho+"Relatorio_Convenio_Analitico.jasper";
		}
		InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);
		

		try {
			ConexaoRelatorio conexaoRelatorio = new ConexaoRelatorio();
			Connection connection = conexaoRelatorio.getConexao();
			HashMap<Object, Object> map = 
				new HashMap<Object, Object>();

			//para indicar o diretório onde
			//se encontram as imagens
			//			map.put("DIRETORIO", 
			//					context.getRealPath(caminho)+File.separator+
			//					"imagens"+
			//					File.separator);
			//
			//			//transmitir o Locale novo
			//			map.put(JRParameter.REPORT_LOCALE, 
			//					new Locale("en"));


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