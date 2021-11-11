package data.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LocationDetails implements Serializable {
    @SerializedName("title")
    private String title;

    @SerializedName("location_type")
    private String locationType;

    @SerializedName("woeid")
    private Integer woeid;

    @SerializedName("latt_long")
    private String lattLong;
}
