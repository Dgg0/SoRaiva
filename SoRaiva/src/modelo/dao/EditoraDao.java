package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.entidade.Editora;

public class EditoraDao {
	
	Connection con = null;
	
	private void iniciarConexaoDB() {
		ConexaoDB conexaoDB = new ConexaoDB();
		con = conexaoDB.getConexaoDB();
	}
	
	public void save(Editora editora) {
		if (editora.getId() != null) {
			this.update(editora);
		} else {
			this.insert(editora);
		}
	}
	
	public void insert(Editora editora) {
		this.iniciarConexaoDB();
		String sql = "INSERT INTO editora id, nome VALUES (DEFAULT, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editora.getNome());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Editora inserida com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir editora: " + ex.getMessage());
		}
	}
	
	public void update(Editora editora) {
		this.iniciarConexaoDB();
		String sql = "UPDATE TABLE editora SET nome = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editora.getNome());
			pstmt.setInt(2, editora.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Dados de editora atualizados com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados de editora: " + ex.getMessage());
		}
	}
	
	public void delete(Editora editora) {
		this.iniciarConexaoDB();
		String sql = "DELETE FROM editora WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, editora.getId());
			pstmt.execute();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar editora por nome: " + ex.getMessage());
		}
	}
	
	public Editora select(int id) {
		this.iniciarConexaoDB();
		Editora editora = null;
		String sql = "SELECT id, nome FROM editora WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				editora = new Editora();
				editora.setId(rs.getInt(1));
				editora.setNome(rs.getString(2));
			}
			return editora;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar editora por id: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Editora> select(String nome) {
		this.iniciarConexaoDB();
		Editora editora = null;
		List<Editora> lstEditora = new ArrayList<>();
		String sql = "SELECT id, nome FROM editora WHERE nome LIKE ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				editora = new Editora();
				editora.setId(rs.getInt(1));
				editora.setNome(rs.getString(2));
				lstEditora.add(editora);
			}
			return lstEditora;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar editora por nome: " + ex.getMessage());
			return null;
		}
		
	}
}
