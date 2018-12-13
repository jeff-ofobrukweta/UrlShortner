package com.interswitch.microservice_test.Controller;
import com.google.common.hash.Hashing;
import com.interswitch.microservice_test.Controller.Res.ResponseApi;
import com.interswitch.microservice_test.Controller.Res.UrlShortRes;
import com.interswitch.microservice_test.Controller.Res.Urlshortner;
import com.interswitch.microservice_test.Model.UserModel;
import com.interswitch.microservice_test.UserModelRepository.UserModelRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseApi getUrl(@PathVariable String id){
        String Originalurl =  redisTemplate.opsForValue().get(id);
        ResponseApi response = new Urlshortner("00","Successful",null, Originalurl);
        return  response;
    }
    @RequestMapping(value = "/create/new",method = RequestMethod.POST)
    public String createShortUrl(@RequestBody UserModel url) throws URISyntaxException {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});
        if(urlValidator.isValid(url.getUrl())){
            String HashCodeIdentifier = Hashing.murmur3_32().hashString(url.getUrl(), StandardCharsets.UTF_8).toString();
            String collectNewUrl = UrlShortRes.getUrlDomain(url.getUrl())+"-"+HashCodeIdentifier;
            ResponseApi response = new Urlshortner("00","Successful",null, collectNewUrl);
            redisTemplate.opsForValue().set(collectNewUrl,url.getUrl());
            return (((Urlshortner) response).getUrl()); }
        throw new RuntimeException("url Invalid" + url.getUrl());
    }
    @RequestMapping(value="/testGeneratedUrl",method = RequestMethod.GET)
    public void testGeneratedUrl(@RequestBody UserModel url, HttpServletResponse httpResponse) throws Exception {
        if(url.getUrl() == null) { httpResponse.sendRedirect("/"); }
        else{ httpResponse.sendRedirect("/all"); }
    }

}
