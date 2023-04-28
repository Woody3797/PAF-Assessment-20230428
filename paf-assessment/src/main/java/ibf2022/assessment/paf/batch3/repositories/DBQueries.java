package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {
    
    public static final String GET_STYLES = """
        select styles.id as styleId, style_name as 'name', count(*) as beerCount from styles join beers on styles.id=beers.style_id group by style_name, styles.id order by count(*) desc, style_name asc 
    """; 

    public static final String GET_BREWERIES_BY_BEER = """ 
        select beers.id as beerId, beers.name as beerName, beers.descript as beerDescription, breweries.name as breweryName, breweries.id as breweryId from beers join breweries on beers.brewery_id=breweries.id where beers.style_id = ? order by beers.name asc;
    """;

    public static final String GET_BEERS_FROM_BREWERY = """ 
        select beers.brewery_id as breweryId, beers.id as beerId, beers.name as beerName, beers.descript as beerDescription, breweries.name as name, breweries.descript as 'description', breweries.address1 as address1, breweries.address2 as address2, breweries.city as city, breweries.phone as phone, breweries.website as website from beers join breweries on beers.brewery_id=breweries.id where breweries.id = ? order by beerName asc
    """;

}
