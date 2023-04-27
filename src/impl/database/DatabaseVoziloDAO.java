package impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.VakcinaDAO;
import model.Vakcina;

public class DatabaseVoziloDAO implements VakcinaDAO {

	private final Connection conn;

	public DatabaseVoziloDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Collection<Vakcina> getAll() throws Exception {
		Collection<Vakcina> vakcine = new ArrayList<>();
		String sql = "SELECT id, naziv,tip, temperaturaCuvanja FROM vakcine";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					String tip = rset.getString(++kolona);
					int temperatura = rset.getInt(++kolona);
					
					Vakcina vakcina = new Vakcina(id, naziv, tip, temperatura);
					vakcine.add(vakcina);
				}
			}
		}
		return vakcine;
	}

	@Override
	public Vakcina get(long id) throws Exception {
			Vakcina vakcina = null;
			String sql = "SELECT naziv,tip, temperaturaCuvanja FROM vakcine WHERE id = ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setLong(1, id);
				try(ResultSet rset = stmt.executeQuery()){
					while(rset.next()) {
						int kolona = 0;
						String naziv = rset.getString(++kolona);
						String tip = rset.getString(++kolona);
						int temperatura = rset.getInt(++kolona);
						
						vakcina = new Vakcina(id, naziv, tip, temperatura);
					}
				}
			}
			return vakcina;
	}
	
}
