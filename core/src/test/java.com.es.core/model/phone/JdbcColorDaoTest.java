package model.phone;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.ColorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JdbcColorDaoTest {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ColorDao colorDao;

    @Test
    public void getColorById(){
        Long key = new Long(1000);
        Set<Color> colors = colorDao.get(key);
        assertNotNull(colors);
    }

    @Test
    public void getAllColors(){
        assertNotNull(colorDao.findAll());
    }

    @Test
    public void saveNewColor(){
        int countColorsBeforeInsert = getCountColors();
        colorDao.save(newColor());
        int countColorsAfterInset = getCountColors();
        assertTrue(countColorsAfterInset == countColorsBeforeInsert + 1);
    }

    private int getCountColors(){
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject("select count(*) from colors", Integer.class);
    }

    private Color newColor(){
        Color color = new Color();
        color.setCode("21w");
        return color;
    }
}
