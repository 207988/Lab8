package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.ConnessioneDAO;
import it.polito.tdp.metrodeparis.db.FermataDAO;
import it.polito.tdp.metrodeparis.db.LineaDAO;



public class MetroDeParisModel {
	
	private WeightedMultigraph<Fermata,DefaultWeightedEdge> metro=new WeightedMultigraph<Fermata,DefaultWeightedEdge>(DefaultWeightedEdge.class);
	private HashMap<Integer,Fermata>fermate=new HashMap<Integer,Fermata>();
	private HashMap<Integer,Linea>linee=new HashMap<Integer,Linea>();
	private List<Connessione>conn=new ArrayList<Connessione>();
	
	public void caricaGrafo(){
		LineaDAO lindao=new LineaDAO();
		FermataDAO ferdao=new FermataDAO();
		ConnessioneDAO condao=new ConnessioneDAO();
		
		//aggiungo fermate a lista ed a vertici grafo
		for(Fermata f:ferdao.trovaFermate()){
			fermate.put(f.getCodF(),f);
			metro.addVertex(f);
		}
		
		//aggiungi linee a lista
		for(Linea l:lindao.trovaLinee()){
			linee.put(l.getIdLinea(),l);
		}
		
		conn=condao.trovaConnessioni();			
			
		//System.out.println(conn.size());
		
		for(Connessione c:conn){
			
			if(!metro.containsEdge(fermate.get(c.getIdP()), fermate.get(c.getIdA()))){
				Fermata f1=fermate.get(c.getIdP());
				Fermata f2=fermate.get(c.getIdA());
				DefaultWeightedEdge dwe=metro.addEdge(f1,f2);
				metro.setEdgeWeight(dwe, this.calcolaTempo(f1, f2, linee.get(c.getIdLinea())));
			}
				
		}
		
		
		System.out.println(metro.vertexSet().size());
		System.out.println(metro.edgeSet().size());
		
	}
	
	public String camminoMinimo(Fermata f1,Fermata f2){
		String s="";
		List<DefaultWeightedEdge>temp=DijkstraShortestPath.findPathBetween(metro, f1, f2);
		/*
		s+="Percorso: [ "+f1.toString();
		
		for(DefaultWeightedEdge dwe:temp){
			s+=" ";
		}
		
		
		s+="Tempo stimato: "+(temp.size()*30)/60;*/
		s+=temp.toString();
		return s;
		
	}
	
	
	
	public List<Fermata> getFermate() {
		return new ArrayList<Fermata>(fermate.values());
	}



	public List<Linea> getLinee() {
		return new ArrayList<Linea>(linee.values());
	}



	private double calcolaTempo(Fermata f1,Fermata f2,Linea l){
		LatLng pos1= new LatLng(f1.getX(),f1.getY());
		LatLng pos2= new LatLng(f2.getX(),f2.getY());
		return LatLngTool.distance(pos1,pos2,LengthUnit.KILOMETER)/l.getVel();
	}
	
	
}
