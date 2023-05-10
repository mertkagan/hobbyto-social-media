package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.CityService;
import com.mertkagan.hobbyto.dataAccess.abstracts.CityRepository;
import com.mertkagan.hobbyto.entities.concretes.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityManager implements CityService {

    private CityRepository cityRepository;
    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
