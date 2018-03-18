package com.hhu.acd.touching;

/**
 * Created by wei on 2018/3/17.
 */

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by liziming on 18-2-26.
 */

public class votation_adapter extends BaseAdapter {
    private List<itembean> mData;
    private Context mContext;


    public votation_adapter(List<itembean> mData, Context mContext){
        this.mData=mData;
        this.mContext=mContext;

    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position): null ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View item = null;

        ViewHolder vh = null;

        if (convertView == null) {
            item = View.inflate(mContext, R.layout.votation_item, null);
            vh = new ViewHolder();
            //找到文本框
            vh.button =  item.findViewById(R.id.delete);
            //找到复选框
            vh.edixt =  item.findViewById(R.id.selection);
            //让item和ViewHolder绑定在一起
            item.setTag(vh);
        } else {
            //复用ListView给的View
            item = convertView;
            //拿出ViewHolder
            vh = (ViewHolder) item.getTag();
        }
        final itembean itemObj = mData.get(position);
        if (vh.edixt.getTag() instanceof TextWatcher) {
            vh.edixt.removeTextChangedListener((TextWatcher) vh.edixt.getTag());
        }
        vh.edixt.setText(itemObj.getSelection());

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObj.setSelection("");
                } else {
                    itemObj.setSelection(s.toString());
                }
            }
        };

        vh.edixt.addTextChangedListener(watcher);
        vh.edixt.setTag(watcher);
        vh.button.setImageResource(R.drawable.ic_add_circle_blue_700_24dp);
        if(mData.get(position).getVisible()==true) vh.button.setVisibility(View.VISIBLE);
        else vh.button.setVisibility(View.INVISIBLE);
        vh.edixt.setHint("选项"+position);




        return convertView;
    }
    /**
     * 用于存放一个ItemView中的控件,由于这里只有两个控件,那么声明两个控件即可
     */
    class ViewHolder {
        ImageButton button;
        EditText edixt;
    }

}