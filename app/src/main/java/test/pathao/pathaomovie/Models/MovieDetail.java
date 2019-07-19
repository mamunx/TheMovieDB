package test.pathao.pathaomovie.Models;

import com.google.gson.annotations.SerializedName;

public class MovieDetail {

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String imageUrl;

    @SerializedName("release_date")
    private String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
