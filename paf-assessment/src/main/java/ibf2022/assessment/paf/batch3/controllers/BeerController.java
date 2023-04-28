package ibf2022.assessment.paf.batch3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	BeerRepository beerRepository;

	@Autowired
	BeerService beerService;

	//TODO Task 2 - view 0
	@GetMapping
	public String getLandingPage(Model model) {
		List<Style> styles = new ArrayList<>();
		styles = beerRepository.getStyles();
		model.addAttribute("styles", styles);
		return "view0";
	}
	
	
	//TODO Task 3 - view 1
	@GetMapping(path = "/beer/style/{styleId}")
	public String getBreweriesByBeer(Model model, @PathVariable("styleId") String styleId, @RequestParam("styleName") String styleName) {
		List<Beer> beers = new ArrayList<>();
		beers = beerRepository.getBreweriesByBeer(styleId);

		if (beers.size() == 0) {
			model.addAttribute("message", "No beers found for this style");
			return "view1";
		}

		model.addAttribute("beers", beers);
		model.addAttribute("styleName", styleName);
		return "view1";
	}

	//TODO Task 4 - view 2
	@GetMapping(path = "/beer/brewery/{breweryId}")
	public String getBeersFromBrewery(Model model, @PathVariable("breweryId") String breweryId) {
		Optional<Brewery> brewery = beerRepository.getBeersFromBrewery(breweryId);

		if (!brewery.isPresent()) {
			model.addAttribute("error", "Brewery not found");
			return "view2";
		}

		List<Beer> beers = brewery.get().getBeers();
		model.addAttribute("beers", beers);
		model.addAttribute("brewery", brewery.get());
		return "view2";
	}

	
	//TODO Task 5 - view 2, place order
	@PostMapping(path = "/brewery/{breweryId}/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String orderBeerByPost(Model model, @PathVariable String breweryId, @RequestParam MultiValueMap<String, String> payload) {
		String orderId = beerService.placeOrder(Integer.parseInt(breweryId), payload);
		model.addAttribute("orderId", orderId);
		return "view3";
	}

}
