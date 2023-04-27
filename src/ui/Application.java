package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.PrijavaDAO;
import dao.VakcinaDAO;
import impl.database.DatabasePrijavaDAO;
import impl.database.DatabaseVoziloDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/vakcine?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");
		
		VakcinaDAO vakcinaDAO = new DatabaseVoziloDAO(conn);
		VakcinaUI.setVakcinaDAO(vakcinaDAO);
		PrijavaDAO prijavaDAO = new DatabasePrijavaDAO(conn, vakcinaDAO);
		PrijaveUI.setPrijavaDAO(prijavaDAO);
		IzvestajUI.setPrijavaDAO(prijavaDAO);
		IzvestajUI.setVakcinaDAO(vakcinaDAO);
		;

	}
	
	static {
		try {
			initDatabase();
		} catch (Exception e) {
			System.out.println("Doslo je do greske pri povezivanju sa bazom");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Meni.pokreni("Vakcine", new StavkaMenija [] {
				new IzlaznaStavkaMenija ("Izlaz"),
				new FunkcionalnaStavkaMenija ("Prikaz svih vakcina") {

					@Override
					public void izvrsi() {
						VakcinaUI.prikazvSvih();
						
					}
					
				},
				new FunkcionalnaStavkaMenija ("Dodavanje prijave") {

					@Override
					public void izvrsi() {
						PrijaveUI.dodaj();
						
					}
					
				},
				new FunkcionalnaStavkaMenija ("Prikaz svih prijava sa vakcinama") {

					@Override
					public void izvrsi() {
						PrijaveUI.prikazSvihSaVakcinama();
						
					}
					
				},
				new FunkcionalnaStavkaMenija ("Izvestaj") {

					@Override
					public void izvrsi() {
						IzvestajUI.izvestavanje();
						
					}
					
				},
		});
	}
}

