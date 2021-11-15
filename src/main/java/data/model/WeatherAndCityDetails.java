package data.model;


import com.google.gson.annotations.SerializedName;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ToString
public class WeatherAndCityDetails implements Serializable {
    @SerializedName("consolidated_weather")
    private List<Weather> consolidatedWeather;

    @SerializedName("time")
    private LocalDateTime time;

    @SerializedName("sun_rise")
    private LocalDateTime sunRise;

    @SerializedName("sun_set")
    private LocalDateTime sunSet;

    @SerializedName("timezone_name")
    private String timeZoneName;

    private LocationDetails parent;

    private List<Source> sources;

    private String title;

    @SerializedName("locationType")
    private String location_type;

    private Integer woeid;

    @SerializedName("latt_long")
    private String lattLong;

    @SerializedName("timezone")
    private String timezone;
}
