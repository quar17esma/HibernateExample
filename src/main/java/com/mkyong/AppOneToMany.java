package com.mkyong;

import com.mkyong.model.stock.Stock;
import com.mkyong.model.stock.StockDailyRecord;
import com.mkyong.model.stock.StockDetail;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class AppOneToMany {
    public static void main(String[] args) {

        System.out.println("Hibernate one to many (Annotation)");

        HibernateUtil.createSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();

        Stock stock = new Stock("7052", "PADINI");
//        stock.setStockCode();
//        stock.setStockName();

        StockDetail stockDetail = new StockDetail(stock,"PADINI Holding Malaysia",
                "one stop shopping", "vinci vinci", new Date());
//        stockDetail.setCompName();
//        stockDetail.setCompDesc();
//        stockDetail.setRemark();
//        stockDetail.setListedDate();

        stock.setStockDetail(stockDetail);
        stockDetail.setStock(stock);

        session.save(stock);

        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());

        stockDailyRecords.setStock(stock);
        stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);
        session.getTransaction().commit();


        System.out.println("Done");
    }
}
