package com.github.adityagarde.rest.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public MappingJacksonValue getSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter.filterOutAllExcept("filed1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue getListOfSomeBeans() {
        List<SomeBean> beanList = Arrays.asList(new SomeBean("value10", "value20", "value30"),
                new SomeBean("value11", "value21", "value31"));

        SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter.filterOutAllExcept("filed2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(beanList);
        mapping.setFilters(filters);

        return mapping;
    }
}
