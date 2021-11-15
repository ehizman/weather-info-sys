package data.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {
    private Long id;

    @SerializedName("weather_state_name")
    private String weatherStateName;

    @SerializedName("weather_state_abbr")
    private String weatherStateAbbr;

    @SerializedName("wind_direction_compass")
    private String windDirectionCompass;

    @SerializedName("created")
    private LocalDateTime createdAt;

    @SerializedName("applicable_date")
    private LocalDate applicableDate;

    @SerializedName("min_temp")
    private Double minTemp;

    @SerializedName("max_temp")
    private Double maxTemp;

    @SerializedName("the_temp")
    private Double theTemp;

    @SerializedName("wind_speed")
    private Double windSpeed;

    @SerializedName("wind_direction")
    private Double windDirection;

    @SerializedName("air_pressure")
    private Double airPressure;

    @SerializedName("humidity")
    private Integer humidity;

    @SerializedName("visibility")
    private Double visibility;

    @SerializedName("predictability")
    private Integer predictability;

}
