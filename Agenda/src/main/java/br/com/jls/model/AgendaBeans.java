package br.com.jls.model;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class AgendaBeans.
 */
public class AgendaBeans {	
	
	/** The id. */
	private Long id;
	
	/** The nome. */
	private String nome;	
	
	/** The telefone. */
	private String telefone;
	
	/** The email. */
	private String email;
	
	/** The endereco. */
	private String endereco;
	
	/** The data nascimento. */
	private String dataNascimento;
	
	/**
	 * Instantiates a new agenda beans.
	 */
	public AgendaBeans() {
		super();		
	}

	/**
	 * Instantiates a new agenda beans.
	 *
	 * @param nome the nome
	 * @param telefone the telefone
	 * @param email the email
	 * @param endereco the endereco
	 * @param dataNascimento the data nascimento
	 */
	public AgendaBeans(String nome, String telefone, String email, String endereco, String dataNascimento) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the telefone.
	 *
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Sets the telefone.
	 *
	 * @param telefone the new telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Gets the data nascimento.
	 *
	 * @return the data nascimento
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Sets the data nascimento.
	 *
	 * @param dataCadastro the new data nascimento
	 */
	public void setDataNascimento(String dataCadastro) {
		this.dataNascimento = dataCadastro;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgendaBeans other = (AgendaBeans) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "AgendaBeans [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", endereco=" + endereco + ", dataNascimento=" + dataNascimento + "]";
		}	
}

