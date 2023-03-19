<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="br.com.jls.model.AgendaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<AgendaBeans> lista = (ArrayList<AgendaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	<a href="imprimir" class="Botao2">Imprimir Contatos</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Endereço</th>
				<th>Data de Nascimento</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (AgendaBeans ag : lista) {
			%>
			<tr>
				<td><%=ag.getNome()%></td>				
				<td><%=ag.getTelefone()%></td>				
				<td><%=ag.getEmail()%></td>
				<td><%=ag.getEndereco()%></td>
				<td><%=ag.getDataNascimento()%></td>
				<td><a href="selecionar?id=<%=ag.getId()%>" class="Botao1">Editar</a>
					<a href="javascript: confirmar(<%=ag.getId()%>)" class="Botao2">Excluir</a>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<div class="container">
		<h6>Autora:</h6>
		<a href="https://www.linkedin.com/in/juliana-lima-b133b967/"
			target="_blank"> <img src="icones/linkedin.png" alt="LinkedIn"
			class="icon">
		</a>
	</div>
	<script src="scripts/confirmador.js"></script>		
</body>
</html>