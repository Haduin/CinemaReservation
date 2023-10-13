//package com.nussknacker.cinemareservation.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//@Configuration
//public class DateFilterChain extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            request.getParameter("dateFrom")
//            dateFormat.parse(dateStr);
//        } catch (ParseException e) {
//            try {
//                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//                dateTimeFormat.parse(dateStr);
//                response.put("message", "Data jest w formacie yyyy-MM-dd'T'HH:mm");
//            } catch (ParseException ex) {
//                response.put("message", "Nieprawidłowy format daty");
//            }
//        }
//    }
//}
