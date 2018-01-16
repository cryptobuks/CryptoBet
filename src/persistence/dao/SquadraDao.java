package persistence.dao;

import java.util.List;

import model.footballdata.Squadra;

public interface SquadraDao {

	public void save(Squadra squadra);  // Create
	public Squadra findByPrimaryKey(String nome);     // Retrieve
	public List<Squadra> findAll();       
	public void update(Squadra squadra);
}
