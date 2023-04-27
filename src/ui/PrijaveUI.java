package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import dao.PrijavaDAO;
import model.Prijava;
import model.Vakcina;
import util.Konzola;

public class PrijaveUI {

	private static PrijavaDAO prijavaDAO;

	public static void setPrijavaDAO(PrijavaDAO prijavaDAO) {
		PrijaveUI.prijavaDAO = prijavaDAO;
	}
	
	public static void dodaj () {
		try {
			Collection<Prijava> prijave = prijavaDAO.getAll();
			String jmbg = "";
			while(jmbg.equals("")) {
				jmbg = Konzola.ocitajString("Unesite jmbg");
			}
			for (Prijava prijava : prijave) {
				if (prijava.getJmbg().equals(jmbg)) {
					System.out.println("Prijava sa navedenim jbmg-om vec postoji");
					return;
				}
			}
			String ime = Konzola.ocitajString("Unesite ime i prezime");
			Vakcina vakcina = VakcinaUI.pretraga();
			if (vakcina == null) {
				return;
			}
			LocalDateTime datum = LocalDateTime.now();
			
			Prijava prijava = new Prijava(jmbg, ime, vakcina, datum);
			prijavaDAO.add(prijava);
			System.out.println("Uspesno dodavanje");
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
}
	
	
	public static void prikazSvihSaVakcinama () {
		try {
			Collection<Prijava> prijave = prijavaDAO.getAll();
			System.out.println();
			for (Prijava prijava : prijave) {
				System.out.println(prijava);
			}
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}
