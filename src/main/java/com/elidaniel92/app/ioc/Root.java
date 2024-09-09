package com.elidaniel92.app.ioc;

import java.util.Arrays;

import org.int4.dirk.api.Injector;
import org.int4.dirk.jsr330.Injectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elidaniel92.app.Bootstrap;
import com.elidaniel92.app.infra.DatabaseConnection;
import com.elidaniel92.app.product.ProductBusinessLogicSample;
import com.elidaniel92.app.product.ProductDAO;
import com.elidaniel92.app.product.ProductPrintTimerTask;
import com.elidaniel92.app.product.RandonProductTimerTask;
import com.elidaniel92.app.scheduler.Scheduler;

public class Root {
    private static Logger log = LoggerFactory.getLogger(Root.class);
    public static Injector setting() {
        // Configure dirk injector
		//  - there are different 'Injectors' classes per each style (jsr-330 being used here)
        log.info("Setting dirk injector...");
		Injector injector = Injectors.autoDiscovering();
		injector.register(Arrays.asList(
            DatabaseConnection.class,
            Scheduler.class,
            ProductDAO.class,
            ProductBusinessLogicSample.class,
            ProductPrintTimerTask.class,
            RandonProductTimerTask.class,
            Bootstrap.class
        ));
        return injector;
    }   
}
