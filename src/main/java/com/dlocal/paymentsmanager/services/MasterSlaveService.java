package com.dlocal.paymentsmanager.services;

import org.springframework.stereotype.Service;

@Service
public class MasterSlaveService {

    private static boolean isMaster = false;

    public static boolean isMaster() {
        return isMaster;
    }

    public static void setMaster(boolean master) {
        isMaster = master;
    }
}
