package model.phone;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JdbcPhoneDaoTest {
    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void getPhoneByKey(){
        Long key = new Long(1000);
        Optional<Phone> phone = phoneDao.get(key);
        assertNotNull(phone);
    }

    @Test
    public void findAllPhones(){
        int offset = 0;
        int limit = 10;
        List<Phone> phones = phoneDao.findAll(offset, limit);
        assertNotNull(phones);
    }
}
