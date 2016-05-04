package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;

public class LineaDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost/metroparis?user=root";	
	
	public List<Linea> trovaLinee(){
		
		List<Linea>elencoLinee=new ArrayList<Linea>();		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			String sql= "SELECT * FROM linea;";			
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			ResultSet res=st.executeQuery();
			
			while(res.next()){
				Linea l= new Linea(res.getInt("id_linea"),res.getString("nome"),res.getDouble("velocita"),res.getDouble("intervallo"));
				elencoLinee.add(l);
			}
			
			res.close();
			conn.close();
			return elencoLinee;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
		
	}

}
