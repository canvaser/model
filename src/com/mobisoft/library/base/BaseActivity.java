package com.mobisoft.library.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobisoft.library.AppManager;
import com.mobisoft.library.R;

public abstract class BaseActivity extends Activity implements View.OnClickListener {

	/**头部布局跟布局*/
	RelativeLayout ll_head_root;
	/**左边文字*/
	TextView tv_left;
	/**左边图标*/
	ImageView iv_left;
	/**中间文字*/
	TextView tv__middle;
	/**中间图标*/
	ImageView iv__middle;
	/**右边文字*/
	TextView tv_right;
	/**右边图标*/
	ImageView iv_right;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		initWindow();
		initViews();
		initEvents();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}

	protected  abstract  void initWindow();

	protected abstract void initViews();

	protected abstract void initEvents();


	/**
	 * 设置头部布局
	 * @param leftText
	 * @param leftImage
	 * @param middleText
	 * @param middleImage
	 * @param rightText
	 * @param rightImage
	 */
	public void initHeadView(String leftText,int leftImage,String middleText,int middleImage,String rightText,int rightImage){
		ll_head_root= (RelativeLayout) findViewById(R.id.ll_head_root);
		tv_left= (TextView) findViewById(R.id.tv_left);
		iv_left= (ImageView) findViewById(R.id.iv_left);
		tv__middle= (TextView) findViewById(R.id.tv__middle);
		iv__middle= (ImageView) findViewById(R.id.iv__middle);
		tv_right= (TextView) findViewById(R.id.tv_right);
		iv_right= (ImageView) findViewById(R.id.iv_right);

		if(ll_head_root==null){
			return;
		}

		tv_left.setText(leftText);
		if(leftImage>0){
			iv_left.setImageResource(leftImage);
		}

		tv__middle.setText(middleText);
		if(middleImage>0){
			iv_left.setImageResource(middleImage);
		}

		tv_right.setText(rightText);
		if(rightImage>0){
			iv_left.setImageResource(rightImage);
		}

	}
	/**
	 * 打开至指定的Activity
	 *
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 打开至指定的Activity
	 *
	 * @param pClass
	 * @param pBundle
	 *            传值
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 打开activity
	 *
	 * @param pAction
	 *            activity动作
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * 打开activity
	 *
	 * @param pAction
	 *            activity动作
	 * @param pBundle
	 *            数据
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 返回activity ，绑定数据
	 *
	 * @param pClass
	 */
	protected void returnActivity(Class<?> pClass) {
		returnActivity(pClass, null);
	}

	/**
	 * 返回activity ，绑定数据
	 *
	 * @param pClass
	 * @param pBundle
	 */
	protected void returnActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	/**
	 * 返回activity
	 *
	 * @param pAction
	 *            activity动作
	 */
	protected void returnActivity(String pAction) {
		returnActivity(pAction, null);
	}

	/**
	 * 返回activity
	 *
	 * @param pAction
	 *            activity动作
	 * @param pBundle
	 *            数据
	 */
	protected void returnActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {

	}
}
