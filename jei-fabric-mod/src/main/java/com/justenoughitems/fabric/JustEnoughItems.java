package com.justenoughitems.fabric;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JustEnoughItems implements ModInitializer {
    public static final String MOD_ID = "justenoughitems";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Just Enough Items initialized!");
        
        // Register any common initialization here
        // Recipe indexing and data structures will be set up on client side
    }
}
