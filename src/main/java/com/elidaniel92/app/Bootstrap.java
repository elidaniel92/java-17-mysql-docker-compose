package com.elidaniel92.app;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elidaniel92.app.infra.DatabaseConnection;
import com.elidaniel92.app.scheduler.Scheduler;

@Singleton
public class Bootstrap {
    private DatabaseConnection databaseConnection;
    private Scheduler scheduler;
    private static Logger log = LoggerFactory.getLogger(Bootstrap.class);

    @Inject
    public Bootstrap(DatabaseConnection databaseConnection, Scheduler scheduler) {
        this.databaseConnection = databaseConnection;
        this.scheduler = scheduler;
    }

    public void initialize() {
        log.info("Initializing all services...");
        try {
            databaseConnection.connect();            
        } catch (SQLException e) {
            log.error("Database connection failed, finishing application", e);
            return;
        }
        scheduler.start();
    }
}
