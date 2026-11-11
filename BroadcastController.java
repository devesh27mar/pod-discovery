package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BroadcastController {

    private final PodDiscoveryService podDiscoveryService;
    private final RestTemplate restTemplate;

    public BroadcastController(
            PodDiscoveryService podDiscoveryService,
            RestTemplate restTemplate) {

        this.podDiscoveryService = podDiscoveryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/broadcast")
    public List<String> broadcast() throws Exception {

        List<String> responses = new ArrayList<>();

        List<String> podIps =
                podDiscoveryService.getPodIps();

        for (String ip : podIps) {

            try {

                String url =
                        "http://" + ip+":8080/sendMessage";

                System.out.println("Url is "+url);

                String response =
                        restTemplate.getForObject(
                                url,
                                String.class);

                responses.add(response);

            } catch (Exception ex) {

                responses.add(
                        ip + " => FAILED : "
                                + ex.getMessage());
            }
        }

        return responses;
    }
}
