package com.es.core.service;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class JdbcPhoneService implements PhoneService{
    @Resource
    private PhoneDao phoneDao;

    @Override
    public Phone getById(Long id) {
        Optional<Phone> phone = Optional.ofNullable(phoneDao.getById(id));
        if(phone.isPresent()){
            return phone.get();
        }else{
            return null;
        }
    }
}