package main;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Service
public class BusService {

public void alertUser (LocalDate  date) throws MessagingException {
    Integer seatCount=getAvailableSeats(date);
    if (seatCount<50)
    {
        Properties properties=new Properties();
        properties.setProperty("mail.smtp.port","8588");
        Session session =Session.getDefaultInstance(properties);
        MimeMessage mimeMessage= new MimeMessage(session);
        mimeMessage.setSubject("book your seats","UTF-8");
        mimeMessage.setText("Seat book karlo bro "+seatCount+" bachi h","UTF-8");
        mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse("siddisgupta@gmail.com" ));
        Transport.send(mimeMessage);
    }

}

    public Integer getAvailableSeats(LocalDate date)
    {
        RestTemplate restTemplate= new RestTemplate();
        BusTicketResponse busTicketResponse = restTemplate.getForObject("" + date, BusTicketResponse.class);
        List<SearchResult> search_results = busTicketResponse.getData().getSearch_results();
        SearchResult searchResult = search_results.stream().filter(result -> result.getDep_time().equals("19:30")).findFirst().get();
        return searchResult.getAvailable_seat_count();
    }
}


