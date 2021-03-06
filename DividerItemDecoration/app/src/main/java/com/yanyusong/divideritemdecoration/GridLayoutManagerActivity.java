package com.yanyusong.divideritemdecoration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yanyusong.divideritemdecoration.y_recycleradapter.GeneralRecyclerViewHolder;
import com.yanyusong.divideritemdecoration.y_recycleradapter.Y_ItemEntityList;
import com.yanyusong.divideritemdecoration.y_recycleradapter.Y_MultiRecyclerAdapter;
import com.yanyusong.divideritemdecoration.y_recycleradapter.Y_OnBind;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/4/6.
 */

public class GridLayoutManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Y_ItemEntityList itemEntityList = new Y_ItemEntityList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        List<String> items = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            items.add("item" + i);
        }
        itemEntityList.addItems(R.layout.item_recyclerview_text, items)
                .addOnBind(R.layout.item_recyclerview_text, new Y_OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        holder.setText(R.id.textView, (String) itemData);
                    }
                });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new Y_MultiRecyclerAdapter(this, itemEntityList));


        recyclerView.addItemDecoration(new DividerItemDecoration(this, 6, 0xff666666));


    }


    private class DividerItemDecoration extends Y_DividerItemDecoration {

        private DividerItemDecoration(Context context, int lineWidthDp, @ColorInt int colorRGB) {
            super(context, lineWidthDp, colorRGB);
        }

        @Override
        public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
            //顺序:left, top, right, bottom
            boolean[] booleans = {false, false, false, false};
            switch (itemPosition % 3) {
                case 0:
                case 1:
                    //每一行前两个显示rignt和bottom
                    booleans[2] = true;
                    booleans[3] = true;
                    break;
                case 2:
                    //最后一个只显示bottom
                    booleans[3] = true;
                    break;
                default:
                    break;
            }

            return booleans;
        }
    }


}
