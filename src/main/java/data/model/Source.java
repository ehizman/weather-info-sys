package data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Source implements Serializable {
    private String title;

    private String slug;

    private String url;

    @SerializedName("crawl_rate")
    private Integer crawlRate;
}
