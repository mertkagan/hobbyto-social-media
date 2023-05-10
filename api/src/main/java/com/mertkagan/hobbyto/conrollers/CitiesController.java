package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.CityService;
import com.mertkagan.hobbyto.entities.concretes.City;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cities")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@AllArgsConstructor
public class CitiesController {

    private CityService cityService;

    @GetMapping
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

}
