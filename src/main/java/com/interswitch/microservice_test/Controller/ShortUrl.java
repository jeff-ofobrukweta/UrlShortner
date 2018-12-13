package com.interswitch.microservice_test.Controller;
import com.google.common.hash.Hashing;
import com.interswitch.microservice_test.Controller.Res.UrlShortRes;
import com.interswitch.microservice_test.Model.UserModel;
import com.interswitch.microservice_test.UserModelRepository.UserModelRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
@RestController
@RequestMapping(value = "/url_v1/v1")
public class ShortUrl implements Serializable  {
    private UserModelRepository userModelRepository;
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getUrl(@PathVariable String id){
        String Originalurl =  redisTemplate.opsForValue().get(id);
        System.out.println("original-url retrieved" + Originalurl);
        return  Originalurl;
    }
    @RequestMapping(value = "/create/new",method = RequestMethod.POST)
    public String createShortUrl(@RequestBody UserModel url) throws URISyntaxException {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});
        if(urlValidator.isValid(url.getUrl())){
            String HashCodeIdentifier = Hashing.murmur3_32().hashString(url.getUrl(), StandardCharsets.UTF_8).toString();
            System.out.println("This is for the id creation " + HashCodeIdentifier);
            String collectNewUrl = UrlShortRes.getUrlDomain(url.getUrl())+HashCodeIdentifier;
            redisTemplate.opsForValue().set(collectNewUrl,url.getUrl());
            return collectNewUrl;
        }
        throw new RuntimeException("url Invalid" + url.getUrl());
    }

}
