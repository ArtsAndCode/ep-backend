package com.artsandcodes.eventpadi.service;

import com.swifta.omnibranches.dto.SmsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by prestige on 1/23/2018.
 */

@Service
public class SmsService {

    @Value("${sms.username}")
    private String username;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.baseUrl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(SmsService.class);

    /**
     *
     * @param mobiles
     * @param message
     * @param sender
     */
    public void sendAgentMobileConfirmCode(String mobiles, String message, String sender ){

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        MediaType[] mediaTypes = new MediaType[]{MediaType.ALL};
        converter.setSupportedMediaTypes(Arrays.asList(mediaTypes));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        String url = String.format("%s?username=%s&password=%s&message=%s&sender=%s&mobiles=%s",
                this.baseUrl,this.username,this.password,message,sender,mobiles);


        log.info(url);

        SmsDto smsDto = this.restTemplate.getForObject(url,SmsDto.class);

        log.info(smsDto.toString());
    }
}
