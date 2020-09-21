package id.giansar.demo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class HelperService {
    public String generateRandomString32() {
        return RandomStringUtils.random(32, true, true);
    }
}
