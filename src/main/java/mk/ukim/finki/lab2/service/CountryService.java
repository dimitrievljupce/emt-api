package mk.ukim.finki.lab2.service;

import mk.ukim.finki.lab2.model.Country;

public interface CountryService {
    Country findById(Long id);

    Country create(Country country);
}
