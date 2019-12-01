package earthquakes.osm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;



public class Place {
    private static Logger logger = LoggerFactory.getLogger(Place.class);
    public long place_id;
    public double lat;
    public double lon;
    public String display_name;
    public String type;

     /**
     * Create a list of Place objects from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new List of Place objects 
     * @see <a href=
     *      "https://stackoverflow.com/a/6349488">https://stackoverflow.com/a/6349488</a>
     */
    public static List<Place> listFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Place> placeObjects = objectMapper.readValue(json, new TypeReference<List<Place>>(){});

            return placeObjects;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }        
    }
}