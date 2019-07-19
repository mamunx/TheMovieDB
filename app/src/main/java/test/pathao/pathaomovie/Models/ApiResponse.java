package test.pathao.pathaomovie.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse {

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<MovieDetail> results;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MovieDetail> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieDetail> results) {
        this.results = results;
    }
}
