package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
