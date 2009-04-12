<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD>

<SCRIPT LANGUAGE="JavaScript">

<!-- Begin
function Login(){
var done=0;
var username=document.login.username.value;
username=username.toLowerCase();
var password=document.login.password.value;
password=password.toLowerCase();
if (username=="ruajava" && password=="entrar") { window.location="#"; done=1; }
if (username=="outro" && password=="outrosenha") { window.location="#"; done=1; }
if (username=="outro" && password=="outrasenha") { window.location="#"; done=1; }
if (done==0) { alert("Senha ou Usuário inválido."); }
}
// End -->
</SCRIPT>

<BODY>
<img src="componentes\img\simbLogin.jpg"  width="50%">
<center> 
<form name=login >
<table width=225 border=0 cellpadding=3 height="123" bgcolor="#BCD2E6" >
	<tr>
		<td colspan=3 height="13">
		<center>
		<p><font face="Arial Black">Área Restrita</font></p>
		</center>
		</td>
	</tr>
	<td rowspan="2" >
	<p align="right"><img border="0" src="componentes\img\user.gif"
		width="80" height="80" align="left"></font>
	</td>
	<td><font face="Verdana" style="font-size: 8pt; font-weight: 700"><b>Usuário</b></font></td>
	<td><input name="user" size=30 value=""></td>
	
	</tr>
	<tr align="right">
		<td><font face="Verdana" style="font-size: 8pt; font-weight: 700"><b>Senha</b></font></td>
		<td><input type="password" name="pass" size=30 value=""></td>
	</tr>
	<td valign="bottom" height="26"  colspan="3"><input type=button
		value="Entrar" onClick="Login()"></td>

	</tr>
	</tr>
</table>
</form>
</center>

<p>

<p align="center" style="margin-top: 0; margin-bottom: 0"> </p>