package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.entidade.Idioma;

public class IdiomaDao {
	
	private Connection con;
	
	private void iniciarConexaoDB() {
		ConexaoDB conexaoDB = new ConexaoDB();
		con = conexaoDB.getConexaoDB();
	}
	
	public void save(Idioma idioma) {
		if (idioma.getId() != null) {
			this.update(idioma);
		} else {
			this.insert(idioma);
		}
	}
	
	public void insert(Idioma idioma) {
		this.iniciarConexaoDB();
		String sql = "INSERT INTO idioma id, nome VALUES (DEFAULT, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idioma.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Idioma inserido com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir idioma: " + ex.getMessage());
		}
	}
	
	public void update(Idioma idioma) {
		this.iniciarConexaoDB();
		String sql = "DELETE FROM idioma WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idioma.getId());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Idioma apagado com sucesso. ");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao apagar idioma: " + ex.getMessage());
		}
	}
	
	public Idioma select(int id) {
		this.iniciarConexaoDB();  
		Idioma idioma = null;
		String sql = "SELECT id, nome FROM idioma WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				idioma = new Idioma();
				idioma.setId(rs.getInt(1));
				idioma.setNome(rs.getString(2));
			}
			return idioma;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar idioma por id: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Idioma> lstIdioma(String nome) {
		this.iniciarConexaoDB();
		Idioma idioma = null;
		List<Idioma> lstIdioma = new ArrayList<>();
		String sql = "SELECT id, nome FROM idioma WHERE nome LIKE ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				idioma = new Idioma();
				idioma.setId(rs.getInt(1));
				idioma.setNome(rs.getString(2));
				lstIdioma.add(idioma);
			}
			return lstIdioma;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar idioma por nome: " + ex.getMessage());
			return null;
		}
	}
}
