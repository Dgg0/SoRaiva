package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.entidade.Categoria;

public class CategoriaDao {
	
	private Connection con;
	
	private void iniciarConexaoDB() {
		ConexaoDB conexaoDB = new ConexaoDB();
		con = conexaoDB.getConexaoDB();
	}
	
	public void save(Categoria categoria) {
		if (categoria.getId() != null) {
			this.update(categoria);
		} else {
			this.insert(categoria);
		}
	}
	
	public void insert(Categoria categoria) {
		this.iniciarConexaoDB();
		String sql = "INSERT INTO categoria id, nome VALUES (DEFAULT, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, categoria.getNome());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir categoria: " + ex.getMessage());
		}
	}
	
	public void update(Categoria categoria) {
		this.iniciarConexaoDB();
		String sql = "UPDATE TABLE categoria SET nome = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, categoria.getNome());
			pstmt.setInt(2, categoria.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria: " + ex.getMessage());
		}
	}
	
	public void delete(Categoria categoria) {
		this.iniciarConexaoDB();
		String sql = "DELETE FROM categoria WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoria.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Categoria apagada com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao apagar categoria: " + ex.getMessage());
		}
	}
	
	public Categoria select(int id) {
		this.iniciarConexaoDB();
		Categoria categoria = null;
		String sql = "DELETE FROM categoria WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt(1));
				categoria.setNome(rs.getString(2));
			}
			return categoria;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao apagar categoria");
			return null;
		}
	}
	
	public List<Categoria> select(String nome) {
		this.iniciarConexaoDB();
		Categoria categoria = null;
		List<Categoria> lstCategoria = new ArrayList<>();
		String sql = "SELECT id, nome FROM categoria WHERE nome LIKE ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt(1));
				categoria.setNome(rs.getString(2));
				lstCategoria.add(categoria);
			}
			return lstCategoria;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar categoria por nome: " + ex.getMessage());
			return null;
		}
	}
}
