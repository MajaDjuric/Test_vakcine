package impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import dao.PrijavaDAO;
import dao.VakcinaDAO;
import model.Prijava;
import model.Vakcina;

public class DatabasePrijavaDAO  implements PrijavaDAO{

	private final Connection conn;
	private final VakcinaDAO vakcinaDAO;

	public DatabasePrijavaDAO(Connection conn, VakcinaDAO vakcinaDAO) {
		super();
		this.conn = conn;
		this.vakcinaDAO = vakcinaDAO;
	}

	@Override
	public void add(Prijava prijava) throws Exception {
		String sql = "INSERT INTO prijave (jmbg, imeIPrezime, vakcinaId, datum) VALUES (?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, prijava.getJmbg());
			stmt.setString(++param, prijava.getImeIPrezime());
			stmt.setLong(++param, prijava.getVakcina().getId());
			stmt.setTimestamp(++param, Timestamp.valueOf(prijava.getDatum()));
			
			stmt.executeUpdate();
		}
	}

	@Override
	public Collection<Prijava> getAll() throws Exception {
		Collection<Prijava> prijave = new ArrayList<>();
		String sql = "SELECT id, jmbg, imeIPrezime, vakcinaId, datum FROM prijave";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String jmbg = rset.getString(++kolona);
					String imeIPrezime = rset.getString(++kolona);
					long vakcinaId = rset.getLong(++kolona);
					LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
					
					Vakcina vakcina = vakcinaDAO.get(vakcinaId);
					Prijava prijava = new Prijava(id, jmbg, imeIPrezime, vakcina, datum);
					prijave.add(prijava);
				}
			}
		}
		return prijave;
	}
}
