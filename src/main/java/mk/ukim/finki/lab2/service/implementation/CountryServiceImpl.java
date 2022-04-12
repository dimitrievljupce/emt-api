package mk.ukim.finki.lab2.service.implementation;

import mk.ukim.finki.lab2.model.Country;
import mk.ukim.finki.lab2.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.lab2.repository.CountryRepository;
import mk.ukim.finki.lab2.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country create(Country country) {
        return this.countryRepository.save(country);
    }
}
