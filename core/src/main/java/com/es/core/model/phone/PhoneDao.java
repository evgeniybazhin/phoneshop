package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Phone getById(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit, String search, String sortBy);
    Integer getCount();
}
