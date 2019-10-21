package com.mayanktechnologies.alarmmanager;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.time.RadialPickerLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ListViewItemClick {

    private AlarmManager alarmManager;
    private PendingIntent alarmsilentIntent, alarmringIntent;
    private LinearLayout not_granted_layout,entered_slot_layout;

    Button selectSlotsButton, customTimeButton;
    private RecyclerView slot_recycler_view;
    RecyclerViewAdapter listAdapter;
    private ArrayList<PendingIntent> pendingIntentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("density",String.valueOf(getResources().getDisplayMetrics().density));

        Constants.createSlots();

        not_granted_layout = (LinearLayout) findViewById(R.id.not_granted_layout);
        entered_slot_layout = (LinearLayout) findViewById(R.id.entered_slot_layout);
        slot_recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        selectSlotsButton = (Button) findViewById(R.id.select_slots_button);
        customTimeButton=(Button) findViewById(R.id.btn_custom_time);
        checkPermission();
        Constants.SharedPreferenceLoad(getApplicationContext());


        customTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customTime();
            }
        });


        selectSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                //Inflate the view from a predefined XML layout
//                View layout = inflater.inflate(R.layout.activity_slot_select,(ViewGroup)findViewById(R.id.popup_element));
//
//                PopupWindow window=new PopupWindow(layout,400,480,true);
//                window.setContentView(layout);

                Intent intent = new Intent(getApplicationContext(), SlotSelectActivity.class);
                startActivityForResult(intent, Constants.ACTIVITY_REQUEST_CODE);
            }
        });


        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        listAdapter = new RecyclerViewAdapter(Constants.selectedSlots, this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        slot_recycler_view.setLayoutManager(manager);
        slot_recycler_view.setAdapter(listAdapter);


        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int pos = viewHolder.getAdapterPosition();
                removeSlot(pos);
                Constants.selectedSlots.remove(pos);
                listAdapter.notifyItemRemoved(pos);
            }
        });


        touchHelper.attachToRecyclerView(slot_recycler_view);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SERVICE_STATE) {
            Constants.removeAlarm(getApplicationContext());
            for(int i=0;i<Constants.removedSlots.size();i++){
                slots slot=Constants.removedSlots.get(i);
                Constants.selectedSlots.remove(slot);
            }
            Constants.removedSlots.clear();

            Constants.setAlarm(getApplicationContext());
        }
        else {
            for(int i=0;i<Constants.removedSlots.size();i++){
                slots slot=Constants.removedSlots.get(i);
                Constants.selectedSlots.remove(slot);
            }
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        check_permission();
    }

    public void removeSlot(int pos) {

        slots slot = Constants.selectedSlots.get(pos);
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

            Intent silentintent = new Intent(getApplicationContext(), AlarmReceiver.class);
            silentintent.setAction(Constants.ACTION_SILENT);
            Random random = new Random();
            int silentRequestCode = 0;
            try {

                silentRequestCode = Constants.requestCodesMap.get(slot.getSlot_title() + Constants.daysName[day - 1])[0];
            } catch (Exception e) {
                Log.v("exception", e.toString());
            }

            alarmsilentIntent = PendingIntent.getBroadcast(getApplicationContext(), silentRequestCode, silentintent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(alarmsilentIntent);

            int endhour = slot.getSlot_end_time()[j][0];
            int endmin = slot.getSlot_end_time()[j][1];
            Calendar endcalendar = Calendar.getInstance();
            endcalendar.set(Calendar.DAY_OF_WEEK, day);
            endcalendar.set(Calendar.HOUR_OF_DAY, endhour);
            endcalendar.set(Calendar.MINUTE, endmin);
            endcalendar.set(Calendar.SECOND, 0);

            Log.v("Calendar", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));

            Intent ringintent = new Intent(getApplicationContext(), AlarmReceiver.class);
            ringintent.setAction(Constants.ACTION_RING);
            int ringRequestCode = 0;
            try {

                ringRequestCode = Constants.requestCodesMap.get(slot.getSlot_title() + Constants.daysName[day - 1])[1];
            } catch (Exception e) {
                Log.v("exception", e.toString());
            }
            alarmringIntent = PendingIntent.getBroadcast(getApplicationContext(), ringRequestCode, ringintent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(alarmringIntent);
        }

    }

    public void check_permission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (!manager.isNotificationPolicyAccessGranted()) {
                Log.v("permission", "denied");

                disable_views();

                Button grant_button = (Button) findViewById(R.id.grant_button);
                grant_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), "Please allow this application to access DND to change Ringer Modes", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                        startActivity(intent);
                    }
                });


            } else {
                enable_views();
                Log.v("permission", "granted");
            }
        }
    }

    public void disable_views() {
        not_granted_layout.setEnabled(true);
        not_granted_layout.setVisibility(View.VISIBLE);
        entered_slot_layout.setEnabled(false);
        entered_slot_layout.setVisibility(View.INVISIBLE);
        selectSlotsButton.setEnabled(false);
        selectSlotsButton.setVisibility(View.INVISIBLE);
        slot_recycler_view.setEnabled(false);
        slot_recycler_view.setVisibility(View.INVISIBLE);
    }

    public void enable_views() {
        not_granted_layout.setEnabled(false);
        not_granted_layout.setVisibility(View.INVISIBLE);
        entered_slot_layout.setEnabled(true);
        entered_slot_layout.setVisibility(View.VISIBLE);
        selectSlotsButton.setEnabled(true);
        selectSlotsButton.setVisibility(View.VISIBLE);
        slot_recycler_view.setEnabled(true);
        slot_recycler_view.setVisibility(View.VISIBLE);
    }


    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (!manager.isNotificationPolicyAccessGranted()) {
                Log.v("permission", "denied");

                Toast.makeText(getApplicationContext(), "Please allow this application to access DND to change Ringer Modes", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);

            } else {
                not_granted_layout.setEnabled(false);
                not_granted_layout.setVisibility(View.INVISIBLE);
                Log.v("permission", "granted");
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (permissions[0]) {
            case Manifest.permission.ACCESS_NOTIFICATION_POLICY: {
                // If request is cancelled, the result arrays are empty.
                Log.v("permission", "permission_granted");
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    not_granted_layout.setEnabled(false);
                    not_granted_layout.setVisibility(View.INVISIBLE);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.v("permission", "permission_denied");

                    not_granted_layout.setEnabled(true);
                    not_granted_layout.setVisibility(View.VISIBLE);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            private Paint p = new Paint();

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    ;
                } else {
                    ;
                }
            }


            public void addSlots() {
                if (Constants.SERVICE_STATE == true) {
                    for (int i = 0; i < Constants.selectedSlots.size(); i++) {
                        slots slot = Constants.selectedSlots.get(i);
                        addalarm(slot);
                    }
                }
            }


            private void addalarm(slots slot) {

                int[] codes = new int[2];
                int[] days = slot.slot_days;
                for (int j = 0; j < days.length; j++) {
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
                    Log.v("Calendar", String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())));

                    Intent silentintent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    silentintent.setAction(Constants.ACTION_SILENT);
                    //silentintent.putExtra("start",i);
                    Random random = new Random();
                    int silentRequestCode = random.nextInt();
                    codes[0] = silentRequestCode;
                    //Constants.pendingIntentRequestCodes.add(silentRequestCode);
                    alarmsilentIntent = PendingIntent.getBroadcast(getApplicationContext(), silentRequestCode, silentintent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startcalendar.getTimeInMillis(), 7 * AlarmManager.INTERVAL_DAY, alarmsilentIntent);
                    //pendingIntentList.add(alarmsilentIntent);

                    int endhour = slot.getSlot_end_time()[j][0];
                    int endmin = slot.getSlot_end_time()[j][1];
                    Calendar endcalendar = Calendar.getInstance();
                    endcalendar.set(Calendar.DAY_OF_WEEK, day);
                    endcalendar.set(Calendar.HOUR_OF_DAY, endhour);
                    endcalendar.set(Calendar.MINUTE, endmin);
                    endcalendar.set(Calendar.SECOND, 0);

                    Log.v("Calendar", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));

                    Intent ringintent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    ringintent.setAction(Constants.ACTION_RING);
                    //ringintent.putExtra("end",i);
                    int ringRequestCode = random.nextInt();
                    codes[1] = ringRequestCode;
                    //Constants.pendingIntentRequestCodes.add(ringRequestCode);
                    alarmringIntent = PendingIntent.getBroadcast(getApplicationContext(), ringRequestCode, ringintent, 0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, endcalendar.getTimeInMillis(), 7 * AlarmManager.INTERVAL_DAY, alarmringIntent);

                    Constants.requestCodesMap.put(slot.getSlot_title() + Constants.daysName[day - 1], codes);
                }


            }
        };
    }


    @Override
    protected void onStop() {
        super.onStop();
        Constants.saveSharedPreference(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onListItemClick(int index) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuselected = item.getItemId();
        if (menuselected == R.id.settings_menu) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        /*
        else if (menuselected == R.id.about_menu) {
            Intent intent = new Intent(this, About_Activity.class);
            startActivity(intent);
        }
        */
        return super.onOptionsItemSelected(item);
    }

    public void customTime(){
        int mHour,mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);


        com.borax12.materialdaterangepicker.time.TimePickerDialog.OnTimeSetListener listener=new com.borax12.materialdaterangepicker.time.TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                Log.d("timeSet","start- "+hourOfDay+":"+minute+" end-"+hourOfDayEnd+":"+minuteEnd);
                slots slot = new slots("Custom", new int[]{(Calendar.DAY_OF_WEEK - 2) % 7}, new int[][]{{hourOfDay, minute}}, new int[][]{{hourOfDayEnd, minuteEnd}});
                Constants.selectedSlots.add(slot);
                //listAdapter.slotlist.add(slot);
                listAdapter.notifyDataSetChanged();
                setCustomAlarm(hourOfDay,minute,hourOfDayEnd,minuteEnd);
            }
        };

        com.borax12.materialdaterangepicker.time.TimePickerDialog timePickerDialog = com.borax12.materialdaterangepicker.time.TimePickerDialog.newInstance(listener, mHour, mMinute, false);



        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
    }

    public void setCustomAlarm(int starthour,int startmin,int endhour,int endmin){
        Calendar startcalendar = Calendar.getInstance();
        startcalendar.set(Calendar.HOUR_OF_DAY, starthour);
        startcalendar.set(Calendar.MINUTE, startmin);
        startcalendar.set(Calendar.SECOND, 0);

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Log.v("Calendar", String.valueOf(dateFormat.format(startcalendar.getTimeInMillis())));

        Intent silentintent = new Intent(getApplicationContext(), AlarmReceiver.class);
        silentintent.setAction(Constants.ACTION_SILENT);
        //silentintent.putExtra("start",i);
        Random random = new Random();
        int silentRequestCode = random.nextInt();
        //Constants.pendingIntentRequestCodes.add(silentRequestCode);
        alarmsilentIntent = PendingIntent.getBroadcast(getApplicationContext(), silentRequestCode, silentintent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startcalendar.getTimeInMillis(), alarmsilentIntent);
        //pendingIntentList.add(alarmsilentIntent);

        Calendar endcalendar = Calendar.getInstance();
        endcalendar.set(Calendar.HOUR_OF_DAY, endhour);
        endcalendar.set(Calendar.MINUTE, endmin);
        endcalendar.set(Calendar.SECOND, 0);


        if(endcalendar.getTimeInMillis()<startcalendar.getTimeInMillis())
            endcalendar.set(Calendar.DAY_OF_MONTH,startcalendar.get(Calendar.DAY_OF_MONTH)+1);

        Log.v("Calendar", String.valueOf(dateFormat.format(endcalendar.getTimeInMillis())));

        Intent ringintent = new Intent(getApplicationContext(), AlarmReceiver.class);
        ringintent.setAction(Constants.ACTION_RING);
        //ringintent.putExtra("end",i);
        int ringRequestCode = random.nextInt();
        //Constants.pendingIntentRequestCodes.add(ringRequestCode);
        alarmringIntent = PendingIntent.getBroadcast(getApplicationContext(), ringRequestCode, ringintent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, endcalendar.getTimeInMillis(), alarmringIntent);
    }


}

