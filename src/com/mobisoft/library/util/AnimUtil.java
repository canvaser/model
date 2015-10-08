package com.mobisoft.library.util;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by canvaser on 2015/9/16.
 */
public class AnimUtil {

    /**
     *  渐变动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param listener  动画监听
     * @param value     动画属性值
     */
    public static void alphaAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"alpha",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  X轴缩放动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void scaleXAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"scaleX",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  Y轴缩放动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void scaleYAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"scaleY",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  缩放动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void scaleAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"scaleX",value).setDuration(duration),
                ObjectAnimator.ofFloat(v,"scaleY",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  旋转动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void rotateAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"rotation",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  旋转动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void rotateXAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"rotationX",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  旋转动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void rotateYAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"rotationY",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *   X轴移动动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void translateXAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"translationX",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }

    /**
     *  Y轴移动动画
     * @param v         操作对象
     * @param duration  动画持续时间
     * @param value     动画属性值
     */
    public static void translateYAnim(View v,int duration,AnimatorListenerAdapter listener,float... value){
        AnimatorSet set=new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v,"translationY",value).setDuration(duration));
        set.start();
        set.addListener(listener);
    }
}
