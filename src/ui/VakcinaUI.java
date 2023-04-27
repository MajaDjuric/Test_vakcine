package ui;

import java.util.Collection;

import dao.VakcinaDAO;
import model.Vakcina;
import util.Konzola;

public class VakcinaUI {

	private static VakcinaDAO vakcinaDAO;

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		VakcinaUI.vakcinaDAO = vakcinaDAO;
	}
	
	public static Vakcina pretraga () throws Exception {
		prikazvSvih();
		long id = Konzola.ocitajLong("Unesite id vakcine");
		Vakcina vakcina = vakcinaDAO.get(id);
		if (vakcina == null) {
			System.out.println("Vakcina nije pronadjena");
		}
		return vakcina;
	}
	
	public static void prikazvSvih() {
		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			System.out.println();
			for (Vakcina vakcina : vakcine) {
				System.out.println(vakcina);
			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
	}
	
}
