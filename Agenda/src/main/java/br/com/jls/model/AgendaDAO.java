package br.com.jls.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jls.conexao.FabricaConexao;

// TODO: Auto-generated Javadoc
/**
 * The Class AgendaDAO.
 */
public class AgendaDAO {
	
	/** The conexao. */
	private Connection conexao;
	
	/** The pst. */
	private PreparedStatement pst;

	/**
	 * Gets the conexao.
	 *
	 * @return the conexao
	 */
	private Connection getConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {

		}
		conexao = FabricaConexao.getConexao();
		return conexao;
	}

	/**
	 * Fechar conexao.
	 */
	public void fecharConexao() {
		try {
			getConexao().close();

		} catch (SQLException e) {

		} finally {
			conexao = null;
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param agenda the agenda
	 */
	public void inserirContato(AgendaBeans agenda) {		
		String sql = "INSERT INTO agenda(nome, telefone, email, endereco, dataNascimento) VALUES (?, ?, ?, ?, ?)";			
		try {
			conexao = FabricaConexao.getConexao();			
			pst = conexao.prepareStatement(sql);
			pst.setString(1, agenda.getNome());
			pst.setString(2, agenda.getTelefone());
			pst.setString(3, agenda.getEmail());
			pst.setString(4, agenda.getEndereco());			
			pst.setString(5, agenda.getDataNascimento());
			pst.execute();
			fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the list
	 */
	public List<AgendaBeans> listarContatos() {
		String sql = "Select * from agenda order by nome";
		List<AgendaBeans> contatos = new ArrayList<>();
		ResultSet resultado = null;
		try {
			conexao = FabricaConexao.getConexao();
			pst = conexao.prepareStatement(sql);
			resultado = pst.executeQuery();
			while (resultado.next()) {
				AgendaBeans contato = new AgendaBeans();
				contato.setId(resultado.getLong("id"));
				contato.setNome(resultado.getString("nome"));				
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setEndereco(resultado.getString("endereco"));
				contato.setDataNascimento(resultado.getString("dataNascimento"));
				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}

				if (resultado != null) {
					resultado.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fecharConexao();
		return contatos;
	}

	/**
	 * Selecionar contato.
	 *
	 * @param agenda the agenda
	 */
	public void selecionarContato(AgendaBeans agenda) {
		String sql = "Select * from agenda where id = ?";		
		ResultSet resultado = null;
		try {
			conexao = FabricaConexao.getConexao();
			pst = conexao.prepareStatement(sql);
			pst.setLong(1, agenda.getId());
			resultado = pst.executeQuery();
			while (resultado.next()) {
				agenda.setId(resultado.getLong("id"));
				agenda.setNome(resultado.getString("nome"));				
				agenda.setTelefone(resultado.getString("telefone"));
				agenda.setEmail(resultado.getString("email"));
				agenda.setEndereco(resultado.getString("endereco"));
				agenda.setDataNascimento(resultado.getString("dataNascimento"));
			}
			fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (resultado != null) {
					resultado.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Atualizar contato.
	 *
	 * @param agenda the agenda
	 */
	public void atualizarContato(AgendaBeans agenda) {
		String sql = "UPDATE agenda SET nome = ?, telefone = ?, email = ?, endereco = ?, dataNascimento = ?" + "WHERE id = ?";
		try {
			conexao = FabricaConexao.getConexao();
			pst = conexao.prepareStatement(sql);
			pst.setString(1, agenda.getNome());		
			pst.setString(2, agenda.getTelefone());
			pst.setString(3, agenda.getEmail());
			pst.setString(4, agenda.getEndereco());
			pst.setString(5, agenda.getDataNascimento());
			pst.setLong(6, agenda.getId());
			pst.executeUpdate();
			fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deletar contato.
	 *
	 * @param agenda the agenda
	 */
	public void deletarContato(AgendaBeans agenda) {
		String sql = " DELETE FROM agenda WHERE id = ?";
		try {
			conexao = FabricaConexao.getConexao();
			pst = conexao.prepareStatement(sql);
			pst.setLong(1, agenda.getId());
			pst.executeUpdate();

			fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Criar tabela.
	 */
	public void criarTabela() {
		conexao = FabricaConexao.getConexao();
		try {
			String sql = "CREATE TABLE IF NOT EXISTS agenda"
					+ "(id BIGINT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(82) NOT NULL, telefone VARCHAR(20), email VARCHAR(150), endereco VARCHAR(150), dataNascimento VARCHAR(20))";

			Statement stmt = conexao.createStatement();
			stmt.execute(sql);
			stmt.close();
			fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

