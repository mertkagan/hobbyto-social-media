package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.entities.concretes.City;

import java.util.List;

public interface CityService {

    City getCityById(Long cityId);

    List<City> getAllCities();
}
