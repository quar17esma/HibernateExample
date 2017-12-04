package com.mkyong.stock;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = "STOCK_NAME"),
        @UniqueConstraint(columnNames = "STOCK_CODE") })
public class Stock implements Serializable {

    private Integer stockId;
    private String stockCode;
    private String stockName;
    private StockDetail stockDetail;
    private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>(0);

    public Stock() {
    }

    public Stock(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public Stock(String stockCode, String stockName, StockDetail stockDetail) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.stockDetail = stockDetail;
    }

    public Stock(String stockCode, String stockName, StockDetail stockDetail,
                 Set<StockDailyRecord> stockDailyRecords) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.stockDetail = stockDetail;
        this.stockDailyRecords = stockDailyRecords;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "STOCK_ID", unique = true, nullable = false)
    public Integer getStockId() {
        return this.stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    @Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
    public String getStockCode() {
        return this.stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
    public String getStockName() {
        return this.stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
    public StockDetail getStockDetail() {
        return this.stockDetail;
    }

    public void setStockDetail(StockDetail stockDetail) {
        this.stockDetail = stockDetail;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<StockDailyRecord> getStockDailyRecords() {
        return this.stockDailyRecords;
    }

    public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
        this.stockDailyRecords = stockDailyRecords;
    }

}

