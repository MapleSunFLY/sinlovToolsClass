package com.sinlov.toolsclass.myenum;

public enum EmergencyStateEnum {
    NULL(EmergencyInfoReportStatesEnum.NULL),
    REPORTING(EmergencyInfoReportStatesEnum.REPORTING),
    REPORT_END(EmergencyInfoReportStatesEnum.REPORT_END),
    ASSESS(EmergencyInfoReportStatesEnum.ASSESS),
    ASSESS_END(EmergencyInfoReportStatesEnum.ASSESS_END),
    DELETED(EmergencyInfoReportStatesEnum.DELETED),
    CLOSED(EmergencyInfoReportStatesEnum.CLOSED);
     
    private final EmergencyInfoReportStatesEnum value;
     
    EmergencyStateEnum(EmergencyInfoReportStatesEnum value){
        this.value = value;
    }
     
    public static EmergencyStateEnum getState(int code){
        EmergencyInfoReportStatesEnum state = EmergencyInfoReportStatesEnum.fromInt(code);
        if(state == null)return EmergencyStateEnum.NULL;
         
        for(EmergencyStateEnum se : EmergencyStateEnum.values()){
            if(se.value == state){
                return se;
            }
        }
         
        return EmergencyStateEnum.NULL;
    }
     
    public static void main(String[] args){
//        EmergencyStateEnum state = EmergencyStateEnum.getState(event.getStates()));  
        EmergencyStateEnum state = EmergencyStateEnum.getState(101);
         
        switch (state) {
        case REPORTING:
            System.out.println(state.name()); break;
        case REPORT_END:
            System.out.println(state.name()); break;
        case ASSESS:
            System.out.println(state.name()); break;
        case ASSESS_END:
            System.out.println(state.name()); break;
        case DELETED:
            System.out.println(state.name()); break;
        case CLOSED:
            System.out.println(state.name()); break;
        default:
            System.out.println(state.name());
            break;
        }
    }
}
