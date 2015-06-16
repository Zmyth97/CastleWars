package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
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
    private float scrollAmount;
    private int scrollDirection;
    private int columns;
    private float widgetWidth;
    private float widgetHeight;
    private float spacing;
    private float activeWidth;
    private float activeHeight;

    private MovementAnimator slideAnimator;

    public PopupScrollArea(Texture background, float x, float y, float width, float height, float activeWidth, float activeHeight, int scrollDirection, int columns, float spacing, float widgetSize) {
        super(background, width, height, x, y);

        super.setSize(width, height);
        scrollAmount = 0;
        this.scrollDirection = scrollDirection;
        this.columns = columns;
        if (scrollDirection == VERTICAL) this.columns = columns;
        this.widgetWidth = widgetSize;
        this.widgetHeight = widgetSize;
        this.spacing = spacing;
        this.activeWidth = activeWidth;
        this.activeHeight = activeHeight;
        this.setPosition(x, y);
    }

    public PopupScrollArea(Texture background, float x, float y, float width, float height, float activeWidth, float activeHeight, int scrollDirection, int columns, int rows, float spacing, float widgetWidth, float widgetHeight) {
        super(background, width, height, x, y);

        super.setSize(width, height);

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
        } else if (scrollDirection == HORIZONTAL && scrollAmount < -(getChildren().size() - 1) * (widgetWidth + spacing)) {
            scrollAmount = -(getChildren().size() - 1) * (widgetWidth + spacing);
        }
        updateWidgets();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (slideAnimator != null) {
            if (slideAnimator.isRunning()) {
                slideAnimator.update(delta);
                scrollAmount = slideAnimator.getAmount();
                updateWidgets();
            }
        }
    }

    public void updateWidgets() {
        for (int widgetNum = 0; widgetNum < getChildren().size(); widgetNum++) {
            PopupWidget widget = getChildren().get(widgetNum);

            if (scrollDirection == HORIZONTAL) {
                widget.setX(getX() + getWidth() / 2 + (widgetNum / columns) * (widgetWidth + spacing) + scrollAmount);
                float widgetDistanceFromCenter = (getX() + getWidth() / 2 - widget.getX() - widget.getWidth() / 2) / activeWidth / 2;
                widgetDistanceFromCenter *= 4;
                if (widgetDistanceFromCenter < 0) widgetDistanceFromCenter *= -1;
                if (widgetDistanceFromCenter > 1) widgetDistanceFromCenter = 1;
                if (isEnabled()) widget.setAlpha(1 - widgetDistanceFromCenter);
                widget.setScale(1 - widgetDistanceFromCenter, 1);
            } else {
                widget.setY(getY() + getHeight() / 2 + (widgetNum / columns) * (widgetHeight + spacing) + scrollAmount);
                float widgetDistanceFromCenter = (getY() + getHeight() / 2 - widget.getY()) / activeHeight / 2;
                widgetDistanceFromCenter *= 4;
                if (widgetDistanceFromCenter < 0) widgetDistanceFromCenter *= -1;
                if (widgetDistanceFromCenter > 1) widgetDistanceFromCenter = 1;
                if (isEnabled()) widget.setAlpha(1 - widgetDistanceFromCenter);
                widget.setScale(1 - widgetDistanceFromCenter, 1);
            }
        }
    }

    @Override
    public void addPopupWidget(PopupWidget toAdd) {
        toAdd.setSize(widgetWidth, widgetHeight);

        if (scrollDirection == HORIZONTAL) {
            toAdd.setX(getX() + getWidth() / 2 + (getChildren().size() / columns) * (widgetWidth + spacing));
            toAdd.setY(getY() + (getChildren().size() % columns) * (widgetHeight + spacing));
        } else if (scrollDirection == VERTICAL) {
            toAdd.setY(getY() + getHeight() / 2 + (getChildren().size() / columns) * (widgetWidth + spacing));
            System.out.println(toAdd.getY());
            toAdd.setX(getX() + (getChildren().size() % columns) * (widgetHeight + spacing));
            System.out.println(toAdd.getX());
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
        }
        getChildren().add(toAdd);
        updateWidgets();
    }

    public void setWidgets(ArrayList<PopupWidget> widgetsToSet) {
        super.setWidgets(widgetsToSet);
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
            for (int pos = 0; pos < getChildren().size(); pos++) {
                PopupWidget widget = getChildren().get(pos);
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
