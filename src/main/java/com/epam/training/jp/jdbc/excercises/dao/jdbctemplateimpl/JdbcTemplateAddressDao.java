package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;

public class JdbcTemplateAddressDao extends JdbcDaoSupport implements AddressDao {
	
	public JdbcTemplateAddressDao(DataSource dataSource) {		
		setDataSource(dataSource);
	}
	
	@Override
	public void save(Address address) {
		/*this.getJdbcTemplate().update("INSERT INTO ADDRESS " + " (CITY, COUNTRY, STREET, ZIPCODE) " + " VALUES (?, ?, ?, ?)",
				address.getCity(),
				address.getCountry(),
				address.getStreet(),
				address.getZipCode());*/
		
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(getDataSource())
			.withTableName("ADDRESS")
			.usingGeneratedKeyColumns("ID");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("city", address.getCity());
		parameters.put("country", address.getCountry());
		parameters.put("street", address.getStreet());
		parameters.put("zipCode", address.getZipCode());
		
		Number newId = insertActor.executeAndReturnKey(parameters);
		address.setId(newId.intValue());
	}

}
