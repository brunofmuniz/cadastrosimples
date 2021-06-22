package br.com.cadastro.infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.domain.Contato;

public class ContatoRepository {

    public void inserir(Contato contato) {
        
        Connection conn = FabricaDeConexoes.conectar();
        try {
            
            String sql = "INSERT INTO contatos "
                    + "      (nome, email, fone) "
                    + "      VALUES (?,?,?)";            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getFone());
            ps.executeUpdate();
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            
            FabricaDeConexoes.desconectar(conn);
        }
    }
    
    public List<Contato> listar() {
        
        List<Contato> retorno = new ArrayList<>();        
        Connection conn = FabricaDeConexoes.conectar();
        try {
            
            String sql = "SELECT * FROM contatos ORDER BY nome";            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setFone(rs.getString("fone"));
                retorno.add(contato);
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            
            FabricaDeConexoes.desconectar(conn);
        }
        return retorno;
    }
}
