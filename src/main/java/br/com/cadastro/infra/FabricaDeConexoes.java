package br.com.cadastro.infra;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexoes {

    public static Connection conectar() {
        
        Connection retorno = null;
        try {
            
            //1 - parâmetros
            String driver = "com.mysql.cj.jdbc.Driver";
            String usuario = "root";
            String senha = "root";
            String url = "jdbc:mysql://localhost:3307/projetoatm?zeroDateTimeBehavior=CONVERT_TO_NULL";
            //2 - carregar o driver na memória
            Class.forName(driver);
            //3 - conectar com o banco
            retorno = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return retorno;
    }
    
    public static void desconectar(Connection conn) {
        
        try {
            
            if(conn != null && !conn.isClosed()) {
                
                conn.close();
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
