package br.com.jls.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.jls.model.AgendaBeans;
import br.com.jls.model.AgendaDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class AgendaController.
 */
@WebServlet(urlPatterns = { "/AgendaController", "/main", "/inserir", "/selecionar", "/atualizar", "/deletar",
		"/imprimir" })
public class AgendaController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	AgendaDAO dao = new AgendaDAO();
	
	/** The agenda. */
	AgendaBeans agenda = new AgendaBeans();

	/**
	 * Instantiates a new agenda controller.
	 */
	public AgendaController() {
		super();
	}
	
	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();
		if (acao.equals("/main")) {
			dao.criarTabela();
			listarContatos(request, response);
		} else if (acao.equals("/inserir")) {
			adicionarContato(request, response);
		} else if (acao.equals("/selecionar")) {
			listarContato(request, response);
		} else if (acao.equals("/atualizar")) {
			editarContato(request, response);
		} else if (acao.equals("/deletar")) {
			deletarContato(request, response);
		} else if (acao.equals("/imprimir")) {
			criarPDF(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AgendaBeans> contatos = dao.listarContatos();
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("/agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			agenda.setNome(request.getParameter("nome"));
			agenda.setTelefone(request.getParameter("telefone"));
			agenda.setEmail(request.getParameter("email"));
			agenda.setEndereco(request.getParameter("endereco"));
			agenda.setDataNascimento(request.getParameter("dataNascimento"));			
			dao.inserirContato(agenda);
			response.sendRedirect("main");
		} catch (Exception e) {
			System.out.println(e);			
		}		
	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idConverter = request.getParameter("id");
		Long id = (long) Long.parseLong(idConverter);
		agenda.setId(id);
		dao.selecionarContato(agenda);
		request.setAttribute("id", agenda.getId());
		request.setAttribute("nome", agenda.getNome());
		request.setAttribute("telefone", agenda.getTelefone());
		request.setAttribute("email", agenda.getEmail());
		request.setAttribute("endereco", agenda.getEndereco());
		request.setAttribute("dataNascimento", agenda.getDataNascimento());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idConverter = request.getParameter("id");
		agenda.setId((long) Long.parseLong(idConverter));
		agenda.setNome(request.getParameter("nome"));
		agenda.setTelefone(request.getParameter("telefone"));
		agenda.setEmail(request.getParameter("email"));
		agenda.setEndereco(request.getParameter("endereco"));
		agenda.setDataNascimento(request.getParameter("dataNascimento"));
		dao.atualizarContato(agenda);
		response.sendRedirect("main");
	}

	/**
	 * Deletar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idConverter = request.getParameter("id");
		Long id = (long) Long.parseLong(idConverter);
		agenda.setId(id);
		dao.deletarContato(agenda);
		response.sendRedirect("main");
	}

	/**
	 * Criar PDF.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void criarPDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename= contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(5);			
			tabela.setWidthPercentage(100);		
			Font font = new Font(FontFamily.HELVETICA, 11, 5);
			Font font2 = new Font(FontFamily.HELVETICA, 10);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome", font));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone", font));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail", font));
			PdfPCell col4 = new PdfPCell(new Paragraph("Endereço", font));
			PdfPCell col5 = new PdfPCell(new Paragraph("Data de Nascimento", font));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);	
			List<AgendaBeans> lista = dao.listarContatos();
			for (AgendaBeans agenda : lista) {
				col1 = new PdfPCell(new Paragraph(agenda.getNome(), font2));
				tabela.addCell(col1);
				col2 = new PdfPCell(new Paragraph(agenda.getTelefone(), font2));
				tabela.addCell(col2);
				col3 = new PdfPCell(new Paragraph(agenda.getEmail(), font2));
				tabela.addCell(col3);
				col4 = new PdfPCell(new Paragraph(agenda.getEndereco(), font2));
				tabela.addCell(col4);
				col5 = new PdfPCell(new Paragraph(agenda.getDataNascimento(), font2));
				tabela.addCell(col5);
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
