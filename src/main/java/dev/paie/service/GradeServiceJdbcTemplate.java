package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;
import dev.paie.util.PaieUtils;

@Service
public class GradeServiceJdbcTemplate implements GradeService {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	PaieUtils paieUtils;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void sauvegarder(Grade ng){
		
		this.jdbcTemplate.update("INSERT INTO grade (code,nbHeureBase,tauxBase) VALUES (?,?,?)", 
								ng.getCode(), paieUtils.formaterBigDecimal(ng.getNbHeuresBase()), paieUtils.formaterBigDecimal(ng.getTauxBase()));
		
	}
	
	@Override
	public void mettreAJour(Grade grade){
		
		this.jdbcTemplate.update("UPDATE grade SET code = ?, nbHeureBase = ?, tauxBase= ? where id = ?",
									grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase());
		
	}
	
	@Override
	public List<Grade> lister(){
		
		String sql = "SELECT * FROM grade";
		
		RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setId(rs.getInt("ID"));
			g.setCode(rs.getString("code"));
			g.setNbHeuresBase(rs.getBigDecimal("nbHeureBase"));
			g.setTauxBase(rs.getBigDecimal("tauxBase"));
			return g;
			};
		
		List<Grade> liste = jdbcTemplate.query(sql, mapper);
		
		return liste;

		
	}



}
