package com.mkyong;

import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDetail;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class AppOneToOne {
    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");

        HibernateUtil.createSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();

        Stock stock = new Stock();

        stock.setStockCode("7052");
        stock.setStockName("PADINI");

        StockDetail stockDetail = new StockDetail();
        stockDetail.setCompName("PADINI Holding Malaysia");
        stockDetail.setCompDesc("one stop shopping");
        stockDetail.setRemark("vinci vinci");
        stockDetail.setListedDate(new Date());

        stock.setStockDetail(stockDetail);
        stockDetail.setStock(stock);

        session.save(stock);
        session.getTransaction().commit();


        System.out.println("Done");
    }
}
