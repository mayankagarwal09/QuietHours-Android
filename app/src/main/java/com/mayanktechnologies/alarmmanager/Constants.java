package com.mayanktechnologies.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;



public class Constants {

    static ArrayList<slots> slotsArrayList;
    public static ArrayList<slots> selectedSlots=new ArrayList<>();
    public static ArrayList<slots> removedSlots=new ArrayList<>();
    public static HashMap<String,int[]> requestCodesMap=new HashMap<>();
    public static final int REQUEST_CODE=101;
    public static final int ACTIVITY_REQUEST_CODE=11;
    public static final int NOTIFICATION_REQUEST_CODE=102;
    public static final String ACTION_RING="action_ring";
    public static final String ACTION_SILENT="action_silent";
    public static final String ACTION_NOTIFICATION_SHOW="action_notification_show";
    public static int NUMBER_OF_SLOTS_SELECTED=0;
    public static final String SHARED_PREF="Slot_pref";
    public static final String SELECTED_LIST="selected_list";
    public static final String PENDING_LIST="pending_list";
    public static final String SERVICE_STATE_ID="service_state";
    public static final String CONTINOUS_STATE_ID="continous_state";
    public static final int SET_ALARM_ID=99;
    public static final int SHARED_PREF_ID=100;
    public static int CURRENT_MEDIA_VOLUME=100;
    public static boolean SERVICE_STATE=false;
    public static boolean PHONE_RING_STATE=true;
    public static boolean CONTINOUS_CLASS_STATE=false;
    public static boolean VIBRATE_STATE=true;
    public static int ADDITIONAL_TIME=2;
    public static final String[] daysName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static PendingIntent alarmsilentIntent,alarmringIntent,alarmNotificationIntent;
    public static AlarmManager alarmManager;

    public static void createSlots() {


        slotsArrayList=new ArrayList<>();

        slots A1 = new slots("A1", new int[]{Calendar.MONDAY, Calendar.WEDNESDAY}, new int[][]{{8, 00}, {9, 00}}, new int[][]{{8, 50}, {9, 50}});
        slotsArrayList.add(A1);
        slots B1 = new slots("B1", new int[]{Calendar.TUESDAY, Calendar.THURSDAY}, new int[][]{{8, 00}, {9, 00}}, new int[][]{{8, 50}, {9, 50}});
        slotsArrayList.add(B1);
        slots C1 = new slots("C1", new int[]{Calendar.WEDNESDAY, Calendar.FRIDAY}, new int[][]{{8, 00}, {9, 00}}, new int[][]{{8, 50}, {9, 50}});
        slotsArrayList.add(C1);
        slots D1 = new slots("D1", new int[]{Calendar.MONDAY, Calendar.THURSDAY}, new int[][]{{10, 00}, {8, 00}}, new int[][]{{10, 50}, {8, 50}});
        slotsArrayList.add(D1);
        slots E1 = new slots("E1", new int[]{Calendar.TUESDAY, Calendar.FRIDAY}, new int[][]{{10, 00}, {8, 00}}, new int[][]{{10, 50}, {8, 50}});
        slotsArrayList.add(E1);
        slots F1 = new slots("F1", new int[]{Calendar.MONDAY, Calendar.WEDNESDAY}, new int[][]{{9, 00}, {10, 00}}, new int[][]{{9, 50}, {10, 50}});
        slotsArrayList.add(F1);
        slots G1 = new slots("G1", new int[]{Calendar.TUESDAY, Calendar.THURSDAY}, new int[][]{{9, 00}, {10, 00}}, new int[][]{{9, 50}, {10, 50}});
        slotsArrayList.add(G1);

        slots TA1 = new slots("TA1", new int[]{Calendar.FRIDAY}, new int[][]{{10, 00}}, new int[][]{{10, 50}});
        slotsArrayList.add(TA1);
        slots TB1 = new slots("TB1", new int[]{Calendar.MONDAY}, new int[][]{{11, 00}}, new int[][]{{11, 50}});
        slotsArrayList.add(TB1);
        slots TC1 = new slots("TC1", new int[]{Calendar.TUESDAY}, new int[][]{{11, 00}}, new int[][]{{11, 50}});
        slotsArrayList.add(TC1);
        slots TD1 = new slots("TD1", new int[]{Calendar.FRIDAY}, new int[][]{{12, 00}}, new int[][]{{12, 50}});
        slotsArrayList.add(TD1);
        slots TE1 = new slots("TE1", new int[]{Calendar.THURSDAY}, new int[][]{{11, 00}}, new int[][]{{11, 50}});
        slotsArrayList.add(TE1);
        slots TF1 = new slots("TF1", new int[]{Calendar.FRIDAY}, new int[][]{{11, 00}}, new int[][]{{11, 50}});
        slotsArrayList.add(TF1);
        slots TG1 = new slots("TG1", new int[]{Calendar.MONDAY}, new int[][]{{12, 00}}, new int[][]{{12, 50}});
        slotsArrayList.add(TG1);

        slots TAA1 = new slots("TAA1", new int[]{Calendar.TUESDAY}, new int[][]{{12, 00}}, new int[][]{{12, 50}});
        slotsArrayList.add(TAA1);
        slots TCC1 = new slots("TCC1", new int[]{Calendar.THURSDAY}, new int[][]{{12, 00}}, new int[][]{{12, 50}});
        slotsArrayList.add(TCC1);

        slots A2 = new slots("A2", new int[]{Calendar.MONDAY, Calendar.WEDNESDAY}, new int[][]{{14, 00}, {15, 00}}, new int[][]{{14, 50}, {15, 50}});
        slotsArrayList.add(A2);
        slots B2 = new slots("B2", new int[]{Calendar.TUESDAY, Calendar.THURSDAY}, new int[][]{{14, 00}, {15, 00}}, new int[][]{{14, 50}, {15, 50}});
        slotsArrayList.add(B2);
        slots C2 = new slots("C2", new int[]{Calendar.WEDNESDAY, Calendar.FRIDAY}, new int[][]{{14, 00}, {15, 00}}, new int[][]{{14, 50}, {15, 50}});
        slotsArrayList.add(C2);
        slots D2 = new slots("D2", new int[]{Calendar.MONDAY, Calendar.THURSDAY}, new int[][]{{16, 00}, {14, 00}}, new int[][]{{16, 50}, {14, 50}});
        slotsArrayList.add(D2);
        slots E2 = new slots("E2", new int[]{Calendar.TUESDAY, Calendar.FRIDAY}, new int[][]{{16, 00}, {14, 00}}, new int[][]{{16, 50}, {14, 50}});
        slotsArrayList.add(E2);
        slots F2 = new slots("F2", new int[]{Calendar.MONDAY, Calendar.WEDNESDAY}, new int[][]{{15, 00}, {16, 00}}, new int[][]{{15, 50}, {16, 50}});
        slotsArrayList.add(F2);
        slots G2 = new slots("G2", new int[]{Calendar.TUESDAY, Calendar.THURSDAY}, new int[][]{{15, 00}, {16, 00}}, new int[][]{{15, 50}, {16, 50}});
        slotsArrayList.add(G2);

        slots TA2 = new slots("TA2", new int[]{Calendar.FRIDAY}, new int[][]{{16, 00}}, new int[][]{{16, 50}});
        slotsArrayList.add(TA2);
        slots TB2 = new slots("TB2", new int[]{Calendar.MONDAY}, new int[][]{{17, 00}}, new int[][]{{17, 50}});
        slotsArrayList.add(TB2);
        slots TC2 = new slots("TC2", new int[]{Calendar.TUESDAY}, new int[][]{{17, 00}}, new int[][]{{17, 50}});
        slotsArrayList.add(TC2);
        slots TD2 = new slots("TD2", new int[]{Calendar.FRIDAY}, new int[][]{{17, 00}}, new int[][]{{17, 50}});
        slotsArrayList.add(TD2);
        slots TE2 = new slots("TE2", new int[]{Calendar.THURSDAY}, new int[][]{{17, 00}}, new int[][]{{17, 50}});
        slotsArrayList.add(TE2);
        slots TF2 = new slots("TF2", new int[]{Calendar.FRIDAY}, new int[][]{{17, 00}}, new int[][]{{17, 50}});
        slotsArrayList.add(TF2);
        slots TG2 = new slots("TG2", new int[]{Calendar.MONDAY}, new int[][]{{18, 00}}, new int[][]{{18, 50}});
        slotsArrayList.add(TG2);

        slots TAA2 = new slots("TAA2", new int[]{Calendar.TUESDAY}, new int[][]{{18, 00}}, new int[][]{{18, 50}});
        slotsArrayList.add(TAA2);
        slots TBB2 = new slots("TBB2", new int[]{Calendar.WEDNESDAY}, new int[][]{{18, 00}}, new int[][]{{18, 50}});
        slotsArrayList.add(TBB2);
        slots TCC2 = new slots("TCC2", new int[]{Calendar.THURSDAY}, new int[][]{{18, 00}}, new int[][]{{18, 50}});
        slotsArrayList.add(TCC2);
        slots TDD2 = new slots("TDD2", new int[]{Calendar.FRIDAY}, new int[][]{{18, 00}}, new int[][]{{18, 50}});
        slotsArrayList.add(TDD2);


        slots V1 = new slots("V1", new int[]{Calendar.WEDNESDAY}, new int[][]{{11, 00}}, new int[][]{{11, 50}});
        slotsArrayList.add(V1);
        slots V2 = new slots("V2", new int[]{Calendar.WEDNESDAY}, new int[][]{{12, 00}}, new int[][]{{12, 50}});
        slotsArrayList.add(V2);
        slots V3 = new slots("V3", new int[]{Calendar.MONDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V3);
        slots V4 = new slots("V4", new int[]{Calendar.TUESDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V4);
        slots V5 = new slots("V5", new int[]{Calendar.WEDNESDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V5);
        slots V6 = new slots("V6", new int[]{Calendar.THURSDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V6);
        slots V7 = new slots("V7", new int[]{Calendar.FRIDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V7);

        slots V8 = new slots("V8", new int[]{Calendar.SATURDAY}, new int[][]{{8, 00}}, new int[][]{{8, 50}});
        slotsArrayList.add(V8);
        slots V9 = new slots("V9", new int[]{Calendar.SATURDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V9);

        slots V10 = new slots("V10", new int[]{Calendar.SUNDAY}, new int[][]{{8, 00}}, new int[][]{{8, 50}});
        slotsArrayList.add(V10);
        slots V11 = new slots("V11", new int[]{Calendar.SUNDAY}, new int[][]{{19, 00}}, new int[][]{{19, 50}});
        slotsArrayList.add(V11);

        slots W21 = new slots("W21", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{17, 00}, {17, 00}}, new int[][]{{17, 50}, {17, 50}});
        slotsArrayList.add(W21);
        slots W22 = new slots("W22", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{18, 00}, {18, 00}}, new int[][]{{18, 50}, {18, 50}});
        slotsArrayList.add(W22);


        slots X11 = new slots("X11", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{9, 00}, {11, 00}}, new int[][]{{9, 50}, {11, 50}});
        slotsArrayList.add(X11);
        slots X12 = new slots("X12", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{10, 00}, {12, 00}}, new int[][]{{10, 50}, {12, 50}});
        slotsArrayList.add(X12);
        slots X21 = new slots("X21", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{14, 00}, {16, 00}}, new int[][]{{14, 50}, {16, 50}});
        slotsArrayList.add(X21);

        slots Y11 = new slots("Y11", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{11, 00}, {9, 00}}, new int[][]{{11, 50}, {9, 50}});
        slotsArrayList.add(Y11);
        slots Y12 = new slots("Y12", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{12, 00}, {10, 00}}, new int[][]{{12, 50}, {10, 50}});
        slotsArrayList.add(Y12);
        slots Y21 = new slots("Y21", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{16, 00}, {14, 00}}, new int[][]{{16, 50}, {14, 50}});
        slotsArrayList.add(Y21);

        slots Z21 = new slots("Z21", new int[]{Calendar.SATURDAY, Calendar.SUNDAY}, new int[][]{{15, 00}, {15, 00}}, new int[][]{{15, 50}, {15, 50}});
        slotsArrayList.add(Z21);


        slots L1 = new slots("L1+L2", new int[]{Calendar.MONDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L1);
        slots L3 = new slots("L3+L4", new int[]{Calendar.MONDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L3);
        slots L5 = new slots("L5+L6", new int[]{Calendar.MONDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L5);

        slots L7 = new slots("L7+L8", new int[]{Calendar.TUESDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L7);
        slots L9 = new slots("L9+L10", new int[]{Calendar.TUESDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L9);
        slots L11 = new slots("L11+L12", new int[]{Calendar.TUESDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L11);

        slots L13 = new slots("L13+L14", new int[]{Calendar.WEDNESDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L13);
        slots L15 = new slots("L15+L16", new int[]{Calendar.WEDNESDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L15);

        slots L19 = new slots("L19+L20", new int[]{Calendar.THURSDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L19);
        slots L21 = new slots("L21+L22", new int[]{Calendar.THURSDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L21);
        slots L23 = new slots("L23+L24", new int[]{Calendar.THURSDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L23);

        slots L25 = new slots("L25+L26", new int[]{Calendar.FRIDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L25);
        slots L27 = new slots("L27+L28", new int[]{Calendar.FRIDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L27);
        slots L29 = new slots("L29+L30", new int[]{Calendar.FRIDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L29);



        slots L31 = new slots("L31+L32", new int[]{Calendar.MONDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L31);
        slots L33 = new slots("L33+L34", new int[]{Calendar.MONDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L33);
        slots L35 = new slots("L35+L36", new int[]{Calendar.MONDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L35);

        slots L37 = new slots("L37+L38", new int[]{Calendar.TUESDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L37);
        slots L39 = new slots("L39+L40", new int[]{Calendar.TUESDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L39);
        slots L41 = new slots("L41+L42", new int[]{Calendar.TUESDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L41);

        slots L43 = new slots("L43+L44", new int[]{Calendar.WEDNESDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L43);
        slots L45 = new slots("L45+L46", new int[]{Calendar.WEDNESDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L45);
        slots L47 = new slots("L47+L48", new int[]{Calendar.WEDNESDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L47);

        slots L49 = new slots("L49+L50", new int[]{Calendar.THURSDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L49);
        slots L51 = new slots("L51+L52", new int[]{Calendar.THURSDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L51);
        slots L53 = new slots("L53+L54", new int[]{Calendar.THURSDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L53);

        slots L55 = new slots("L55+L56", new int[]{Calendar.FRIDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L55);
        slots L57 = new slots("L57+L58", new int[]{Calendar.FRIDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L57);
        slots L59 = new slots("L59+L60", new int[]{Calendar.FRIDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L59);

        slots L71 = new slots("L71+L72", new int[]{Calendar.SATURDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L71);
        slots L73 = new slots("L73+L74", new int[]{Calendar.SATURDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L73);
        slots L75 = new slots("L75+L76", new int[]{Calendar.SATURDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L75);

        slots L77 = new slots("L77+L78", new int[]{Calendar.SATURDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L77);
        slots L79 = new slots("L79+L80", new int[]{Calendar.SATURDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L79);
        slots L81 = new slots("L81+L82", new int[]{Calendar.SATURDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L81);

        slots L83 = new slots("L83+L84", new int[]{Calendar.SUNDAY}, new int[][]{{8, 00}}, new int[][]{{9, 30}});
        slotsArrayList.add(L83);
        slots L85 = new slots("L85+L86", new int[]{Calendar.SUNDAY}, new int[][]{{10, 00}}, new int[][]{{11, 30}});
        slotsArrayList.add(L85);
        slots L87 = new slots("L87+L88", new int[]{Calendar.SUNDAY}, new int[][]{{11, 30}}, new int[][]{{13, 00}});
        slotsArrayList.add(L87);

        slots L89 = new slots("L89+L90", new int[]{Calendar.SUNDAY}, new int[][]{{14, 00}}, new int[][]{{15, 30}});
        slotsArrayList.add(L89);
        slots L91 = new slots("L91+L92", new int[]{Calendar.SUNDAY}, new int[][]{{16, 00}}, new int[][]{{17, 30}});
        slotsArrayList.add(L91);
        slots L93 = new slots("L93+L94", new int[]{Calendar.SUNDAY}, new int[][]{{17, 30}}, new int[][]{{19, 00}});
        slotsArrayList.add(L93);


    }

    public static void setAlarm(Context context){

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < Constants.selectedSlots.size(); i++) {

            slots slot = Constants.selectedSlots.get(i);

            if (!slot.slot_title.equals("Custom")) {
                int[] days = slot.getSlot_days();
                for (int j = 0; j < days.length; j++) {

                    int[] codes = new int[2];


                    Log.v("setAlarm", "for j entered");
                    int starthour = slot.getSlot_start_time()[j][0];
                    int startmin = slot.getSlot_start_time()[j][1];
                    int day = slot.getSlot_days()[j];
                    Calendar startcalendar = Calendar.getInstance();
                    startcalendar.set(Calendar.DAY_OF_WEEK, day);
                    startcalendar.set(Calendar.HOUR_OF_DAY, starthour);
                    startcalendar.set(Calendar.MINUTE, startmin);
                    startcalendar.set(Calendar.SECOND, 0);


                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                    Intent silentintent = new Intent(context, AlarmReceiver.class);
                    silentintent.setAction(Constants.ACTION_SILENT);
                    //silentintent.putExtra("start",i);
                    Random random = new Random();
                    int silentRequestCode = random.nextInt();

                    codes[0] = silentRequestCode;
                    Log.v("codesSet", String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())) + "  " + codes[0]);
                    // Constants.pendingIntentRequestCodes.add(silentRequestCode);

                    alarmsilentIntent = PendingIntent.getBroadcast(context, silentRequestCode, silentintent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Log.v("CalendarSetStart", String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())));
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startcalendar.getTimeInMillis(),
                            7 * AlarmManager.INTERVAL_DAY, alarmsilentIntent);
                /*
                if(startcalendar.getTimeInMillis()>System.currentTimeMillis()) {
                    Log.v("CalendarSetStart",String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())));
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startcalendar.getTimeInMillis(),
                            7 * AlarmManager.INTERVAL_DAY, alarmsilentIntent);
                }else {
                    Log.v("CalendarSetStart",String.valueOf(dateFormat.format(startcalendar.getTimeInMillis()+ TimeUnit.DAYS.toMillis(7))));
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startcalendar.getTimeInMillis()+ TimeUnit.DAYS.toMillis(7)
                            , 7 * AlarmManager.INTERVAL_DAY, alarmsilentIntent);
                }
                */

                    int endhour = slot.getSlot_end_time()[j][0];
                    int endmin = slot.getSlot_end_time()[j][1];
                    Calendar endcalendar = Calendar.getInstance();
                    endcalendar.set(Calendar.DAY_OF_WEEK, day);
                    endcalendar.set(Calendar.HOUR_OF_DAY, endhour);
                    endcalendar.set(Calendar.MINUTE, endmin);
                    endcalendar.set(Calendar.SECOND, 0);

                    int flag = 0;
                    if (Constants.CONTINOUS_CLASS_STATE == true) {
                        for (int k = 0; k < Constants.selectedSlots.size(); k++) {
                            slots slotCheck = Constants.selectedSlots.get(k);
                            int[] daysCheck = slotCheck.getSlot_days();
                            for (int l = 0; l < daysCheck.length; l++) {
                                if (slotCheck.getSlot_days()[l] == day && slotCheck.getSlot_start_time()[l][0] == starthour + 1) {
                                    flag = 1;
                                    Log.v("continous", slot.getSlot_title() + " " + daysName[slot.getSlot_days()[j] - 1] + "  " + slot.getSlot_start_time()[j][0]);
                                    break;
                                }
                            }
                            if (flag == 1)
                                break;
                        }
                    }

                    if (flag == 0) {
                        Intent ringintent = new Intent(context, AlarmReceiver.class);
                        ringintent.setAction(Constants.ACTION_RING);


                        int ringRequestCode = random.nextInt();


                        codes[1] = ringRequestCode;
                        Log.v("codesSet", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())) + "  " + codes[1]);
                        //Constants.pendingIntentRequestCodes.add(ringRequestCode);
                        alarmringIntent = PendingIntent.getBroadcast(context, ringRequestCode, ringintent, PendingIntent.FLAG_UPDATE_CURRENT);

                        Log.v("CalendarSetEnd", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, endcalendar.getTimeInMillis()
                                , 7 * AlarmManager.INTERVAL_DAY, alarmringIntent);

                    /*if (startcalendar.getTimeInMillis() > System.currentTimeMillis()) {
                        Log.v("CalendarSetEnd", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, endcalendar.getTimeInMillis()
                                , 7 * AlarmManager.INTERVAL_DAY, alarmringIntent);
                    } else {
                        Log.v("CalendarSetEnd", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(7))));
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, endcalendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(7)
                                , 7 * AlarmManager.INTERVAL_DAY, alarmringIntent);
                    }
*/
                    }
                    //Log.v("codesHashmap",slot.getSlot_title()+daysName[day-1]);
                    requestCodesMap.put(slot.getSlot_title() + daysName[day - 1], codes);
                }
            }
        }
        for(int[] k:requestCodesMap.values()){
            Log.v("codesSetmap",String.valueOf(k[0])+"  "+String.valueOf(k[1]));
        }
        //Log.v("codesFirstvalue",String.valueOf(requestCodesMap.get(requestCodesMap.keySet().toArray()[0])[0]));
    }


    public  static void removeAlarm(Context context) {
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < Constants.selectedSlots.size(); i++) {

            slots slot = Constants.selectedSlots.get(i);
            int[] days = slot.slot_days;
            for (int j = 0; j < days.length; j++) {
                int starthour = slot.getSlot_start_time()[j][0];
                int startmin = slot.getSlot_start_time()[j][1];
                int day = slot.getSlot_days()[j];
                Calendar startcalendar = Calendar.getInstance();
                startcalendar.set(Calendar.DAY_OF_WEEK, day);
                startcalendar.set(Calendar.HOUR_OF_DAY, starthour);
                startcalendar.set(Calendar.MINUTE, startmin);
                startcalendar.set(Calendar.SECOND, 0);

                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                //Log.v("Calendar", String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())));

                Intent silentintent = new Intent(context, AlarmReceiver.class);
                silentintent.setAction(Constants.ACTION_SILENT);
                int silentRequestCode=0;
                try {

                    //Log.v("codesGetMap",slot.getSlot_title()+daysName[day-1]);
                    silentRequestCode=requestCodesMap.get(slot.getSlot_title()+daysName[day-1])[0];
                    //silentRequestCode= Constants.pendingIntentRequestCodes.get(2 * j);
                    Log.v("codesremovesilent",String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())) + "  "+silentRequestCode);
                }
                catch (Exception e)
                {
                    Log.v("exception",e.toString());
                }
                alarmsilentIntent = PendingIntent.getBroadcast(context, silentRequestCode, silentintent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(alarmsilentIntent);

                int endhour = slot.getSlot_end_time()[j][0];
                int endmin = slot.getSlot_end_time()[j][1];
                Calendar endcalendar = Calendar.getInstance();
                endcalendar.set(Calendar.DAY_OF_WEEK, day);
                endcalendar.set(Calendar.HOUR_OF_DAY, endhour);
                endcalendar.set(Calendar.MINUTE, endmin);
                endcalendar.set(Calendar.SECOND, 0);
                //Log.v("Calendar", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));

                Intent ringintent = new Intent(context, AlarmReceiver.class);
                ringintent.setAction(Constants.ACTION_RING);
                int ringRequestCode=0;
                try {

                    ringRequestCode=requestCodesMap.get(slot.getSlot_title()+daysName[day-1])[1];
                    //Log.v("codesGetMap",slot.getSlot_title()+daysName[day-1]);
                    //ringRequestCode= Constants.pendingIntentRequestCodes.get(2 * j + 1);
                    Log.v("codesremovering",String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())) + "  "+ringRequestCode);
                }
                catch (Exception e)
                {
                    Log.v("exception",e.toString());
                }
                alarmringIntent = PendingIntent.getBroadcast(context, ringRequestCode, ringintent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(alarmringIntent);
            }
        }
        requestCodesMap.clear();
        //Constants.pendingIntentRequestCodes.clear();
    }

    public static void SharedPreferenceLoad(Context context) {
        SharedPreferences preferences=context.getSharedPreferences(Constants.SHARED_PREF,MODE_PRIVATE);
        Gson gson=new Gson();
        String json=preferences.getString(Constants.SELECTED_LIST,null);
        String pendingjson=preferences.getString(Constants.PENDING_LIST,null);
        Constants.SERVICE_STATE=preferences.getBoolean(Constants.SERVICE_STATE_ID,true);
        Constants.CONTINOUS_CLASS_STATE=preferences.getBoolean(Constants.CONTINOUS_STATE_ID,false);
        if(json!=null) {
            slots[] slotarray=gson.fromJson(json,slots[].class);
            Constants.selectedSlots = new ArrayList<>(Arrays.asList(slotarray));
            Constants.NUMBER_OF_SLOTS_SELECTED=Constants.selectedSlots.size();
            if(pendingjson!=null){
                Type pendingType = new TypeToken<HashMap<String,int[]>>() {}.getType();
                Constants.requestCodesMap=gson.fromJson(pendingjson,pendingType);
                Log.v("sharedPref","executed");
            }
        }

    }

    public static void saveSharedPreference(Context context){
        if (Constants.selectedSlots!=null) {
            SharedPreferences preferences= context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE) ;
            SharedPreferences.Editor editor=preferences.edit();
            Gson gson=new Gson();
            String selectedSlotjson = gson.toJson(Constants.selectedSlots);
            String pendingRequestCodesjson = gson.toJson(Constants.requestCodesMap);
            editor.putString(Constants.SELECTED_LIST, selectedSlotjson);
            editor.putString(Constants.PENDING_LIST, pendingRequestCodesjson);
            editor.putBoolean(Constants.SERVICE_STATE_ID,Constants.SERVICE_STATE);
            editor.putBoolean(Constants.CONTINOUS_STATE_ID,Constants.CONTINOUS_CLASS_STATE);
            editor.commit();
            Log.v("destroy","savedpref");
        }
    }

}
