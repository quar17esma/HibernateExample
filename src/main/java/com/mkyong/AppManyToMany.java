package com.mkyong;

import com.mkyong.model.stock.Category;
import com.mkyong.model.stock.Stock;
import com.mkyong.model.stock.StockDailyRecord;
import com.mkyong.model.stock.StockDetail;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AppManyToMany {
    public static void main(String[] args) {

        System.out.println("Hibernate many to many (Annotation)");

        HibernateUtil.createSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Stock stock = new Stock("7052", "PADINI");

        StockDetail stockDetail = new StockDetail(stock,"PADINI Holding Malaysia",
                "one stop shopping", "vinci vinci", new Date());
        stock.setStockDetail(stockDetail);
        stockDetail.setStock(stock);

        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");
        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);
        stock.setCategories(categories);

        session.save(stock);

        StockDailyRecord stockDailyRecords = new StockDailyRecord(stock, new Date());
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);

        session.getTransaction().commit();

        System.out.println("Done");
    }

    private static void testTransaction(Session session) {

    }
}
