package com.desitum.castleWars.libraries.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
import com.desitum.castleWars.libraries.menu.PopupSlider;
import com.desitum.castleWars.libraries.menu.PopupWidget;

import java.util.ArrayList;

/**
 * Created by kody on 5/21/15.
 * can be used by kody and people in []
 */
public class KodyWorld {

    private ArrayList<PopupWidget> widgets;
    private Vector3 touchPoint;
    private Viewport cam;

    public KodyWorld () {
        widgets = new ArrayList<PopupWidget>();
        touchPoint = new Vector3(0, 0, 0);
    }
    public void update(float delta) {
        for (PopupWidget widget: widgets) {
            widget.update(delta);
        }

        if (Gdx.input.isTouched()) {
            if (cam != null) {
                touchPoint = cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            }
        }

        if (cam != null) {
            updateTouchInput(touchPoint, Gdx.input.isTouched());
        }
    }

    public void setCam(Viewport cam) {
        this.cam = cam;
    }

    public void updateTouchInput(Vector3 touchPos, boolean clickDown){
        for (PopupWidget widget: widgets){
            boolean clickInArea = CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), touchPos);
            if (widget.getClass().equals(PopupButton.class)){
                PopupButton button = (PopupButton) widget;
                if (clickInArea && clickDown){
                    button.onClickDown();
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            } else if (widget.getClass().equals(PopupSlider.class)){
                PopupSlider slider = (PopupSlider) widget;
                if (clickInArea && clickDown){
                    slider.onClickDown(touchPos);
                } else if (clickInArea) {
                    slider.onClickUp();
                } else {
                    slider.onClickUp(); // handles if not in area
                }
            } else if (widget.getClass().equals(PopupScrollArea.class)){
                PopupScrollArea popupScrollArea = (PopupScrollArea) widget;
                popupScrollArea.updateTouchInput(touchPos, clickDown);
            }
        }
    }

    public void addWidget(PopupWidget widget){
        widgets.add(widget);
    }

    public ArrayList<PopupWidget> getWidgets() {
        return widgets;
    }
}
