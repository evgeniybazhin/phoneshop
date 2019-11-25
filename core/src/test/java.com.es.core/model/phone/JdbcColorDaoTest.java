package model.phone;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.ColorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JdbcColorDaoTest {
    @Autowired
    private ColorDao colorDao;

    @Test
    public void getColorById(){
        Long key = new Long(1000);
        Set<Color> colors = colorDao.get(key);
        assertNotNull(colors);
    }
}
