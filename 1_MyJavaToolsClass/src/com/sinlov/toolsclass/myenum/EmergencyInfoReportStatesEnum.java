package com.sinlov.toolsclass.myenum;

import java.util.Map;
import java.util.TreeMap;

public enum EmergencyInfoReportStatesEnum {
    NULL,
    REPORTING,
    REPORT_END,
    ASSESS,
    ASSESS_END,
    DELETED,
    CLOSED
    ;

    private static final int START_VALUE = 100;

    private static Map<Integer, EmergencyInfoReportStatesEnum> ss = new TreeMap<Integer, EmergencyInfoReportStatesEnum>();
    private int value;

    static {
        for (int i = 0; i < values().length; i++) {
            values()[i].value = START_VALUE + i;
            ss.put(values()[i].value, values()[i]);
        }
    }

    public static EmergencyInfoReportStatesEnum fromInt(int i) {
        return ss.get(i);
    }

    public int intValue() {
        return value;
    }
}
