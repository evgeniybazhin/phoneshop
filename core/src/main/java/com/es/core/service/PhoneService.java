package com.es.core.service;

import com.es.core.model.phone.Phone;

import java.util.Optional;

public interface PhoneService {
    Optional<Phone> getById(Long id);
}