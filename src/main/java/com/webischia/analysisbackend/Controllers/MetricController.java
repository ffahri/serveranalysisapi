package com.webischia.analysisbackend.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping({"/api/v1/metric/", "/api/v1/metric"})

public class MetricController {

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/{rq}")
    public ResponseEntity<String> getById(@PathVariable String rq) throws IOException {

        String url = "http://prometheus:9090/api/v1/query?query=" + rq;

        System.out.println(url);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        URL url2 = new URL(url);
        HttpURLConnection request = (HttpURLConnection) url2.openConnection();
        request.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        br.close();

        System.out.println(sb.toString());
        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK); //proxying prometheus todo check security XSS
        //return new ResponseEntity<String>("TEST " + rq, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")  // 404
    public class OrderNotFoundException extends RuntimeException {
        // ...
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<String> unAuthorised(HttpServletRequest request) throws Exception {
        return new ResponseEntity<String>("Hello", HttpStatus.OK); //proxying prometheus todo check security XSS
    }
}
