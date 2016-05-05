package it.polito.tdp.metrodeparis.model;

public class Linea {
	
	private int idLinea;
	private String nomeLinea;
	private double vel;
	private double intervallo;
	
	public Linea(int idLinea, String nomeLinea, double vel, double intervallo) {
		super();
		this.idLinea = idLinea;
		this.nomeLinea = nomeLinea;
		this.vel = vel;
		this.intervallo = intervallo*60;
	}

	public int getIdLinea() {
		return idLinea;
	}

	public String getNomeLinea() {
		return nomeLinea;
	}

	public double getVel() {
		return vel;
	}

	public double getIntervallo() {
		return intervallo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLinea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeLinea;
	}
	
	
	

}
