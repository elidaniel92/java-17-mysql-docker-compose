package com.elidaniel92.app.product;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elidaniel92.app.scheduler.Task;

@Singleton
public class RandonProductTimerTask extends Task {
    private ProductBusinessLogicSample productBusinessLogicSample;
    private long period;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    public RandonProductTimerTask(ProductBusinessLogicSample productPrintService) {
        this.productBusinessLogicSample = productPrintService;
        this.period = 5000;
    }

    @Override
    public void run() {
        try {
            this.productBusinessLogicSample.createProduct();
            this.productBusinessLogicSample.updateProduct();
            this.productBusinessLogicSample.deleteProduct();
        } catch (SQLException e) {
            log.error("Can not execute the task. Next execution will be in " + this.period + "ms", e);
        }
    }
    
    @Override
    public long getPeriod() {
        return this.period;
    }
}
