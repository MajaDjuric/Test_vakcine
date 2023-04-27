package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dao.PrijavaDAO;
import dao.VakcinaDAO;
import model.Prijava;
import model.StavkaIzvestaja;
import model.Vakcina;
import util.Konzola;

public class IzvestajUI {

	private static VakcinaDAO vakcinaDAO;
	private static PrijavaDAO prijavaDAO;
	
	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		IzvestajUI.vakcinaDAO = vakcinaDAO;
	}
	public static void setPrijavaDAO(PrijavaDAO prijavaDAO) {
		IzvestajUI.prijavaDAO = prijavaDAO;
	}
	
	
	public static void izvestavanje () {
		LocalDateTime pocetni = Konzola.ocitajDateTime("Unesite pocetni datum");
		LocalDateTime krajnji = Konzola.ocitajDateTime("Unesite krajnji datum");
	
		
		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			List<StavkaIzvestaja> izvestaji = new ArrayList<>();
			Collection<Prijava> prijave = prijavaDAO.getAll();
			Set<String> nazivi = new LinkedHashSet<>();
			
			for (Vakcina vakcina : vakcine) {
				nazivi.add(vakcina.getNaziv());
			}
			
			for (String naziv : nazivi) {
				Collection<Prijava> prijaveUOpsegu = new HashSet<>();
				int ukupanBrojPrijava = 0;
				LocalDateTime datumPoslednjePrijave = null;
				
				for (Prijava prijava: prijave) {
										
					if (prijava.getVakcina().getNaziv().equals(naziv) && 
						prijava.getDatum().compareTo(pocetni) >= 0 &&
						prijava.getDatum().compareTo(krajnji) <= 0) {
						prijaveUOpsegu.add(prijava);
						}
					
						ukupanBrojPrijava = prijaveUOpsegu.size();
						
						for (Prijava prijavauOpsegu: prijaveUOpsegu) {
							if (prijaveUOpsegu.isEmpty()) {
								datumPoslednjePrijave = null;
							}else if (prijava.getDatum().compareTo(prijavauOpsegu.getDatum()) > 0) {
								datumPoslednjePrijave = prijava.getDatum();
							}
						}
						
						}
				StavkaIzvestaja stavka = new StavkaIzvestaja(naziv, ukupanBrojPrijava, datumPoslednjePrijave);
				izvestaji.add(stavka);
				}
			
			izvestaji.sort(StavkaIzvestaja :: compareUkupanBroj);
			
			for (StavkaIzvestaja stavka : izvestaji) {
				System.out.println(stavka);
				}
		}catch (Exception e) {
			System.out.println("Doslo je do greske");
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
