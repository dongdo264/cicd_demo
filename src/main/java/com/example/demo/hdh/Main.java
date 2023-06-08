package com.example.demo.hdh;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
//        String inputDate = "Mar 22, 2023, 7:00:00 AM";
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a", Locale.ENGLISH);
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        String outputDate = LocalDateTime.parse(inputDate, inputFormatter)
//                .format(outputFormatter);
//
//        System.out.println(outputDate);
        String inputDateString = "Mar 22, 2023, 7:00:00 AM";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM d, yyyy, h:mm:ss a", Locale.ENGLISH);

        Date inputDate = null;
        try {
            inputDate = inputDateFormat.parse(inputDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
//        Timestamp timestamp = Timestamp.valueOf(outputDateFormat.format(inputDate));
//        System.out.println(timestamp);
        String outputDateString = outputDateFormat.format(inputDate);

        System.out.println(outputDateString);
        Date date = outputDateFormat.parse(outputDateString);

// Lấy timestamp từ đối tượng Date
        long timestamps = date.getTime();
        System.out.println(timestamps);
    }
}
