package main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)


public class Schedules {

    List<SearchResult> search_results;

    public void setSearch_results(List<SearchResult> search_results) {
        this.search_results = search_results;
    }

    public List<SearchResult> getSearch_results() {
        return search_results;
    }
}

