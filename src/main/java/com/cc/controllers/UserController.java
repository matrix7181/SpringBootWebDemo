package com.cc.controllers;

import com.cc.models.User;
import com.cc.service.ApiUserRequestService;
import com.cc.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

    private ApiUserRequestService apiUserRequestService;

    @Autowired
    public UserController(ApiUserRequestService requestService) {
        this.apiUserRequestService = requestService;
    }

    @RequestMapping(value = {
            "/api/v{[1-0]+}/users"
    }, method = OPTIONS)
    public ResponseEntity options() {
        HttpHeaders allowHeaders = HttpUtil.generateAllowHeader(HttpMethod.OPTIONS,
                HttpMethod.GET,
                HttpMethod.POST);
        return new ResponseEntity<>(allowHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = {
            "/api/v{[1-0]+}/users"
    }, method = GET)
    public Collection<User> getUsers() {
        return apiUserRequestService.fetch(0, -1);
    }

    @RequestMapping(value = {
            "/api/v{[1-0]+}/users"
    }, method = POST, consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User payload) {
        // todo: data validation
        if(apiUserRequestService.create(payload)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}