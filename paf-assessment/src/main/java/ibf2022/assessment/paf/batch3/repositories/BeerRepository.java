package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		List<Style> styles = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(DBQueries.GET_STYLES);
		while (rs.next()) {
			Style style = new Style();
			style.setStyleId(rs.getInt("styleId"));
			style.setName(rs.getString("name"));
			style.setBeerCount(rs.getInt("beerCount"));
			styles.add(style);
		}
		return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(String styleId) {
		// TODO: Task 3
		List<Beer> beers = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(DBQueries.GET_BREWERIES_BY_BEER, Integer.parseInt(styleId));
		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beerId"));
			beer.setBeerName(rs.getString("beerName"));
			beer.setBeerDescription(rs.getString("beerDescription"));
			beer.setBreweryId(rs.getInt("breweryId"));
			beer.setBreweryName(rs.getString("breweryName"));
			beers.add(beer);
		}
		return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(String breweryId) {
		// TODO: Task 4
		Brewery brewery = new Brewery();
		List<Beer> beers = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(DBQueries.GET_BEERS_FROM_BREWERY, Integer.parseInt(breweryId));
		while (rs.next()) {
			brewery.setBreweryId(rs.getInt("breweryId"));
			brewery.setName(rs.getString("name"));
			brewery.setAddress1(rs.getString("address1"));
			brewery.setAddress2(rs.getString("address2"));
			brewery.setCity(rs.getString("city"));
			brewery.setPhone(rs.getString("phone"));
			brewery.setWebsite(rs.getString("website"));
			brewery.setDescription(rs.getString("description"));
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beerId"));
			beer.setBeerName(rs.getString("beerName"));
			beer.setBeerDescription(rs.getString("beerDescription"));
			beers.add(beer);
		}
		brewery.setBeers(beers);		
		return Optional.of(brewery);
	}
}
