package main;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusService {

public void alertUser (LocalDate  date)
{
    Integer seatCount=getAvailableSeats(date);
    if (seatCount<50)
    {
        System.out.println("book your seats");
    }

}

    public Integer getAvailableSeats(LocalDate date)
    {
        RestTemplate restTemplate= new RestTemplate();
        BusTicketResponse busTicketResponse= restTemplate.getForObject("https://busapi.mobikwik.com/api/v2/availability/5042/6727/"+date,BusTicketResponse.class);
        List<SearchResult> search_results = busTicketResponse.getData().getSearch_results();
        SearchResult searchResult = search_results.stream().filter(result -> result.getDep_time().equals("19:30")).findFirst().get();
        return searchResult.getAvailable_seat_count();
    }
}


