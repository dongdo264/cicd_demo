package com.example.demo.security.middleware;

import com.example.demo.entity.ProductEntity;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.util.stream.Collectors;

@Component
public class ProductInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        System.out.println("Pre Handler");
        System.out.println(request.getHeader("Authorization"));
        System.out.println(request.getMethod());
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            ObjectMapper mapper = new ObjectMapper();
//            ProductEntity p = mapper.readValue(request.getReader(), ProductEntity.class);
//            System.out.println(p);
//            response.sendError(HttpServletResponse.SC_ACCEPTED, "Error: Unauthorized");
//            return false;
//            StringBuilder sb = new StringBuilder();
//            BufferedReader reader = request.getReader();
//            try {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line).append('\n');
//                }
//            } finally {
//                reader.close();
//            }
//            System.out.println(sb.toString());
//            Gson gson = new Gson();
////            String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            ProductEntity person = gson.fromJson(sb.toString(), ProductEntity.class);
//            System.out.println(person);
//            System.out.println(person.getProductDetailEntity());
//                        response.sendError(HttpServletResponse.SC_ACCEPTED, "Error: Unauthorized");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

    }
}
