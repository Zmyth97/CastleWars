package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;

import java.util.ArrayList;

/**
 * Created by dvan6234 on 4/23/2015.
 */
public class PopupScrollArea extends PopupMenu {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    ArrayList<PopupWidget> widgets;
    private float scrollAmount;
    private int scrollDirection;
    private int columns;
    private int rows;
    private float widgetWidth;
    private float widgetHeight;
    private float spacing;
    private float activeWidth;
    private float activeHeight;

    private MovementAnimator slideAnimator;

    public PopupScrollArea(Texture background, float x, float y, float width, float height, float activeWidth, float activeHeight, int scrollDirection, int columns, int rows, float spacing, float widgetSize) {
        super(background, width, height, x, y);

        widgets = new ArrayList<PopupWidget>();

        scrollAmount = 0;
        this.scrollDirection = scrollDirection;
        this.columns = columns;
        if (scrollDirection == VERTICAL) this.columns = rows;
        this.widgetWidth = widgetSize;
        this.widgetHeight = widgetSize;
        this.spacing = spacing;
        this.activeWidth = activeWidth;
        this.activeHeight = activeHeight;
        this.setPosition(x , y);
    }

    public PopupScrollArea(Texture background, float x, float y, float width, float height, float activeWidth, float activeHeight, int scrollDirection, int columns, int rows, float spacing, float widgetWidth, float widgetHeight) {
        super(background, width, height, x, y);

        widgets = new ArrayList<PopupWidget>();

        scrollAmount = 0;
        this.scrollDirection = scrollDirection;
        this.columns = columns;
        if (scrollDirection == VERTICAL) this.columns = rows;
        this.widgetWidth = widgetWidth;
        this.widgetHeight = widgetHeight;
        this.spacing = spacing;
        this.activeWidth = activeWidth;
        this.activeHeight = activeHeight;
        this.setPosition(x, y);
    }

    public void updateScrollInput(float amount) {
        scrollAmount += amount;

        if (scrollAmount > 0) {
            scrollAmount = 0;
        } else if (scrollDirection == HORIZONTAL && scrollAmount < -(widgets.size() - 1) * (widgetWidth + spacing)) {
            scrollAmount = -(widgets.size() - 1) * (widgetWidth + spacing);
        }
        updateWidgets();
    }

    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        for (PopupWidget widget : widgets) {
            boolean clickInArea = CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), touchPos);
            if (widget.getClass().equals(PopupButton.class)) {
                PopupButton button = (PopupButton) widget;
                if (clickInArea && clickDown) {
                    button.onClickDown();
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            } else if (widget.getClass().equals(PopupImage.class)) {
                PopupImage button = (PopupImage) widget;
                if (clickInArea && clickDown) {
                    button.onClickDown();
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (PopupWidget widget : widgets) {
            widget.update(delta);
        }

        if (slideAnimator != null) {
            if (slideAnimator.isRunning()) {
                slideAnimator.update(delta);
                scrollAmount = slideAnimator.getAmount();
                updateWidgets();
            }
        }
    }

    @Override
    public void addIncomingAnimator(Animator anim) {
        super.addIncomingAnimator(anim);
    }

    @Override
    public void addOutgoingAnimator(Animator anim) {
        super.addOutgoingAnimator(anim);
    }

    @Override
    public void moveIn() {
        super.moveIn();
        for (PopupWidget widget : widgets) {
            widget.moveIn();
        }
    }

    @Override
    public void moveOut() {
        super.moveOut();
        for (PopupWidget widget : widgets) {
            widget.moveOut();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        for (PopupWidget wid : widgets) {
            wid.draw(batch);
        }
    }

    public void updateWidgets() {
        for (int widgetNum = 0; widgetNum < widgets.size(); widgetNum++) {
            PopupWidget widget = widgets.get(widgetNum);

            if (scrollDirection == HORIZONTAL) {
                widget.setX(getX() + getWidth() / 2 + (widgetNum / columns) * (widgetWidth + spacing) + scrollAmount);
                float widgetDistanceFromCenter = (getX() + getWidth() / 2 - widget.getX() - widget.getWidth() / 2) / activeWidth / 2;
                widgetDistanceFromCenter *= 4;
                if (widgetDistanceFromCenter < 0) widgetDistanceFromCenter *= -1;
                if (widgetDistanceFromCenter > 1) widgetDistanceFromCenter = 1;
                widget.setAlpha(1 - widgetDistanceFromCenter);
                widget.setScale(1 - widgetDistanceFromCenter, 1);
            } else {
                widget.setY(getY() + getWidth() / 2 + (widgetNum / columns) * (widgetHeight + spacing) + scrollAmount);
                float widgetDistanceFromCenter = (getY() + getHeight() / 2 - widget.getY()) / activeWidth / 2;
                widgetDistanceFromCenter *= 4;
                if (widgetDistanceFromCenter < 0) widgetDistanceFromCenter *= -1;
                if (widgetDistanceFromCenter > 1) widgetDistanceFromCenter = 1;
                widget.setAlpha(1 - widgetDistanceFromCenter);
                widget.setScale(1 - widgetDistanceFromCenter, 1);
            }
        }
    }

    public void addWidget(PopupWidget toAdd) {
        toAdd.setSize(widgetWidth, widgetHeight);

        if (scrollDirection == HORIZONTAL) {
            toAdd.setX(getX() + getWidth() / 2 + (widgets.size() / columns) * (widgetWidth + spacing));
            toAdd.setY(getY() + (widgets.size() % columns) * (widgetHeight + spacing));
        } else if (scrollDirection == VERTICAL) {
            toAdd.setY(getY() + getHeight() / 2 + (widgets.size() / columns) * (widgetWidth + spacing));
            toAdd.setX(getX() + (widgets.size() % columns) * (widgetHeight + spacing));
        }

        if (scrollDirection == HORIZONTAL || scrollDirection == VERTICAL) {
            for (Animator anim : getIncomingAnimatorsToAdd()) {
                Animator dupAnim = anim.duplicate();
                if (dupAnim.getClass().equals(MovementAnimator.class)) {
                    MovementAnimator dupMov = (MovementAnimator) dupAnim;
                    if (dupMov.isControllingX() && scrollDirection == VERTICAL) {
                        dupMov.setStartPos(toAdd.getX() - dupMov.getDistance());
                        dupMov.setEndPos(toAdd.getX());
                    }
                    if (dupMov.isControllingY() && scrollDirection == HORIZONTAL) {
                        dupMov.setStartPos(dupMov.getStartPos());
                        dupMov.setEndPos(dupMov.getEndPos());
                    }
                    toAdd.addIncomingAnimator(dupMov);
                }
            }
            for (Animator anim : getOutgoingAnimatorsToAdd()) {
                Animator dupAnim = anim.duplicate();
                if (dupAnim.getClass().equals(MovementAnimator.class)) {
                    MovementAnimator dupMov = (MovementAnimator) dupAnim;
                    if (dupMov.isControllingX()) {
                        dupMov.setStartPos(toAdd.getX() - dupMov.getDistance());
                        dupMov.setEndPos(toAdd.getX());
                    }
                    if (dupMov.isControllingY()) {
                        dupMov.setStartPos(toAdd.getY() - dupMov.getDistance());
                        dupMov.setEndPos(toAdd.getY());
                    }
                    toAdd.addOutgoingAnimator(dupMov);
                }
            }

            widgets.add(toAdd);
            updateWidgets();
        }
    }

    public void setWidgets(ArrayList<PopupWidget> widgetsToSet) {
        this.widgets = new ArrayList<PopupWidget>();
        for (PopupWidget widget : widgetsToSet) {
            this.addPopupWidget(widget);
        }
        updateWidgets();
    }

    public void goToWidget(int widgetNum) {
        if (scrollDirection == HORIZONTAL) {
            scrollAmount = -(widgetNum / columns) * (widgetWidth + spacing);
        }
        updateWidgets();
    }

    public void slideToPosition(float position) {
        slideAnimator = new MovementAnimator(scrollAmount, position, 0.75f, Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR);
        slideAnimator.start(false);
    }

    public float getPositionToCenter(int widgetNum) {
        if (scrollDirection == HORIZONTAL) {
            return -(widgetNum / columns) * (widgetWidth + spacing) - widgetWidth / 2;
        }
        return 0;
    }

    public void selectWidget(int position, boolean deselectRest) {
        if (deselectRest) {
            for (int pos = 0; pos < widgets.size(); pos++) {
                PopupWidget widget = widgets.get(pos);
                if (widget.getClass().equals(PopupImage.class)) {
                    PopupImage image = (PopupImage) widget;
                    if (pos == position) {
                        image.setActive();
                    } else {
                        image.deactivate();
                    }
                }
            }
        }
    }
}
