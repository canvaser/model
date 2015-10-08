package com.mobisoft.library.view.selectview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mobisoft.library.R;

/**
 * Created by canvaser on 2015/9/29.
 */
public class SelectView extends LinearLayout implements View.OnClickListener{

	private boolean isSelected=false;
	int select_id=-1;
	int un_select_id=-1;
	public SelectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.select_view);
		select_id=array.getResourceId(R.styleable.select_view_select_id,-1);
		un_select_id=array.getResourceId(R.styleable.select_view_unselect_id,-1);
		this.setOnClickListener(this);
	}

	@Override
	public boolean isSelected() {
		return isSelected;

	}

	public void setIsSelected(boolean isSelected) {
		ImageView imageView=null;
		for(int i=0;i<this.getChildCount();i++){
			if(getChildAt(i) instanceof  ImageView){
				imageView= (ImageView) this.getChildAt(0);
				break;
			}
		}
		if(imageView==null){
			return;
		}
		if(isSelected){
			if(select_id!=-1){
				imageView.setImageResource(select_id);
				invalidate();
			}
		}else{
			if(un_select_id!=-1){
				imageView.setImageResource(un_select_id);
				invalidate();
			}
		}
		this.isSelected = isSelected;
	}

	@Override
	public void onClick(View v) {
		setIsSelected(!isSelected());
	}
}
