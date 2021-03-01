package com.app.homelauncher.viewutil;

import com.app.homelauncher.widget.Desktop;

import in.championswimmer.sfg.lib.SimpleFingerGestures;

public class DesktopGestureListener implements SimpleFingerGestures.OnFingerGestureListener {

    private final DesktopGestureCallback _callback;
    private final Desktop _desktop;

    public DesktopGestureListener(Desktop desktop, DesktopGestureCallback callback) {
        _desktop = desktop;
        _callback = callback;
    }

    @Override
    public boolean onSwipeUp(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.SwipeUp);
    }

    @Override
    public boolean onSwipeDown(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.SwipeDown);
    }

    @Override
    public boolean onSwipeLeft(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.SwipeLeft);
    }

    @Override
    public boolean onSwipeRight(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.SwipeRight);
    }

    @Override
    public boolean onPinch(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.Pinch);
    }

    @Override
    public boolean onUnpinch(int i, long l, double v) {
        return _callback.onDrawerGesture(_desktop, Type.Unpinch);
    }

    @Override
    public boolean onDoubleTap(int i) {
        return _callback.onDrawerGesture(_desktop, Type.DoubleTap);
    }

    public enum Type {
        SwipeUp,
        SwipeDown,
        SwipeLeft,
        SwipeRight,
        Pinch,
        Unpinch,
        DoubleTap
    }

    public interface DesktopGestureCallback {
        boolean onDrawerGesture(Desktop desktop, Type event);
    }
}