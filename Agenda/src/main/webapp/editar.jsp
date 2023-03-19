<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Contato</h1>
	<form name="frmContato" action="atualizar">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="telefone" class="Caixa2" value="<%out.print(request.getAttribute("telefone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="endereco" class="Caixa1" value="<%out.print(request.getAttribute("endereco"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="dataNascimento" class="Caixa2" value="<%out.print(request.getAttribute("dataNascimento"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<div class="container">
		<h6>Autora:</h6>
		<a href="https://www.linkedin.com/in/juliana-lima-b133b967/"
			target="_blank"> <img src="icones/linkedin.png" alt="LinkedIn"
			class="icon">
		</a>
	</div>
	<script src="scripts/validador.js"></script>	
</body>
</html>