package com.elidaniel92.app.scheduler;

import java.util.TimerTask;

public abstract class Task extends TimerTask {
    public abstract long getPeriod();
}
