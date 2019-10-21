package com.mayanktechnologies.alarmmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class SelectSlotsAdapter extends RecyclerView.Adapter<SelectSlotsAdapter.SlotViewHolder> {


    private ArrayList<slots> slotlist;
    final private SelectSlotsAdapter.SlotListViewItemClick onClickListener;
    private slots slot;

    public SelectSlotsAdapter(ArrayList<slots> slot, SelectSlotsAdapter.SlotListViewItemClick listViewItemClick){
        slotlist=slot;
        onClickListener=listViewItemClick;
    }

    public interface SlotListViewItemClick{
        void onListItemClick(int index);
    }

    @Override
    public SelectSlotsAdapter.SlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.slotselect_row,parent,false);
        SelectSlotsAdapter.SlotViewHolder listViewHolder=new SelectSlotsAdapter.SlotViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(final SelectSlotsAdapter.SlotViewHolder holder, final int position) {
        slot=slotlist.get(position);
        String slot_title=slot.getSlot_title();
        holder.slot_select_title_textview.setText(slot_title);

        if(Constants.selectedSlots.contains(slot)){
            holder.slot_select_checkbox.setChecked(true);
        }
        else {
            holder.slot_select_checkbox.setChecked(false);
        }


        holder.slot_select_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=slotlist.get(position);
                CheckBox checkBox=(CheckBox)v;
                //Log.v("checkbox",String.valueOf(checkBox.isChecked()));
                if(checkBox.isChecked()){
                    Constants.selectedSlots.remove(slot);
                    if(Constants.removedSlots.contains(slot))
                        Constants.removedSlots.remove(slot);
                    Constants.selectedSlots.add(slot);

                }else {
                    Constants.removedSlots.add(slot);
                    Constants.selectedSlots.remove(slot);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return slotlist.size();
    }


    class SlotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView slot_select_title_textview;
        CheckBox slot_select_checkbox;

        public SlotViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            slot_select_title_textview=(TextView)itemView.findViewById(R.id.slot_select_title_row);
            slot_select_checkbox=(CheckBox)itemView.findViewById(R.id.slot_select_checkbox);

        }


        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
           CheckBox checkBox=(CheckBox)view.findViewById(R.id.slot_select_checkbox);

            slot=slotlist.get(position);
            if(checkBox.isChecked()){
                Constants.removedSlots.add(slot);
                Constants.selectedSlots.remove(slot);
                //Log.v("size",String.valueOf(Constants.selectedSlots.size()));
                checkBox.setChecked(false);
            }else {
                if(Constants.removedSlots.contains(slot))
                    Constants.removedSlots.remove(slot);
                Constants.selectedSlots.add(slot);

                checkBox.setChecked(true);
            }

            onClickListener.onListItemClick(position);

        }
    }
}
