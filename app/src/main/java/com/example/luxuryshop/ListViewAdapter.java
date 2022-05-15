package com.example.luxuryshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private final Context context;
    private final List<ProductDto> listViewItem;

    // ListViewAdapter의 생성자
    public ListViewAdapter(Context context, int resource, List<ProductDto> list) {
        this.context = context;
        this.listViewItem = list;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItem.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItem.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View v, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        v = inflater.inflate(R.layout.activity_listview_item, parent, false);

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView productCd = v.findViewById(R.id.tvProductCd);
        TextView productName = v.findViewById(R.id.tvProductName);
//        TextView barCode = v.findViewById(R.id.tvBarCode);
//        TextView wonAmt = v.findViewById(R.id.tvWonAmt);
//        TextView maeAmt = v.findViewById(R.id.tvMaeAmt);
//        TextView useYN = v.findViewById(R.id.tvUseYN);

        // 아이템 내 각 위젯에 데이터 반영
//        productCd.setText(listViewItem.get(position).getProductCd());
        productName.setText(listViewItem.get(position).getProductName());
//        barCode.setText(listViewItem.get(position).getBarCode());
//        wonAmt.setText(String.valueOf(listViewItem.get(position).getWon_Amt()));
//        maeAmt.setText(String.valueOf(listViewItem.get(position).getMae_Amt()));
//        useYN.setText(listViewItem.get(position).getUseYN());

        return v;
    }
}
