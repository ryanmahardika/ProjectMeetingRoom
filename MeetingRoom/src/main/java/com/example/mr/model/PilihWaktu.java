package com.example.mr.model;

public enum PilihWaktu {
    time1("08:00"),
    time2("08:30"),
    time3("09:00"),
    time4("09:30"),
    time5("10:00"),
    time6("10:30"),
    time7("11:00"),
    time8("11:30"),
    time9("12:00"),
    time10("12:30"),
    time11("13:00"),
    time12("13:30"),
    time13("14:00"),
    time14("14:30"),
    time15("15:00"),
    time16("15:30"),
    time17("16:00"),
    time18("16:30"),
    time19("17:00");
    
    
    private final String text;

    /**
     * @param text
     */
    PilihWaktu(final String text) {
        this.text = text;
    }
        
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
