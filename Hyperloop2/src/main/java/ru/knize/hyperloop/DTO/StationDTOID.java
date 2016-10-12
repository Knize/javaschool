package ru.knize.hyperloop.DTO;


import ru.knize.hyperloop.entities.StationEntity;

/**
 * Created by knize on 06.10.16.
 */
public class StationDTOID {
    private int id;
    private String name;
    private String timezone;
    private double latitude;
    private double longitude;

    public StationDTOID() {
    }

    @Override
    public String toString() {
        return "StationDTOID{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitudeStr) {
        try {
            this.latitude = Double.parseDouble(latitudeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        try {
            this.longitude = Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }



    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public StationDTO toStationDTO(){
        StationDTO stationDTO = new StationDTO();
        stationDTO.setName(this.name);
        stationDTO.setTimezone(this.timezone);
        stationDTO.setLatitude(Double.toString(this.latitude));
        stationDTO.setLongitude(Double.toString(this.longitude));
        return stationDTO;
    }

    public static StationDTOID fromStationEntity(StationEntity se){
        StationDTOID stationDTOID = new StationDTOID();
        stationDTOID.setId(Integer.toString(se.getId()));
        stationDTOID.setName(se.getName());
        stationDTOID.setTimezone(se.getTimezone());
        stationDTOID.setLatitude(Double.toString(se.getLatitude()));
        stationDTOID.setLongitude(Double.toString(se.getLongitude()));

        return stationDTOID;
    }


}
