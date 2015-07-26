package com.desitum.castleWars.libraries.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvan6234 on 4/23/2015.
 */
public class ScrollArea extends Widget {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    private float scrollAmount;
    private int scrollDirection;
    private float spacing;
    private float activeWidth;
    private float activeHeight;

    private float lastDelta;
    private float touchDuration;
    private Vector3 lastTouchPos;

    private List<Animator> incomingAnimatorsToAdd;
    private List<Animator> outgoingAnimatorsToAdd;

    private MovementAnimator slideAnimator;
    private Layout child;

    public ScrollArea(Texture background, float x, float y, float width, float height, int scrollDirection, Camera camera) {
        super(background, width, height, x, y);

        super.setSize(width, height);
        scrollAmount = 0;
        this.scrollDirection = scrollDirection;
        this.spacing = spacing;
        this.activeWidth = width;
        this.activeHeight = height;
        this.touchDuration = 0;
        this.setPosition(x, y);

        lastTouchPos = new Vector3(0, 0, 0);
    }

    @Override
    public void updateScroll(float amount, Vector3 touchPos) {
        scrollAmount += amount;

        if (scrollAmount > 0) {
            scrollAmount = 0;
        } else if (scrollDirection == HORIZONTAL && scrollAmount < -getChild().getWidth()) {
            scrollAmount = -getChild().getWidth();
        } else if (scrollDirection == VERTICAL && scrollAmount < -getChild().getHeight()) {
            scrollAmount = -getChild().getHeight();
        }
        updateWidgets();
    }

    @Override
    boolean scrollPosMatters() {
        return false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        lastDelta = delta;

        if (slideAnimator != null) {
            if (slideAnimator.isRunning()) {
                slideAnimator.update(delta);
                scrollAmount = slideAnimator.getAmount();
                updateWidgets();
            }
        }
    }

    public void updateWidgets() {
        if (scrollDirection == HORIZONTAL) {
            getChild().setX(getX() + scrollAmount); // TODO untested
        } else {
            getChild().setY(getY() + scrollAmount);
        }
    }

    /**
     * updates the Layout with touch, done automatically in KodyWorld
     *
     * @param touchPos  - touchPos in relation to the world
     * @param clickDown - whether or not the mouse is down or not
     */
    @Override
    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        if (clickDown) touchDuration += lastDelta;
        else touchDuration = 0;
        if (clickDown && touchDuration >= 0.25f) {
            touchPos.set(getX() - 1, getY() - 1, touchPos.z);
            getChild().updateTouchInput(touchPos, false);
            updateScroll(touchPos.y - lastTouchPos.y, touchPos);
        } else {
            getChild().updateTouchInput(touchPos, clickDown);
        }

        lastTouchPos.set(touchPos);
    }

    @Override
    public ArrayList<Widget> getChildren(boolean walk) {
        ArrayList<Widget> returnArray = new ArrayList<Widget>();
        returnArray.add(getChild());
        return returnArray;
    }

    public void setContent(Layout toAdd) {
        if (scrollDirection == VERTICAL && toAdd.getWidth() == -1) {
            toAdd.setSize(toAdd.getHeight(), getWidth());
        } else if (scrollDirection == HORIZONTAL && toAdd.getHeight() == -1) {
            toAdd.setSize(getHeight(), toAdd.getWidth());
        }

        toAdd.setPosition(getX(), getY() + getHeight() - toAdd.getHeight()); // set it to fill the beginning of the layout

        if (scrollDirection == HORIZONTAL || scrollDirection == VERTICAL) {
            for (Animator anim : incomingAnimatorsToAdd) {
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
            for (Animator anim : outgoingAnimatorsToAdd) {
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
        setChild(toAdd);
        updateWidgets();
    }

    public void slideToPosition(float position) {
        slideAnimator = new MovementAnimator(scrollAmount, position, 0.75f, Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR);
        slideAnimator.start(false);
    }

    public void goToPosition(float position) {
        scrollAmount = position;
        updateWidgets();
    }

    public float getPositionToCenter(int widgetID) {
        return getChild().getPostionToCenterInScroll(this, widgetID);
    }

    public Layout getChild() {
        return child;
    }

    public void setChild(Layout child) {
        this.child = child;
    }

    @Override
    public void addIncomingAnimator(Animator animator) {
        super.addIncomingAnimator(animator);
        incomingAnimatorsToAdd.add(animator);
    }

    @Override
    public void addOutgoingAnimator(Animator animator) {
        super.addOutgoingAnimator(animator);
        outgoingAnimatorsToAdd.add(animator);
    }

    public int getScrollDirection() {
        return scrollDirection;
    }
}
