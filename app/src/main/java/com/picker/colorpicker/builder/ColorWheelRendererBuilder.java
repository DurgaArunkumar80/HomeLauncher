package com.picker.colorpicker.builder;

import com.picker.colorpicker.ColorPickerView;
import com.picker.colorpicker.renderer.ColorWheelRenderer;
import com.picker.colorpicker.renderer.FlowerColorWheelRenderer;
import com.picker.colorpicker.renderer.SimpleColorWheelRenderer;

public class ColorWheelRendererBuilder {
    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType) {
        switch (wheelType) {
            case CIRCLE:
                return new SimpleColorWheelRenderer();
            case FLOWER:
                return new FlowerColorWheelRenderer();
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}