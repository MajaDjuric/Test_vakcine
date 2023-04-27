package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class Prijava {

	private final long id;
	private String jmbg;
	private String imeIPrezime;
	Vakcina vakcina;
	private LocalDateTime datum;
	
	public Prijava(long id, String jmbg, String imeIPrezime, Vakcina vakcina, LocalDateTime datum) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.imeIPrezime = imeIPrezime;
		this.vakcina = vakcina;
		this.datum = datum;
	}
	public Prijava(String jmbg, String imeIPrezime, Vakcina vakcina, LocalDateTime datum) {
		super();
		this.id = 0;
		this.jmbg = jmbg;
		this.imeIPrezime = imeIPrezime;
		this.vakcina = vakcina;
		this.datum = datum;
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
		Prijava other = (Prijava) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Prijava [id=" + id + ", jmbg=" + jmbg + ", imeIPrezime=" + imeIPrezime + ", vakcina=" + vakcina.getNaziv()
				+ ", datum=" + datum + "]";
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
	public Vakcina getVakcina() {
		return vakcina;
	}
	public void setVakcina(Vakcina vakcina) {
		this.vakcina = vakcina;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	public long getId() {
		return id;
	}
	

	
}
