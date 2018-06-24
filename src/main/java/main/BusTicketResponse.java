package main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusTicketResponse {

    Schedules data;


    public Schedules getData() {
        return data;
    }

    public void setData(Schedules data) {
        this.data = data;
    }
}
