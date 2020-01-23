package model.phone;

import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JdbcStockDaoTest {
    @Autowired
    private StockDao stockDao;

    @Test
    public void getStockByKey(){
        Long key = new Long(1001);
        assertNotNull(stockDao.getCountInStock(key));
    }

    @Test
    public void updateStockTest(){
        Long phoneId = new Long(1003);
        Long quantity = new Long(10);
        stockDao.updateStock(phoneId, quantity);
        Integer reserved = stockDao.getCountInStock(phoneId).getReserved();
        assertNotNull(reserved);
    }
}
