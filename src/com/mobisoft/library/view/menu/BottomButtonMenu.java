package com.mobisoft.library.view.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mobisoft.library.R;

/**
 * 底部导航按钮
 * Created by canvaser on 2015/9/28.
 */
public class BottomButtonMenu extends View {


	private int v_width;
	private int v_height;
	private Bitmap v_bitmap;
	private Paint v_paint;
	public BottomButtonMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(v_bitmap==null){
			v_bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
			//v_bitmap=Bitmap.createBitmap(v_bitmap,v_width);
		}
		canvas.drawBitmap(v_bitmap,v_width/7,v_height/7,v_paint);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		v_height=getHeight();
		v_width=getWidth();
	}
}
