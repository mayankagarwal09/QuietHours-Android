package com.mayanktechnologies.alarmmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {

    ArrayList<slots> slotlist;
    final private ListViewItemClick onClickListener;

    public RecyclerViewAdapter(ArrayList<slots> slot, ListViewItemClick listViewItemClick){
        slotlist=slot;
        onClickListener=listViewItemClick;
    }

    public interface ListViewItemClick{
        void onListItemClick(int index);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_row,parent,false);
        ListViewHolder listViewHolder=new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ListViewHolder holder, int position) {
        slots slot=slotlist.get(position);
        String slot_title=slot.getSlot_title();
        holder.listItemTextView.setText(slot_title);
        if(slot_title.startsWith("L"))
        {
            holder.slotImageView.setImageResource(R.drawable.lab);
        }
        else {
            holder.slotImageView.setImageResource(R.drawable.theory);
        }
        int day=slot.getSlot_days()[0];
       holder.slotDay1Textview.setText(Constants.daysName[day-1]);
        String startHour=String.format("%02d",(slot.getSlot_start_time()[0][0]));
        String startMin=String.format("%02d",(slot.getSlot_start_time()[0][1]));
        String endHour=String.format("%02d",(slot.getSlot_end_time()[0][0]));
        String endMin=String.format("%02d",(slot.getSlot_end_time()[0][1]));
        holder.slotDay1TimeTextview.setText(startHour+":"+startMin+" - "+endHour+":"+endMin);

        if(slot.slot_days.length==2){
            Log.v("cardview","length=2 "+slot.getSlot_title());
            holder.slot2LinearLayout.setVisibility(View.VISIBLE);
            int day2=slot.getSlot_days()[1];
            holder.slotDay2Textview.setText(Constants.daysName[day2-1]);
            String startHour2=String.format("%02d",(slot.getSlot_start_time()[1][0]));
            String startMin2=String.format("%02d",(slot.getSlot_start_time()[1][1]));
            String endHour2=String.format("%02d",(slot.getSlot_end_time()[1][0]));
            String endMin2=String.format("%02d",(slot.getSlot_end_time()[1][1]));
            holder.slotDay2TimeTextview.setText(startHour2+":"+startMin2+" - "+endHour2+":"+endMin2);
        }else {
            holder.slot2LinearLayout.setVisibility(View.INVISIBLE);
            //holder.slot2LinearLayout.setEnabled(false);

        }
    }

    @Override
    public int getItemCount() {
        return slotlist.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView listItemTextView,slotDay1Textview,slotDay1TimeTextview,slotDay2Textview,slotDay2TimeTextview;;
        ImageView slotImageView;
        LinearLayout slot2LinearLayout;

        public ListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listItemTextView = (TextView) itemView.findViewById(R.id.slot_title);
            slotImageView=(ImageView)itemView.findViewById(R.id.slot_imageview);
            slotDay1Textview=(TextView)itemView.findViewById(R.id.slot_day1_title);
            slotDay1TimeTextview=(TextView)itemView.findViewById(R.id.slot_day1_time);
            slotDay2Textview=(TextView)itemView.findViewById(R.id.slot_day2_title);
            slotDay2TimeTextview=(TextView)itemView.findViewById(R.id.slot_day2_time);
            slot2LinearLayout=(LinearLayout)itemView.findViewById(R.id.slot2_linear_layout);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            onClickListener.onListItemClick(position);
        }
    }
}
