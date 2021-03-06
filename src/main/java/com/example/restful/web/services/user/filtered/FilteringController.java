package com.example.restful.web.services.user.filtered;

import com.example.restful.web.services.user.data.User;
import com.example.restful.web.services.user.inmemory.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @Autowired
    private UserDaoService userDaoService;

    //for this filter to work, @JsonFilter should be used in User
    @GetMapping("/users/retrieve/filtered/{id}")
    public MappingJacksonValue retrieveWithFilter(@PathVariable int id) {
        User user = userDaoService.retrieveUser(id);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "role", "birthDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilterProvider", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
