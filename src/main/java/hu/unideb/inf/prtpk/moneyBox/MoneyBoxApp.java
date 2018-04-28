package hu.unideb.inf.prtpk.moneyBox;

import hu.unideb.inf.prtpk.moneyBox.dao.ProductDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;

public class MoneyBoxApp {
    public static void main(String[] args){
        Product huaweiW1 = new Product(
                "Huawei Watch",
                "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-fekete-borszijjal-p408375",
                114990
        );
        ProductDAO dao = new ProductDAOImpl();

        dao.persist(huaweiW1);
    }
}
