package com.example.shruti.governmentpedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by 7000 on 4/13/2017.
 */

public class PhotoActivity extends AppCompatActivity {
    TextView aboutTxtView;
    ImageView officialImgView;
    ConstraintLayout backgroundLayoutContraint;
    CivilGovermentOfficial mainCivilGovermentOfficial;
    TextView phtActLocTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        aboutTxtView = (TextView) findViewById(R.id.paOfcialInfoText);
        officialImgView = (ImageView) findViewById(R.id.paImgView);
        backgroundLayoutContraint = (ConstraintLayout) findViewById(R.id.phtActContraint);
        phtActLocTv = (TextView) findViewById(R.id.photoActBannerText);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent ofcActIntent = getIntent();

        CivilGovermentOfficial civilGovermentOfficial = (CivilGovermentOfficial) ofcActIntent.getSerializableExtra(getString(R.string.SerializeGovementObject));
        mainCivilGovermentOfficial = civilGovermentOfficial;
        OfficialAddress locaAdd = civilGovermentOfficial.getBannerTextAddress();
        setOfficialImage(civilGovermentOfficial);
        aboutTxtView .setText(getOfficialInfoString(civilGovermentOfficial));
        phtActLocTv.setText(locaAdd.getCity()+","+locaAdd.getState()+","+locaAdd.getZip());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent clickedNoteIntent = new Intent();
        setResult(RESULT_OK, clickedNoteIntent);
        finish();
    }


    String getOfficialInfoString(CivilGovermentOfficial civilGovermentOfficial){
        String aboutString = "";
        aboutString = civilGovermentOfficial.getOfficeName()
                + "\n"+ civilGovermentOfficial.getCivilOfficial().getOfficialName();
        if(civilGovermentOfficial.getCivilOfficial().getOfficialParty()!=null && civilGovermentOfficial.getCivilOfficial().getOfficialParty().length() > 0){
          //  aboutString =aboutString + "\n("+ civilGovermentOfficial.getCivilOfficial().getOfficialParty()+")";

            if(civilGovermentOfficial.getCivilOfficial().getOfficialParty().equals(OfficialActivity.OfficialPartyConstant.PARTY_DEMOCRATIC)){
                int backgroundColor = getResources().getColor(R.color.colorBlue);
                backgroundLayoutContraint.setBackgroundColor(backgroundColor);
            }else if (civilGovermentOfficial.getCivilOfficial().getOfficialParty().equals(OfficialActivity.OfficialPartyConstant.PARTY_REPUBLICAN)){
                int myColor = getResources().getColor(R.color.colorRed);
                backgroundLayoutContraint.setBackgroundColor(myColor);
            }
        }
        return aboutString;
    }

    private void setOfficialImage(final CivilGovermentOfficial civilGovermentOfficial){
        final String photoUrl = civilGovermentOfficial.getCivilOfficial().getOfficialPhotoLink();
        if ( photoUrl != null) {
            Picasso picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    // Here we try https if the http image attempt failed
                    final String changedUrl = photoUrl.replace("http:", "https:");

                    picasso.load(changedUrl)
                            .fit()
                            .error(R.drawable.brokenimage)
                            .placeholder(R.drawable.hourglass)
                            .into(officialImgView);
                }
            }).build();

            picasso.load(photoUrl)
                    .fit()
                    .error(R.drawable.brokenimage)
                    .placeholder(R.drawable.hourglass)
                    .into(officialImgView);

        } else {
            Picasso.with(this).load(photoUrl)
                    .fit()
                    .error(R.drawable.brokenimage)
                    .placeholder(R.drawable.missingimage)
                    .into(officialImgView);

        }
    }

}
