package com.mayanktechnologies.alarmmanager;


public class slots {

    String slot_title;
    int[] slot_days;
    int[][] slot_start_time;
    int[][] slot_end_time;

    public slots(String slot_title, int[] slot_days, int[][] slot_start_time, int[][] slot_end_time) {

        this.slot_title = slot_title;
        this.slot_days = slot_days;
        this.slot_start_time = slot_start_time;
        this.slot_end_time = slot_end_time;
    }

    public String getSlot_title() {
        return slot_title;
    }

    public void setSlot_title(String slot_title) {
        this.slot_title = slot_title;
    }

    public int[] getSlot_days() {
        return slot_days;
    }

    public void setSlot_days(int[] slot_days) {
        this.slot_days = slot_days;
    }

    public int[][] getSlot_start_time() {
        return slot_start_time;
    }

    public void setSlot_start_time(int[][] slot_start_time) {
        this.slot_start_time = slot_start_time;
    }

    public int[][] getSlot_end_time() {
        return slot_end_time;
    }

    public void setSlot_end_time(int[][] slot_end_time) {
        this.slot_end_time = slot_end_time;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        slots slot = (slots) obj;
        if (this.getSlot_title().equals(slot.getSlot_title())) {
            return true;
        } else {
            return false;
        }
    }

}
