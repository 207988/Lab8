package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.ConnessioneDAO;
import it.polito.tdp.metrodeparis.db.FermataDAO;
import it.polito.tdp.metrodeparis.db.LineaDAO;



public class MetroDeParisModel {
	
	private DirectedWeightedMultigraph<Fermata,ArcoMetro> metro=new DirectedWeightedMultigraph<Fermata,ArcoMetro>(ArcoMetro.class);
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
			
			//if(!metro.containsEdge(fermate.get(c.getIdP()), fermate.get(c.getIdA()))){
				Fermata f1=fermate.get(c.getIdP());
				//f1.aggiungiLinea(linee.get(c.getIdLinea()));
				Fermata f2=fermate.get(c.getIdA());
				ArcoMetro dwe=metro.addEdge(f1,f2);
				metro.setEdgeWeight(dwe, this.calcolaTempo(f1, f2, linee.get(c.getIdLinea())));
				dwe.setLinea(linee.get(c.getIdLinea()));
			//}
				
		}
		
		
		System.out.println(metro.vertexSet().size());
		System.out.println(metro.edgeSet().size());
		
	}
	
	public String camminoMinimo(Fermata f1,Fermata f2){
		String s="";
		List<ArcoMetro>temp=DijkstraShortestPath.findPathBetween(metro, f1, f2);
		
		double tempo=0.0;
		
		s+="Percorso: "+f1.toString()+"-";
		Linea oldLinea=null;
		
		for(ArcoMetro dwe:temp){
			//double intervallo=dwe.getLinea().getIntervallo();
			//TEMPO PERCORSO+30SEC
			tempo+=((metro.getEdgeWeight(dwe)*3600)+30);
			
			if(oldLinea!=dwe.getLinea()){
				//CAMBIO LINEA
				if(oldLinea!=null){
					s+="\nCAMBIO LINEA->"+dwe.getLinea()+"\n";
					tempo+=dwe.getLinea().getIntervallo()-30;
				}
				//IL PRIMO NODO DOVRA' POTER USARE PER FORZA LA LINEA DEL PRIMO ARCO
				//QUINDI NON CONTO INTERVALLI
				
			}
				
			s+=metro.getEdgeTarget(dwe).getNomeFermata()+"-";
			oldLinea=dwe.getLinea();
		}
		s+=f2.toString()+" ]\n";
		
		
		
		
		//s+="Tempo stimato: "+tempo/(double)(60*60);
		//s+=temp.toString();
		return this.formatTempo(tempo, s);
		
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
	
	private String formatTempo(double tempo,String s){
		s+="Tempo stimato: ";
		if(tempo<60)
			s+=tempo+ "secondi.";
		else if(tempo<3600){
			int min= (int)tempo/60;
			int sec= (int)tempo%60;
			s+=min+ " minuti e "+sec+" secondi.";
		}
		else{
			int h=(int)tempo/3600;
			int min= (int)((tempo%3600)/60);
			int sec= (int)((tempo%3600)%60);
			s+=h+" ore e "+min+ " minuti e "+sec+" secondi.";
		}
		return s;
	}
}
