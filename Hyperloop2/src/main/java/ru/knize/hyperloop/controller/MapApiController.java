package ru.knize.hyperloop.controller;

import com.google.gson.*;
import org.hibernate.Session;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.knize.hyperloop.HibernateUtil;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.StationEntity;

import java.lang.reflect.Type;

/**
 * Created by knize on 04.10.16.
 */
@Controller
public class MapApiController {

    private Gson gson;

    private static class StationEntitySerializer implements JsonSerializer<StationEntity> {
        @Override
        public JsonElement serialize(StationEntity stationEntity, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            result.addProperty("id", stationEntity.getStationId());
            result.addProperty("name", stationEntity.getStationName());
            result.addProperty("index", stationEntity.getStationIndex());
            result.addProperty("timezone", stationEntity.getTimezone());
            result.addProperty("rangeKm", stationEntity.getRangeKm());
            result.addProperty("branch", stationEntity.getBranch().getId());
            result.addProperty("lat", stationEntity.getLatitude());
            result.addProperty("lng", stationEntity.getLongitude());
            return result;
        }
    }

    private static class StationEntityDeserializer implements JsonDeserializer<StationEntity> {

        @Override
        public StationEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = (JsonObject) jsonElement;
            StationEntity result = new StationEntity();
            if (jsonObject.has("id")) {
                result.setStationId(jsonObject.get("id").getAsInt());
            }
            result.setLatitude(jsonObject.get("lat").getAsDouble());
            result.setLongitude(jsonObject.get("lng").getAsDouble());
            result.setStationName(jsonObject.get("name").getAsString());
            result.setTimezone(jsonObject.get("timezone").getAsString());
            result.setStationIndex(jsonObject.get("index").getAsInt());
            result.setRangeKm(jsonObject.get("rangeKm").getAsInt());
            Session s = HibernateUtil.getSessionFactory().openSession();
            result.setBranch(s.load(BranchEntity.class, jsonObject.get("branch").getAsInt()));
            s.close();
            return result;
        }
    }


}
