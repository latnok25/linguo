package com.linguo.admin.linguo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


public class CustomItemSelectedListener implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(), "OnItemSelectedListener:" + parent.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView arg0){}
}
