package hu.unideb.inf.prtpk.moneyBox.utility;

import org.junit.Test;

public class TestEntityManagerFactoryUtil {

    @Test
    public void testClose(){
        EntityManagerFactoryUtil.getInstance().close();
    }
}
