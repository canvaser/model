package com.mobisoft.library.view.switchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 开关按钮
 * Created by canvaser on 2015/9/29.
 */
public class SwitchView extends View implements OnClickListener{

	/**宽度*/
	private int sv_width=0;
	/**高度*/
	private int sv_height=0;
	/**画笔*/
	private Paint sv_paint=new Paint();
	/**圆半径*/
	private int sv_circle_radious=0;
	/**边距*/
	private int sv_border=0;
	/**开关状态*/
	private boolean opened=false;
	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(opened){
//			sv_paint.setColor(Color.BLACK);
//			sv_paint.setAntiAlias(true);
//			RectF rectF=new RectF();
//			rectF.left=0;
//			rectF.right=sv_width;
//			rectF.bottom=sv_height;
//			rectF.top=0;
//			canvas.drawRoundRect(rectF,sv_height/2,sv_height/2, sv_paint);
//			sv_paint.setColor(Color.WHITE);
//			RectF rectF2=new RectF();
//			rectF2.left=sv_height/20f;
//			rectF2.right=sv_width-sv_height/20f;
//			rectF2.bottom=sv_height-sv_height/20f;
//			rectF2.top=sv_height/20f;
//			canvas.drawRoundRect(rectF2,sv_height*9/20f,sv_height*9/20f, sv_paint);
//			sv_paint.setColor(Color.RED);
//			canvas.drawCircle(sv_width-sv_circle_radious-sv_border,sv_height/2f, sv_circle_radious, sv_paint);
		}else{
//			sv_paint.setColor(Color.BLACK);
//			sv_paint.setAntiAlias(true);
//			RectF rectF=new RectF();
//			rectF.left=0;
//			rectF.right=sv_width;
//			rectF.bottom=sv_height;
//			rectF.top=0;
//			canvas.drawRoundRect(rectF,sv_height/2,sv_height/2, sv_paint);
//			sv_paint.setColor(Color.WHITE);
//			RectF rectF2=new RectF();
//			rectF2.left=sv_height/20f;
//			rectF2.right=sv_width-sv_height/20f;
//			rectF2.bottom=sv_height-sv_height/20f;
//			rectF2.top=sv_height/20f;
//			canvas.drawRoundRect(rectF2,sv_height*9/20f,sv_height*9/20f, sv_paint);
//			sv_paint.setColor(Color.RED);
//			canvas.drawCircle(sv_border+sv_circle_radious,sv_height/2f, sv_circle_radious, sv_paint);
		}
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
		sv_circle_radious=sv_height*5/12;
		sv_border=sv_height/12;
	}

	@Override
	public void onClick(View v) {
		opened=!opened;
		postInvalidate();
	}
}
