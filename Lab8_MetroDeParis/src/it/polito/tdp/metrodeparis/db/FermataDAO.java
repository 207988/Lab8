package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.model.Fermata;



public class FermataDAO {
	private String jdbcURL = "jdbc:mysql://localhost/metroparis?user=root";	
	
	public List<Fermata> trovaFermate(){
		
		List<Fermata>elencoFermate=new ArrayList<Fermata>();		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			String sql= "SELECT * FROM fermata;";			
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			ResultSet res=st.executeQuery();
			
			while(res.next()){
				Fermata f= new Fermata(res.getInt("id_fermata"),res.getString("nome"),res.getDouble("coordX"),res.getDouble("coordY"));
				elencoFermate.add(f);
			}
			
			res.close();
			conn.close();
			return elencoFermate;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
		
	}
	

}