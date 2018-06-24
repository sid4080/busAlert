package main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true )
public class SearchResult {

    Integer available_seat_count;
    String dep_time;

    public void setAvailable_seat_count(Integer available_seat_count) {
        this.available_seat_count = available_seat_count;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public Integer getAvailable_seat_count() {
        return available_seat_count;
    }

    public String getDep_time() {
        return dep_time;
    }
}
