package com.gklijs.adventofcode;

import java.util.logging.Logger;

class Timer {
    private static final Logger LOGGER = Logger.getLogger(Timer.class.getName());

    private static final long BILLION = 1_000_000_000L;
    private static final long MILLION = 1_000_000L;
    private static final long THOUSAND = 1_000L;
    private static final String SECONDS = "seconds";
    private static final String MILLI_SECONDS = "ms";
    private static final String MICRO_SECONDS = "μs";
    private static final String NANO_SECONDS = "ns";
    private static final String MSG = "process %s took %.2f %s";

    private final String process;
    private long start;

    Timer (String process){
        this.process = process;
    }

    void start(){
        start = System.nanoTime();
    }

    void stop(){
        double time = (double) System.nanoTime() - start;
        if(time > BILLION){
            LOGGER.info(() -> String.format(MSG, process, time/BILLION, SECONDS));
        }else if(time > MILLION){
            LOGGER.info(() -> String.format(MSG, process, time/MILLION, MILLI_SECONDS));
        }else if(time > THOUSAND){
            LOGGER.info(() -> String.format(MSG, process, time/THOUSAND, MICRO_SECONDS));
        }else{
            LOGGER.info(() -> String.format(MSG, process, time, NANO_SECONDS));
        }
    }
}
