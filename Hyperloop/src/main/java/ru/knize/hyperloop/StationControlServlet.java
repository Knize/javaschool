package ru.knize.hyperloop;

import com.google.gson.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.StationEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by knize on 04.09.16.
 */
public class StationControlServlet extends HttpServlet {

    private static class StationEntitySerializer implements JsonSerializer<StationEntity> {

        @Override
        public JsonElement serialize(StationEntity stationEntity, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            result.addProperty("id", stationEntity.getStationId());
            result.addProperty("name", stationEntity.getStationName());
            result.addProperty("index", stationEntity.getStationIndex());
            result.addProperty("timezone", stationEntity.getTimezone());
            result.addProperty("range", stationEntity.getRangeKm());
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
            Session s = HibernateUtil.getSessionFactory().openSession();
            result.setBranch(s.load(BranchEntity.class, jsonObject.get("branch").getAsInt()));
            s.close();
            return result;
        }
    }

    private Gson gson;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (req.getPathInfo().endsWith("/list")) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from StationEntity");
            List stationList = query.list();
            session.close();
            String json = gson.toJson(stationList);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (req.getPathInfo().endsWith("/update")) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            StationEntity[] entities = gson.fromJson(req.getReader(), StationEntity[].class);
            session.beginTransaction();
            for (StationEntity entity : entities) {
                session.saveOrUpdate(entity);
            }
            session.getTransaction().commit();
            session.close();
            resp.getWriter().write("\"ok\"");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StationEntity.class, new StationEntitySerializer());
        builder.registerTypeAdapter(StationEntity.class, new StationEntityDeserializer());
        gson = builder.create();
    }
}
