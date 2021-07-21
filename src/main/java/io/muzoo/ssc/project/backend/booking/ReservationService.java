package io.muzoo.ssc.project.backend.booking;

import io.muzoo.ssc.project.backend.Book;
import io.muzoo.ssc.project.backend.BookRepository;
import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ReservationService {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/allReservations")
    public List<Book> getAllReservations() {
        return bookRepository.findAll();
    }

    @PostMapping("/api/currentUserReservations")
    public List<Book> getCurrentUserReservationDetails(String username) {
        return bookRepository.findByUsername(username);
    }

    @PostMapping("/api/deleteReservation")
    public SimpleResponseDTO deleteReservation(Time startTime, Time endTime, Date date) {
        bookRepository.deleteByDateAndStartTimeAndEndTime(date, startTime, endTime);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("Reservation deleted.")
                .build();
    }

    @PostMapping("/api/book")
    public SimpleResponseDTO reserveSchedule(HttpServletRequest request) throws ParseException {
        String username = request.getParameter("username");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(timeFormat.parse(request.getParameter("startTime")).getTime());
        Time endTime = new Time(timeFormat.parse(request.getParameter("endTime")).getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(request.getParameter("date")).getTime());
        if (isReservable(startTime, endTime, date)) {
            Book reservation = new Book();
            reservation.setDate(date);
            reservation.setUsername(username);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            bookRepository.save(reservation);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Reserved.")
                    .build();
        }
        return SimpleResponseDTO
                .builder()
                .success(false)
                .message("Reservation overlaps with existing current reservations")
                .build();
    }

    public boolean isReservable(Time startTime, Time endTime, Date date) {
        /*
        Two schedule overlapped if start of first schedule is between start and
        end of second or if start of second schedule is between start and end of first
         */
        Book overlapReservationOne = bookRepository.findFirstByDateAndStartTimeBetween(date, startTime, endTime);
        if (overlapReservationOne != null) { // can't reserve
            return false;
        }
        else {
            List<Book> reservationByDate = bookRepository.findByDate(date);
            if (reservationByDate.size() == 0) {
                return true;
            }
            else {
                for (Book eachBook : reservationByDate) {
                    if (eachBook.getStartTime().before(startTime) &&
                    eachBook.getEndTime().after(startTime)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
