package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vakcina {

	private final long id;
	private String naziv;
	private String tip;
	private int temperatura;
	final Set<Prijava> prijave = new HashSet<>();
	
	public Vakcina(long id, String naziv, String tip, int temperatura) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.temperatura = temperatura;
	}
	public Vakcina( String naziv, String tip, int temperatura) {
		super();
		this.id = 0;
		this.naziv = naziv;
		this.tip = tip;
		this.temperatura = temperatura;
	}
	public Vakcina() {
		super();
		this.id = 0;
		this.naziv = "";
		this.tip = "";
		this.temperatura = 0;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vakcina other = (Vakcina) obj;
		return id == other.id;
	}
	
	String predznak () {
		String predznak = "";
		if (temperatura > 0) {
		 predznak = "+";
		}
		return predznak;
	}
	
	@Override
	public String toString() {
		return "Vakcina [id=" + id + ", naziv=" + naziv + ", tip=" + tip + ", temperatura=" + predznak() + temperatura + "]";
	}
	public Collection<Prijava> getPrijave() {
		return Collections.unmodifiableCollection(prijave);
	} 
	public void dodajJednuPrijavu (Prijava prijava) {
		this.prijave.add(prijava);
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	public long getId() {
		return id;
	}
	
	
	public Collection<Prijava> getPrijaveUOpsegu (String naziv, LocalDateTime pocetni, LocalDateTime krajnji) {
		Collection<Prijava> prijaveUOpsegu = new HashSet<>();
		for (Prijava prijava : prijave) {
			if (prijava.getVakcina().getNaziv().equals(naziv) &&
				prijava.getDatum().compareTo(pocetni) >= 0 &&
				prijava.getDatum().compareTo(krajnji) <= 0) {
				prijave.add(prijava);
			}
		}
		return prijave;
	}
	
	
}
