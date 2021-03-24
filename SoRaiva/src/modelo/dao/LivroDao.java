package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.entidade.Autor;
import modelo.entidade.Categoria;
import modelo.entidade.Editora;
import modelo.entidade.Idioma;
import modelo.entidade.Livro;

public class LivroDao {
	
	private Connection con;
	
	private CategoriaDao categoriaDao;
	private AutorDao autorDao;
	private EditoraDao editoraDao;
	private IdiomaDao idiomaDao;
	
	private void iniciarConexaoDB() {
		ConexaoDB conexaoDB = new ConexaoDB();
		con = conexaoDB.getConexaoDB();
	}
	
	public void save(Livro livro) {
		if (livro.getId() != null) {
			this.update(livro);
		} else {
			this.insert(livro);
		}
	}
	
	public void insert(Livro livro) {
		this.iniciarConexaoDB();
		String sql = "INSERT INTO livro id, codigo, nome, descricao, preco, id_categoria, id_autor, id_editora, id_idioma, ano_publicacao, qtde_paginas"
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, livro.getCodigo());
			pstmt.setString(2, livro.getNome());
			pstmt.setString(3, livro.getDescricao());
			pstmt.setDouble(4, livro.getPreco());
			pstmt.setInt(5, livro.getCategoria().getId());
			pstmt.setInt(6, livro.getAutor().getId());
			pstmt.setInt(7, livro.getEditora().getId());
			pstmt.setInt(8, livro.getIdioma().getId());
			pstmt.setInt(9, livro.getAnoPublicacao());
			pstmt.setInt(10, livro.getQtdePaginas());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Idioma inserido com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir idioma: " + ex.getMessage());
		}
	}
	
	public void update(Livro livro) {
		this.iniciarConexaoDB();
		String sql = "UPDATE TABLE idioma SET codigo = ?, nome = ?, descricao = ?, preco = ? id_categoria = ?, id_autor = ?, id_editora = ?, id_idioma = ?, ano_publicacao = ?, "
				+ "qtde_paginas = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, livro.getCodigo());
			pstmt.setString(2, livro.getNome());
			pstmt.setString(3, livro.getDescricao());
			pstmt.setDouble(4, livro.getPreco());
			pstmt.setInt(5, livro.getCategoria().getId());
			pstmt.setInt(6, livro.getAutor().getId());
			pstmt.setInt(7, livro.getEditora().getId());
			pstmt.setInt(8, livro.getIdioma().getId());
			pstmt.setInt(9, livro.getAnoPublicacao());
			pstmt.setInt(10, livro.getQtdePaginas());
			JOptionPane.showMessageDialog(null, "Dados de livro atualizados com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do livro: " + ex.getMessage());
		}
	}
	
	public void delete(Livro livro) {
		this.iniciarConexaoDB();
		String sql = "DELETE FROM livro WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, livro.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Livro apagado com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao apagar livro: " + ex.getMessage());
		}
	}
	
	//Métodos que retornam os objetos referentes aos id's recebidos nos metodos select.
	private Categoria buscarCategoria(int id) {
		return categoriaDao.select(id);
	}
	
	private Autor buscarAutor(int id) {
		return autorDao.select(id);
	}
	
	private Editora buscarEditora(int id) {
		return editoraDao.select(id);
	}
	
	private Idioma buscarIdioma(int id) {
		return idiomaDao.select(id);
	}
	//Fim 
	
	public Livro selectId(int id) {
		this.iniciarConexaoDB();
		Livro livro = null;
		String sql = "SELECT * FROM livro WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
			}
			return livro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por id: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Livro> selectNome(String nome) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE nome LIKE ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por nome: " + ex.getMessage());
			return null;
		} 
	} 
	
	public List<Livro> selectCategoria(int id) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE id_categoria = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
				lstLivro.add(livro);
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por categoria: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Livro> selectAutor(int id) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE id_autor = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
				lstLivro.add(livro);
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por autor: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Livro> selectEditora(int id) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE id_editora = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
				lstLivro.add(livro);
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por editora: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Livro> selectIdioma(int id) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE id_idioma = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
				lstLivro.add(livro);
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por idioma: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Livro> selectAnoPublicacao(int ano) {
		this.iniciarConexaoDB();
		Livro livro = null;
		List<Livro> lstLivro = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE ano_publicacao = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setCodigo(rs.getInt("codigo"));
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setCategoria(buscarCategoria(rs.getInt("id_categoria")));
				livro.setAutor(buscarAutor(rs.getInt("id_autor")));
				livro.setEditora(buscarEditora(rs.getInt("id_editora")));
				livro.setIdioma(buscarIdioma(rs.getInt("id_idioma")));
				livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
				livro.setQtdePaginas(rs.getInt("qtde_paginas"));
				lstLivro.add(livro);
			}
			return lstLivro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro por ano de publicação: " + ex.getMessage());
			return null;
		}
	}
}
