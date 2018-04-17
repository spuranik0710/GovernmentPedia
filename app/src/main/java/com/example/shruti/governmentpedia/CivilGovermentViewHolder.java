package com.example.shruti.governmentpedia;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 7000 on 4/13/2017.
 */

public class CivilGovermentViewHolder extends RecyclerView.ViewHolder {
    public TextView cilvilOfficeNameText;
    public TextView civilOfficialNameText;

    public CivilGovermentViewHolder(View itemView) {
        super(itemView);
        cilvilOfficeNameText = (TextView) itemView.findViewById(R.id.ofcNameText);
        civilOfficialNameText = (TextView) itemView.findViewById(R.id.officialNameText);
    }
}
