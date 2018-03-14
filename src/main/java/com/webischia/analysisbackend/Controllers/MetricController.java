package com.webischia.analysisbackend.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Controller
@RequestMapping({"/api/v1/metric/", "/api/v1/metric"})
public class MetricController {

    private RestTemplate restTemplate;

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/{rq}")
    public ResponseEntity<String> getById(@PathVariable String rq) throws IOException {
        String url = "http://192.168.122.160:9090/api/v1/query?query=" + rq;
        System.out.println(url);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        URL url2 = new URL(url);
        HttpURLConnection request = (HttpURLConnection) url2.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        br.close();

        //String json = restTemplate.getForObject(url,String.class);
        System.out.println(sb.toString());
        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
        //return new ResponseEntity<String>("TEST " + rq, HttpStatus.OK);
    }
}
