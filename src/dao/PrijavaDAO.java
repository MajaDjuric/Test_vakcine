package dao;

import java.util.Collection;

import model.Prijava;

public interface PrijavaDAO {

	Collection<Prijava> getAll() throws Exception;
	void add (Prijava prijava) throws Exception;
}
