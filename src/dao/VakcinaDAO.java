package dao;

import java.util.Collection;

import model.Vakcina;

public interface VakcinaDAO {

	Vakcina get(long id) throws Exception;
	Collection<Vakcina> getAll () throws Exception;
}
