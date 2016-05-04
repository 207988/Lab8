package it.polito.tdp.metrodeparis.model;

public class Fermata {
	
	private int codF;
	private String nomeFermata;
	private double x;
	private double y;
	
	public Fermata(int codF, String nomeFermata, double x, double y) {
		super();
		this.codF = codF;
		this.nomeFermata = nomeFermata;
		this.x = x;
		this.y = y;
	}

	public int getCodF() {
		return codF;
	}

	public String getNomeFermata() {
		return nomeFermata;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nomeFermata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codF;
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
		Fermata other = (Fermata) obj;
		if (codF != other.codF)
			return false;
		return true;
	}
	
	
	
	
	

}
