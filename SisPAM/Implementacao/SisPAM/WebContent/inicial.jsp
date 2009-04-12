<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SisPAM - Sistema de Pronto Atendimento Médico</title>
<link rel="stylesheet" href="..\resources\styles.css" type="text/css" />
</head>

<FRAMESET ROWS="127px,*" framespacing="1" frameborder="yes" bordercolor="#BCD2E6">
     <FRAMESET COLS="25%,*" frameborder="no" framespacing="0">
		<FRAME SRC="componentes\logo.jsp" NAME=LOGO noresize="noresize" scrolling="no">
		<FRAME SRC="componentes\cabecalho.jsp" NAME=CABECALHO SCROLLING=no noresize="noresize" >
	</FRAMESET>	 
     <FRAMESET COLS="25%,*" frameborder="no" framespacing="0">
        <FRAME SRC="componentes\mnAdministrativo.jsp" NAME=MENU noresize="noresize">
        <FRAME SRC="componentes\defaut.jsp" NAME=CENTRAL noresize="noresize">
     </FRAMESET>

</FRAMESET>

</html>