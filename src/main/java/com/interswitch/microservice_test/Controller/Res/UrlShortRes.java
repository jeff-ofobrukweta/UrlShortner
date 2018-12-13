package com.interswitch.microservice_test.Controller.Res;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlShortRes {
    public static String getUrlDomain(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        String[] domainArray = domain.split("\\.");
        if (domainArray.length == 1) {
            return domainArray[0];
        }
        else {
            return domainArray[domainArray.length - 2] + "." + domainArray[domainArray.length - 1];
        }
    }
}
