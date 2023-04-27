package model;

import java.time.LocalDateTime;

import util.Konzola;

public class StavkaIzvestaja {

	private String nazivVakcine;
	private int ukupanBrojPrijavaZaVakcinu;
	LocalDateTime datumPoslednjePrijave;
	
	@Override
	public String toString() {
		return "Vakcina: " + nazivVakcine + "   Ukupan broj prijava:"
				+ ukupanBrojPrijavaZaVakcinu + "   Datum poslednje prijave:" + 
				(datumPoslednjePrijave == null ? "/" : Konzola.formatiraj(datumPoslednjePrijave));
	}

	public StavkaIzvestaja(String nazivVakcine, int ukupanBrojPrijavaZaVakcinu, LocalDateTime datumPoslednjePrijave) {
		super();
		this.nazivVakcine = nazivVakcine;
		this.ukupanBrojPrijavaZaVakcinu = ukupanBrojPrijavaZaVakcinu;
		this.datumPoslednjePrijave = datumPoslednjePrijave;
	}
	
	public static int compareUkupanBroj (StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return Integer.compare(stavka1.ukupanBrojPrijavaZaVakcinu, stavka2.ukupanBrojPrijavaZaVakcinu);
	}

	public String getNazivVakcine() {
		return nazivVakcine;
	}

	public void setNazivVakcine(String nazivVakcine) {
		this.nazivVakcine = nazivVakcine;
	}

	public int getUkupanBrojPrijavaZaVakcinu() {
		return ukupanBrojPrijavaZaVakcinu;
	}

	public void setUkupanBrojPrijavaZaVakcinu(int ukupanBrojPrijavaZaVakcinu) {
		this.ukupanBrojPrijavaZaVakcinu = ukupanBrojPrijavaZaVakcinu;
	}

	public LocalDateTime getDatumPoslednjePrijave() {
		return datumPoslednjePrijave;
	}

	public void setDatumPoslednjePrijave(LocalDateTime datumPoslednjePrijave) {
		this.datumPoslednjePrijave = datumPoslednjePrijave;
	}
	
}
