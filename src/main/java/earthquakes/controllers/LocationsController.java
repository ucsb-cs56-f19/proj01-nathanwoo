package earthquakes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.HashMap;

import earthquakes.geojson.FeatureCollection;
import earthquakes.services.LocationQueryService;
import earthquakes.searches.LocSearch;
import earthquakes.osm.Place;
import earthquakes.entities.Location;
import earthquakes.repositories.LocationRepository;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

import java.util.List;

@Controller
public class LocationsController {

    private LocationRepository locationRepository;

    // @Autowired
    // private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public LocationsController (LocationRepository locationRepository) {
        this.locationRepository = locationRepository;   
    }

    @GetMapping("/locations/search")
    public String getLocationsSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
        return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationsResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
            LocationQueryService e = new LocationQueryService();
        model.addAttribute("locSearch", locSearch);
        String json = e.getJSON(locSearch.getLocation());
        model.addAttribute("json",json);
        List<Place> placeCollection = Place.listFromJson(json);
        model.addAttribute("placeCollection",placeCollection);
        return "locations/results";
    }

    @GetMapping("/locations")
    public String index(Model model) {
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locations/index";
    }

    // @GetMapping("/earthquakes/results")
    // public String getEarthquakesResults(Model model, OAuth2AuthenticationToken
    // oAuth2AuthenticationToken,
    // EqSearch eqSearch) {
    // EarthquakeQueryService e = new EarthquakeQueryService();
    // model.addAttribute("eqSearch", eqSearch);
    // String json = e.getJSON(eqSearch.getDistance(), eqSearch.getMinmag());
    // model.addAttribute("json", json);
    // FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
    // model.addAttribute("featureCollection",featureCollection);
    // return "earthquakes/results";
    // }
}
