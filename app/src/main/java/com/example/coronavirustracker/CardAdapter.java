package com.example.coronavirustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context mContext;
    private ArrayList<CardItem> mCardList;

    public CardAdapter(Context context, ArrayList<CardItem> mList) {
        mContext= context;
        mCardList = mList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.state_item,parent,false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentItem = mCardList.get(position);
        String stateName = currentItem.getmState();
        String activeCase = currentItem.getmActive();
        String confirmedCase = currentItem.getmConfirmed();
        String deceasedCase = currentItem.getmDeceased();
        String recoveredCase = currentItem.getmRecovered();

        holder.mStateText.setText(stateName);
        holder.mActiveText.setText(activeCase);
        holder.mRecoveredText.setText(recoveredCase);
        holder.mConfirmedText.setText(confirmedCase);
        holder.mDeceasedText.setText(deceasedCase);
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        public TextView mStateText;
        public TextView mActiveText;
        public TextView mConfirmedText;
        public TextView mDeceasedText;
        public TextView mRecoveredText;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            mStateText = itemView.findViewById(R.id.stateName);
            mActiveText = itemView.findViewById(R.id.active_text);
            mConfirmedText = itemView.findViewById(R.id.confirmed_text);
            mDeceasedText = itemView.findViewById(R.id.deceased_text);
            mRecoveredText = itemView.findViewById(R.id.recovered_text);
        }
    }
}
