package it.polito.tdp.metrodeparis.model;

public class Connessione {

	private int idLinea;
	private int idP;
	private int idA;
	
	public Connessione(int idLinea, int idP, int idA) {
		super();
		this.idLinea = idLinea;
		this.idP = idP;
		this.idA = idA;
	}

	public int getIdLinea() {
		return idLinea;
	}

	public int getIdP() {
		return idP;
	}

	public int getIdA() {
		return idA;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if ((idA != other.idA))
			return false;
		if ((idP != other.idP))
			return false;
		return true;
	}
	
	
	
}
