package it.polito.tdp.metrodeparis.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class ArcoMetro extends DefaultWeightedEdge{

	
	private Linea linea;	
	
	
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 3258748305380352995L;

	
	

}
