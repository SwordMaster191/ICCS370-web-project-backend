package io.muzoo.ssc.project.backend.booking;

import io.muzoo.ssc.project.backend.Book;
import io.muzoo.ssc.project.backend.BookRepository;
import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
public class BookSchedule {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findReserveByUsername(String username) {
        return bookRepository.findByUsername(username);
    }

    public SimpleResponseDTO reserveSchedule(String username, Time startTime, Time endTime, Date date) {
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
                .message("Not reservable.")
                .build();
    }

    public void deleteReservation(Time startTime, Time endTime, Date date) {
        bookRepository.deleteByDateAndStartTimeAndEndTime(date, startTime, endTime);
    }

    public List<Book> getCurrentReservations(String username) {
        return bookRepository.findByUsername(username);
    }

    public boolean isReservable(Time startTime, Time endTime, Date date) {
        /*
        Two schedule overlapped if start of first period is between start and
        end of second or if start of second period is between start and end of first
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
