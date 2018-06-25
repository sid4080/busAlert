package main;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Service
public class BusService {

    public void alertUser(LocalDate date) throws MessagingException {
        Integer seatCount = getAvailableSeats(date);
        if (seatCount < 50) {
            Properties props = System.getProperties();
            String host = "smtp.gmail.com";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", "");
            props.put("mail.smtp.password", "");
            props.put("mail.smtp.ssl", "false");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("shivi301193@gmail.com","busalert");
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSubject("book your seats", "UTF-8");
            mimeMessage.setText("Seat book karlo bro " + seatCount + " bachi h", "UTF-8");
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("siddisgupta@gmail.com"));
            Transport.send(mimeMessage);
        }

    }

    public Integer getAvailableSeats(LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();
        BusTicketResponse busTicketResponse = restTemplate.getForObject("https://busapi.mobikwik.com/api/v2/availability/5042/6727/" + date, BusTicketResponse.class);
        List<SearchResult> search_results = busTicketResponse.getData().getSearch_results();
        SearchResult searchResult = search_results.stream().filter(result -> result.getDep_time().equals("19:30")).findFirst().get();
        return searchResult.getAvailable_seat_count();
    }
}


