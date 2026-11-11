package org.example;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PodDiscoveryService {

    public List<String> getPodIps(){

        List<String> ips = new ArrayList<>();

        InetAddress[] addresses = null;
        try {
            addresses = InetAddress.getAllByName(
                    "test-app-headless.default.svc.cluster.local");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        for (InetAddress address : addresses) {

            ips.add(address.getHostAddress());

            System.out.println(
                    "Discovered Pod IP : "
                            + address.getHostAddress());
        }

        return ips;
    }
}
