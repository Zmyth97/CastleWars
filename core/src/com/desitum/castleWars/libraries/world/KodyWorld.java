package com.desitum.castleWars.libraries.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
import com.desitum.castleWars.libraries.menu.PopupSlider;
import com.desitum.castleWars.libraries.menu.PopupTextLabel;
import com.desitum.castleWars.libraries.menu.PopupWidget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by kody on 5/21/15.
 * can be used by kody and people in []
 */
public class KodyWorld {

    private ArrayList<PopupWidget> widgets;
    private ArrayList<PopupMenu> menus;
    private Vector3 touchPoint;
    private Viewport cam;

    private ArrayList<PopupWidget> widgetsToAdd;
    private ArrayList<PopupWidget> widgetsToRem;

    public KodyWorld () {
        widgets = new ArrayList<PopupWidget>();
        widgetsToAdd = new ArrayList<PopupWidget>();
        widgetsToRem = new ArrayList<PopupWidget>();
        menus = new ArrayList<PopupMenu>();
        touchPoint = new Vector3(0, 0, 0);
    }
    public void update(float delta) {
        for (PopupWidget widget: widgets) {
            widget.update(delta);
        }

        for (PopupMenu menu: menus) {
            menu.update(delta);
        }

        if (Gdx.input.isTouched()) {
            if (cam != null) {
                cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            }
        }

        if (cam != null) {
            updateTouchInput(touchPoint, Gdx.input.isTouched());
        }
    }

    public void setCam(Viewport cam) {
        this.cam = cam;
    }

    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        try {
            for (PopupWidget widget : widgets) {
                boolean clickInArea = CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), touchPos);
                if (widget instanceof PopupButton) {
                    PopupButton button = (PopupButton) widget;
                    if (clickInArea && clickDown) {
                        button.onClickDown();
                    } else if (clickInArea) {
                        button.onClickUp(true);
                    } else {
                        button.onClickUp(false);
                    }
                } else if (widget instanceof PopupSlider) {
                    PopupSlider slider = (PopupSlider) widget;
                    if (clickInArea && clickDown) {
                        slider.onClickDown(touchPos);
                    } else if (clickInArea) {
                        slider.onClickUp();
                    } else {
                        slider.onClickUp(); // handles if not in area
                    }
                } else if (widget instanceof PopupScrollArea) {
                    PopupScrollArea popupScrollArea = (PopupScrollArea) widget;
                    popupScrollArea.updateTouchInput(touchPos, clickDown);
                }
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }

        for (PopupWidget widget: widgetsToRem) {
            widgets.remove(widget);
        }
        widgetsToRem.clear();

        for (PopupWidget widget: widgetsToAdd) {
            widgets.add(widget);
        }
        widgetsToAdd.clear();

        for (PopupMenu menu: menus) {
            menu.updateTouchInput(touchPos, clickDown);
        }
    }

    public void addWidget(PopupWidget widget){
        widgetsToAdd.add(widget);
    }

    public void addPopupMenu(PopupMenu menu) {
        menus.add(menu);
    }

    public void removeWidget(PopupWidget widget) {
        widgetsToRem.add(widget);
    }

    public ArrayList<PopupWidget> getWidgets() {
        return widgets;
    }

    public ArrayList<PopupMenu> getMenus() {
        return menus;
    }
}
