package dev.paie.service;

import java.sql.ResultSet;
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
		
		jdbcTemplate.update("INSERT INTO grade (code,nbHeuresBase,tauxBase) VALUES (?,?,?)", 
								ng.getCode(), paieUtils.formaterBigDecimal(ng.getNbHeuresBase()), paieUtils.formaterBigDecimal(ng.getTauxBase()));
		
	}
	
	@Override
	public void mettreAJour(Grade grade){
		
		jdbcTemplate.update("UPDATE grade SET code = ?, nbHeuresBase = ?, tauxBase= ? where id = ?",
									grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
		
	}
	
	@Override
	public List<Grade> lister(){
		
		String sql = "SELECT * FROM grade";
		
		RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setId(rs.getInt("ID"));
			g.setCode(rs.getString("code"));
			g.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
			g.setTauxBase(rs.getBigDecimal("tauxBase"));
			return g;
			};
		
		
		return jdbcTemplate.query(sql, mapper);

		
	}



}
