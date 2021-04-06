/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.entidade.Usuario;

/**
 *
 * @author Diogo
 */
public class UsuarioDao {
    
    private Connection con;
    
    private void iniciarConexaoDB() {
        ConexaoDB conexaoDB = new ConexaoDB();
        con = conexaoDB.getConexaoDB();
    }
    
    public void save(Usuario usuario) {
        if (usuario.getId() != null) {
            this.update(usuario);
        } else {
            this.insert(usuario);
        }
    }
    
    public void insert(Usuario usuario) {
        this.iniciarConexaoDB();
        String sql = "INSERT INTO usuario id, nome, cpf, email, tag, tipo_usuario VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getCpf());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getTag());
            pstmt.setString(5, usuario.getTipo_usuario());
            pstmt.setString(6, usuario.getSenha());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar usuário: " + ex.getMessage());
        }
    }
    
    public void update(Usuario usuario) {
        this.iniciarConexaoDB();
        String sql = "UPDATE TABLE usuario SET nome = ?, cpf = ?, email = ?, tag = ?, tipo_usuario = ? WHERE id = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getCpf());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getTag());
            pstmt.setString(5, usuario.getTipo_usuario());
            pstmt.setString(6, usuario.getSenha());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + ex.getMessage());
        }
    }
    
    public void delete(Usuario usuario) {
        this.iniciarConexaoDB();
        String sql  = "DELETE FROM usuario WHERE id = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, usuario.getId());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + ex.getMessage());
        }
    } 
    
    public Usuario selectId(int id) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTag(rs.getString("tag"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por id: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Usuario> selectNome(String nome) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        List<Usuario> lstUsuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nome + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTag(rs.getString("tag"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
                lstUsuario.add(usuario);
            }
            return lstUsuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por nome: " + ex.getMessage());
            return null;
        }
    }
    
    public Usuario selectCpf(String cpf) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTag(rs.getString("tag"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por CPF: " + ex.getMessage());
            return null;
        }
    }
    
    public Usuario selectEmail(String email) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        String sql = "SELECT * FROM email WHERE email = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTag(rs.getString("tag"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por E-Mail: " + ex.getMessage());
            return null;
        }
    }
    
    public Usuario selectTag(String tag) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE tag = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tag);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTag(rs.getString("tag"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por Tag: " + ex.getMessage());
            return null;
        }
    }
    
    public Usuario login(String tag, String senha) {
        this.iniciarConexaoDB();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE tag = ? AND senha = ?";
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tag);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
               usuario = new Usuario();
               usuario.setId(rs.getInt("id"));
               usuario.setNome(rs.getString("nome"));
               usuario.setCpf(rs.getString("cpf"));
               usuario.setEmail(rs.getString("email"));
               usuario.setTag(rs.getString("tag"));
               usuario.setTipo_usuario(rs.getString("tipo"));
               usuario.setSenha(rs.getString("senha"));
            }
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos. " + ex.getMessage());
            return null;
        }
    }
}
