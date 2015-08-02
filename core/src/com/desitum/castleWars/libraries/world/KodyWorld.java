package com.desitum.castleWars.libraries.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.game_objects.GameObject;
import com.desitum.castleWars.libraries.ui.layout.Layout;
import com.desitum.castleWars.libraries.ui.widgets.Widget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by kody on 5/21/15.
 * can be used by kody and people in []
 */
public class KodyWorld implements InputProcessor {

    private ArrayList<Widget> widgets;
    private ArrayList<Layout> layouts;
    private Vector3 touchPoint;
    private Viewport cam;

    private ArrayList<Widget> widgetsToAdd;
    private ArrayList<Widget> widgetsToRem;

    public KodyWorld() {
        widgets = new ArrayList<Widget>();
        widgetsToAdd = new ArrayList<Widget>();
        widgetsToRem = new ArrayList<Widget>();
        layouts = new ArrayList<Layout>();
        touchPoint = new Vector3(0, 0, 0);
    }

    public void update(float delta) {
        for (Widget widget : widgets) {
            widget.update(delta);
        }

        for (Layout layout : layouts) {
            layout.update(delta);
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

    public void draw(SpriteBatch batch) {
        for (Widget widget : widgets) {
            widget.draw(batch);
        }
    }

    public Camera getCam() {
        return cam.getCamera();
    }

    public void setCam(Viewport cam) {
        this.cam = cam;
    }

    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        boolean clickedOnWidget = false;
        for (int i = layouts.size() - 1; i >= 0; i--) {
            Layout layout = layouts.get(i);
            if (!layout.isEnabled()) continue;
            boolean clickInArea = (CollisionDetection.pointInRectangle(layout.getBoundingRectangle(), touchPos) && !clickedOnWidget);
            if (clickInArea) {
                layout.updateTouchInput(touchPos, clickDown);
                clickedOnWidget = true;
            }
        }
        if (!clickedOnWidget) {
            try {
                for (Widget widget : widgets) {
                    if (!widget.isEnabled()) continue;
                    widget.updateTouchInput(touchPos, clickDown);
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }

        for (Widget widget : widgetsToRem) {
            widgets.remove(widget);
        }
        widgetsToRem.clear();

        for (Widget widget : widgetsToAdd) {
            widgets.add(widget);
        }
        widgetsToAdd.clear();
    }

    public void addWidget(Widget widget) {
        widgetsToAdd.add(widget);
    }

    public void addPopupMenu(Layout layout) {
        layouts.add(layout);
    }

    public void removeWidget(Widget widget) {
        widgetsToRem.add(widget);
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    public ArrayList<Layout> getLayouts() {
        return layouts;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        cam.unproject(touchPoint.set(((float) screenX), ((float) screenY), 0));
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (Widget widget : widgets) {
            widget.updateScroll(amount, touchPoint);
        }

        return false;
    }

    public void addGameObject(GameObject gameObject) {
        //TODO we need game object functionality added to KodyWorld
    }
}
