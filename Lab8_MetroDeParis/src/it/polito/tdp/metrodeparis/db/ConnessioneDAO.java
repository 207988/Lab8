package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.model.Connessione;

public class ConnessioneDAO {
private String jdbcURL = "jdbc:mysql://localhost/metroparis?user=root";	
	
	public List<Connessione> trovaConnessioni(){
		
		List<Connessione>elencoConn=new ArrayList<Connessione>();		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			String sql= "SELECT  * FROM connessione ";			
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			ResultSet res=st.executeQuery();
			
			while(res.next()){
				Connessione con= new Connessione(res.getInt("id_linea"),res.getInt("id_stazP"),res.getInt("id_stazA"));
				elencoConn.add(con);
			}
			
			res.close();
			conn.close();
			return elencoConn;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
		
	}
}
