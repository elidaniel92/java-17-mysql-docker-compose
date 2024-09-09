package com.elidaniel92.app.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.elidaniel92.app.product.ProductPrintTimerTask;
import com.elidaniel92.app.product.RandonProductTimerTask;


@Singleton
public class Scheduler {
    private List<Task> taskList = new ArrayList<Task>();
    private Timer timer;

    @Inject
    public Scheduler(Timer timer, ProductPrintTimerTask printProduct, RandonProductTimerTask ramdonProductTask) {
        this.timer = timer;
        this.taskList.add(printProduct);
        this.taskList.add(ramdonProductTask);
    }
    
    public void start() {		
		taskList.forEach((elem) -> {
            this.timer.schedule(elem, 0, elem.getPeriod());
        });
    }
}
