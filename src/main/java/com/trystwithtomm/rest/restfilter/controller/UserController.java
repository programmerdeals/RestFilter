package com.trystwithtomm.rest.restfilter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.trystwithtomm.rest.restfilter.domain.User;
import com.trystwithtomm.rest.restfilter.mock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public String getUsers(@RequestParam String selector) throws JsonProcessingException {
        List<User> userList = userService.getUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userList);

        List<String> selectors = new ArrayList<>(Arrays.asList(selector.split(",")));

        String[] selectorsArr = new String[selectors.size()];

        final SimpleFilterProvider filterProvider = new SimpleFilterProvider();

        mappingJacksonValue.setFilters(filterProvider.addFilter("user",
                SimpleBeanPropertyFilter.filterOutAllExcept((new HashSet<>(Arrays
                        .asList(selectors.toArray(selectorsArr)))))));
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setFilterProvider(filterProvider);

        User user = new User();
        return mapper.writer().writeValueAsString(userList.get(0));
    }
}
