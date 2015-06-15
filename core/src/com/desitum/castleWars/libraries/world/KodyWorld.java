package com.desitum.castleWars.libraries.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
import com.desitum.castleWars.libraries.menu.PopupSlider;
import com.desitum.castleWars.libraries.menu.PopupSpinner;
import com.desitum.castleWars.libraries.menu.PopupToggleButton;
import com.desitum.castleWars.libraries.menu.PopupWidget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by kody on 5/21/15.
 * can be used by kody and people in []
 */
public class KodyWorld implements InputProcessor {

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
        boolean clickedOnWidget = false;
        for (int i = menus.size() - 1; i >= 0; i--) {
            PopupMenu menu = menus.get(i);
            if (!menu.isEnabled()) continue;
            boolean clickInArea = (CollisionDetection.pointInRectangle(menu.getBoundingRectangle(), touchPos) && !clickedOnWidget);
            if (clickInArea) {
                menu.updateTouchInput(touchPos, clickDown);
                clickedOnWidget = true;
            }
        }
        if (!clickedOnWidget) {
            try {
                for (PopupWidget widget : widgets) {
                    if (!widget.isEnabled()) continue;
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
                    } else if (widget instanceof PopupButtonMaterial) {
                        PopupButtonMaterial button = (PopupButtonMaterial) widget;
                        if (clickInArea && clickDown) {
                            button.onClickDown(touchPos);
                        } else if (clickInArea) {
                            button.onClickUp(true);
                        } else {
                            button.onClickUp(false); // handles if not in area
                        }
                    } else if (widget instanceof PopupScrollArea) {
                        PopupScrollArea popupScrollArea = (PopupScrollArea) widget;
                        popupScrollArea.updateTouchInput(touchPos, clickDown);
                    } else if (widget instanceof PopupImage) {
                        PopupImage image = (PopupImage) widget;
                        if (clickInArea && clickDown) {
                            image.onClickDown();
                        } else if (clickInArea) {
                            image.onClickUp(true);
                        } else {
                            image.onClickUp(false);
                        }
                    } else if (widget instanceof PopupToggleButton) {
                        PopupToggleButton button = (PopupToggleButton) widget;
                        if (clickInArea && clickDown) {
                            button.onClickDown();
                        } else if (clickInArea) {
                            button.onClickUp(true);
                        } else {
                            button.onClickUp(false);
                        }
                    } else if (widget instanceof PopupSpinner) {
                        PopupSpinner spinner = (PopupSpinner) widget;
                        spinner.updateTouchInput(touchPos, clickDown);
                    }
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }

        for (PopupWidget widget: widgetsToRem) {
            widgets.remove(widget);
        }
        widgetsToRem.clear();

        for (PopupWidget widget: widgetsToAdd) {
            widgets.add(widget);
        }
        widgetsToAdd.clear();
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
        for (PopupWidget widget : widgets) {
            widget.updateScroll(amount, touchPoint);
        }

        return false;
    }
}
