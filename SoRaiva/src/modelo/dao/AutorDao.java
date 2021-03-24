package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.entidade.Autor;

public class AutorDao {

	private Connection con;
	
	private void iniciarConexaoDB() {
		ConexaoDB conexaoDB = new ConexaoDB();
		con = conexaoDB.getConexaoDB();
	}
	
	public void save(Autor autor) {
		if (autor.getId() != null) {
			this.update(autor);
		} else {
			this.insert(autor);
		}
	}
	
	public void insert(Autor autor) {
		this.iniciarConexaoDB();
		String sql = "INSERT INTO autor id, nome VALUES (DEFAULT, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, autor.getNome());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Autor inserido com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir autor: " + ex.getMessage());
		}
	}
	
	public void update(Autor autor) {
		this.iniciarConexaoDB();
		String sql = "UPDATE TABLE autor SET nome = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, autor.getNome());
			pstmt.setInt(2, autor.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Informações de autor alteradas com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar informações do autor: " + ex.getMessage());
		}
	}
	
	public void delete(Autor autor) {
		this.iniciarConexaoDB();
		String sql = "DELETE FROM autor WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, autor.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Autor apagado com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao apagar autor: " + ex.getMessage());
		}
	}
	
	public Autor select(int id) {
		this.iniciarConexaoDB();
		Autor autor = null;
		String sql = "SELECT id, nome FROM autor WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				autor = new Autor();
				autor.setId(rs.getInt(1));
				autor.setNome(rs.getString(2));
			}
			return autor;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar autor por id: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Autor> select(String nome) {
		this.iniciarConexaoDB();
		Autor autor = null;
		List<Autor> lstAutor = new ArrayList<>();
		String sql = "SELECT id, nome FROM autor WHERE nome LIKE ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				autor = new Autor();
				autor.setId(rs.getInt(1));
				autor.setNome(rs.getString(2));
				lstAutor.add(autor);
			}
			return lstAutor;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar autor por nome: " + ex.getMessage());
			return null;
		}
	}
}
