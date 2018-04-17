package com.example.shruti.governmentpedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 7000 on 4/13/2017.
 */

public class CivilGovermentAdaptor extends RecyclerView.Adapter<CivilGovermentViewHolder> {
    MainActivity mainActivity;
    Context context;
    List<CivilGovermentOfficial> civilGovermentOfficialList;

    public CivilGovermentAdaptor(MainActivity mainActivity, List<CivilGovermentOfficial> civilGovermentOfficialList) {
        this.mainActivity =  mainActivity;
        this.civilGovermentOfficialList = civilGovermentOfficialList;
    }
    @Override
    public CivilGovermentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisItemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gov_list,
                parent, false);
        thisItemsView.setOnClickListener(mainActivity);

        return new CivilGovermentViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(CivilGovermentViewHolder holder, int position) {
        CivilGovermentOfficial civilGovermentOfficial = civilGovermentOfficialList.get(position);
        holder.cilvilOfficeNameText.setText(civilGovermentOfficial.getOfficeName());
        holder.civilOfficialNameText.setText(civilGovermentOfficial.getCivilOfficial().getOfficialName()+"("+ civilGovermentOfficial.getCivilOfficial().getOfficialParty()+")");

    }

    @Override
    public int getItemCount() {
        return civilGovermentOfficialList.size();
    }

}
