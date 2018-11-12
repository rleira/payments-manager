package com.dlocal.paymentsmanager.scheduler.task;

import com.dlocal.paymentsmanager.datastore.dal.FixerIORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FixerIOTaskFactory {

    @Autowired
    FixerIORepository fixerIORepository;

    public FixerIORunnable getFixerIORunnable() {
        return new FixerIORunnable();
    }

    private class FixerIORunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("FixerIOTask runnnnn!!");
        }
    }
}
