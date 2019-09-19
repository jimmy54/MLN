/**
  * Created by MomoLuaNative.
  * Copyright (c) 2019, Momo Group. All rights reserved.
  *
  * This source code is licensed under the MIT.
  * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
  */
package com.immomo.mls.fun.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.immomo.mls.fun.ud.view.IBorderRadiusView;
import com.immomo.mls.fun.ud.view.UDView;
import com.immomo.mls.util.LuaViewUtil;
import com.immomo.mls.utils.ViewClipHelper;

import androidx.annotation.NonNull;

/**
 * Created by XiongFangyu on 2018/8/1.
 */
public class BorderRadiusFrameLayout extends ForegroundFrameLayout implements IBorderRadiusView, ViewClipHelper.SuperDrawAction {
    private final @NonNull
    BorderBackgroundDrawable backgroundDrawable;
    private final @NonNull
    ViewClipHelper viewClipHelper;

    public BorderRadiusFrameLayout(@NonNull Context context) {
        super(context);
        backgroundDrawable = new BorderBackgroundDrawable();
        viewClipHelper = new ViewClipHelper();
    }

    //<editor-fold desc="IBorderRadiusView">
    @Override
    public void setBgColor(int color) {
        backgroundDrawable.setBgColor(color);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }

    @Override
    public void setBgDrawable(Drawable drawable) {
        backgroundDrawable.setBgDrawable(drawable);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }

    @Override
    public void setDrawRadiusBackground(boolean draw) {
        viewClipHelper.setDrawRadiusBackground(draw);
//        backgroundDrawable.setDrawRadiusBackground(draw);
    }

    @Override
    public int getBgColor() {
        return backgroundDrawable.getBgColor();
    }

    @Override
    public void setGradientColor(int start, int end, int type) {
        backgroundDrawable.setGradientColor(start, end, type);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }

    @Override
    public void setRadiusColor(int color) {
        viewClipHelper.setRadiusColor(color);
    }

    @Override
    public void setStrokeWidth(float width) {
        backgroundDrawable.setStrokeWidth(width);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }

    @Override
    public void setStrokeColor(int color) {
        backgroundDrawable.setStrokeColor(color);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }

    @Override
    public void setCornerRadius(float radius) {
        backgroundDrawable.setCornerRadius(radius);
        LuaViewUtil.setBackground(this, backgroundDrawable);
        viewClipHelper.setRadius(radius);
    }

    @Override
    public void setRadius(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        backgroundDrawable.setRadius(topLeft, topRight, bottomLeft, bottomRight);
        LuaViewUtil.setBackground(this, backgroundDrawable);
        viewClipHelper.setRadius(topLeft, topRight, bottomLeft, bottomRight);
    }

    @Override
    public void setRadius(int direction, float radius) {
        backgroundDrawable.setRadius(direction, radius);
        LuaViewUtil.setBackground(this, backgroundDrawable);
        viewClipHelper.setRadius(backgroundDrawable);
    }

    @Override
    public void setUDView(UDView udView) {

    }

    @Override
    public float getStrokeWidth() {
        return  backgroundDrawable.getStrokeWidth();
    }

    @Override
    public int getStrokeColor() {
        return backgroundDrawable.getStrokeColor();
    }

    @Override
    public float getCornerRadiusWithDirection(int direction) {
        return backgroundDrawable.getCornerRadiusWithDirection(direction);
    }

    @Override
    public float getRadius(int direction) {
        return backgroundDrawable.getRadius(direction);
    }

    @Override
    public float[] getRadii() {
        return backgroundDrawable.getRadii();
    }

    @Override
    public void drawBorder(Canvas canvas) {
        backgroundDrawable.drawBorder(canvas);
    }
//</editor-fold>

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewClipHelper.updatePath(w, h, backgroundDrawable.getStrokeWidth());
    }

    @Override
    public void draw(Canvas canvas) {
        if (viewClipHelper.needClicp()) {
            viewClipHelper.clip(canvas, this);
        } else {
            super.draw(canvas);
        }
    }

    @Override
    public void innerDraw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        drawBorder(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled())
            backgroundDrawable.onRippleTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void setDrawRipple(boolean drawRipple) {
        if (drawRipple)
            setClickable(true);
        backgroundDrawable.setDrawRipple(drawRipple);
        LuaViewUtil.setBackground(this, backgroundDrawable);
    }
}