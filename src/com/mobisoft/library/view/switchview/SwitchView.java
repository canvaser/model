package com.mobisoft.library.view.switchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 开关按钮
 * Created by canvaser on 2015/9/29.
 */
public class SwitchView extends View{

	/**宽度*/
	private int sv_width=0;
	/**高度*/
	private int sv_height=0;
	/**画笔*/
	private Paint sv_paint=new Paint();
	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		sv_paint.setColor(Color.RED);
		RectF rectF=new RectF();
		rectF.left=0;
		rectF.right=sv_width;
		rectF.bottom=sv_height;
		rectF.top=0;
		canvas.drawOval(rectF,sv_paint);
		//canvas.drawOval(0,0,sv_width,sv_height,sv_paint);
		//canvas.drawOval(0,0,sv_width,sv_height,sv_paint);
		//canvas.drawRoundRect(0,0,sv_width,sv_height,sv_width/5,sv_height/5,sv_paint);
	}



	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		sv_width=getWidth();
		sv_height=getHeight();
	}
}
