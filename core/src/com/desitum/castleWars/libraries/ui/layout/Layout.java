package com.desitum.castleWars.libraries.ui.layout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;
import com.desitum.castleWars.libraries.ui.widgets.ScrollArea;
import com.desitum.castleWars.libraries.ui.widgets.Widget;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in [Zack, Kody]
 */
public abstract class Layout extends Widget {

    private ArrayList<Widget> widgets;

    private ArrayList<Animator> incomingAnimatorsToAdd;
    private ArrayList<Animator> outgoingAnimatorsToAdd;

    private ShapeRenderer shapeRenderer;
    private Camera camera;

    private Texture background;

    /**
     * Create new Popup Layout with a blank canvas
     * use PopupWidgets to create and add them to the Layout
     * PopupWidgets will inherit all Animators from the Layout, and can be overriden
     *
     * @param background background texture for the image
     * @param x          left x position of the Layout
     * @param y          bottom y position of the Layout
     * @param width      the width of the Layout
     * @param height     the height of the Layout
     */
    public Layout(Texture background, float x, float y, float width, float height, Camera camera) {
        super(background, 0, 0, background.getWidth(), background.getHeight());
        widgets = new ArrayList<Widget>();
        incomingAnimatorsToAdd = new ArrayList<Animator>();
        outgoingAnimatorsToAdd = new ArrayList<Animator>();

        this.background = background;

        setPosition(x, y);
        this.setSize(width, height);

        shapeRenderer = new ShapeRenderer();
        this.camera = camera;

        disable();
    }

    /**
     * Draws the Layout with widgets on the screen
     *
     * @param batch batch to draw with
     */
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        if (batch.isDrawing()) {
            batch.end();
        }

// Enable depth testing and mask and disable the color mask.
        Gdx.gl.glDepthFunc(GL20.GL_LESS);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthMask(true);
        Gdx.gl.glColorMask(false, false, false, false);

// Draw your mask.
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(1f, 0f, 0f, 0.5f);
        //shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        //shapeRenderer.circle(getX() + getWidth()/2, getY() + getHeight()/2, getWidth()/2);
        shapeRenderer.ellipse(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 4, getHeight() / 2);
        shapeRenderer.end();

// Reenable the color mask and depth test.
        batch.begin();
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        // draw widgets such that they don't extend past the boundaries
        for (Widget widget : widgets) {
            widget.draw(batch);
        }
        batch.end();

// Clear the depth buffer.
        Gdx.gl.glClearDepthf(1f);
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        batch.begin();

// Draw something else (UI, etc...)
// ....
    }

    /**
     * updates the Layout with touch, done automatically in KodyWorld
     *
     * @param touchPos  - touchPos in relation to the world
     * @param clickDown - whether or not the mouse is down or not
     */
    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        for (Widget widget : widgets) { // Go through each Widget in the menu's widgets
            widget.updateTouchInput(touchPos, clickDown);
        }
    }

    /**
     * Updates information from the scroll wheel
     * all handled in KodyWorld
     *
     * @param amount   gives either 0, 1, or -1
     * @param mousePos pos of the cursor
     */
    @Override
    public void updateScroll(float amount, Vector3 mousePos) {
        for (Widget widget : widgets) {
            if (!(widget instanceof ScrollArea)) {
                continue;
            }
            if (widget.scrollPosMatters() && isEnabled()) {
                ScrollArea scrollArea = (ScrollArea) widget;
                scrollArea.updateScroll(amount, mousePos);
            } else if (isEnabled()) {
                ScrollArea scrollArea = (ScrollArea) widget;
                scrollArea.updateScroll(amount, mousePos);
            }
        }
    }

    @Override
    public boolean scrollPosMatters() {
        return true;
    }

    @Override
    public ArrayList getChildren(boolean walk) {
        ArrayList<Widget> widgets = new ArrayList<Widget>();
        widgets.addAll(getChildren());
        if (walk) {
            for (Widget widget : getChildren()) {
                widgets.addAll(widget.getChildren(walk));
            }
        }
        return widgets;
    }

    /**
     * update animation and widgets and their animations associated with the Layout
     *
     * @param delta - time since last frame
     */
    public void update(float delta) {
        for (Widget widget : widgets) {
            widget.update(delta);
        }

        updateAnimation(delta);
    }

    /**
     * updates animation for the Layout
     *
     * @param delta - time since last frame
     */
    private void updateAnimation(float delta) {
        for (Animator animator : getIncomingAnimatorsToAdd()) {
            if (animator.isRunning()) {
                if (animator instanceof MovementAnimator) {
                    MovementAnimator movementAnimator = (MovementAnimator) animator;
                    for (Widget widget : widgets) {
                        if (movementAnimator.isControllingX()) {
                            widget.setX(movementAnimator.getCurrentPos());
                        } else if (movementAnimator.isControllingY()) {
                            widget.setY(movementAnimator.getCurrentPos());
                        }
                    }
                } else if (animator instanceof ScaleAnimator) {
                    ScaleAnimator scaleAnimator = (ScaleAnimator) animator;
                    for (Widget widget: widgets) {
                        if (scaleAnimator.updateX()) {
                            widget.setScale(scaleAnimator.getScaleSize(), widget.getScaleY());
                        } else if (scaleAnimator.updateY()) {
                            widget.setScale(widget.getScaleY(), scaleAnimator.getScaleSize());
                        }
                    }
                } else if (animator instanceof ColorEffects) {
                    ColorEffects colorEffects = (ColorEffects) animator;
                    for (Widget widget: widgets) {
                        widget.setColor(colorEffects.getCurrentColor());
                    }
                }
            }
        }
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addIncomingAnimator(Animator anim) {
        super.addIncomingAnimator(anim);
        incomingAnimatorsToAdd.add(anim);
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addOutgoingAnimator(Animator anim) {
        super.addOutgoingAnimator(anim);
        outgoingAnimatorsToAdd.add(anim);
    }

    /**
     * add widget to the Layout
     * widget location is in relation to Layout
     *
     * @param toAdd
     */
    public void addPopupWidget(Widget toAdd) {
        for (Animator anim : incomingAnimatorsToAdd) {
            Animator dupAnim = anim.duplicate();
            if (dupAnim instanceof MovementAnimator) {
                MovementAnimator dupMov = (MovementAnimator) dupAnim;
                if (dupMov.isControllingX()) {
                    dupMov.setStartPos(toAdd.getX() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getX() + dupMov.getEndPos());
                }
                if (dupMov.isControllingY()) {
                    dupMov.setStartPos(toAdd.getY() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getY() + dupMov.getEndPos());
                }
                toAdd.addIncomingAnimator(dupMov);
            } else if (dupAnim instanceof ColorEffects) {
                System.out.println(((ColorEffects) dupAnim).getCurrentColor().a);
                dupAnim.start(false);
                dupAnim.update(0.5f);
                System.out.println(((ColorEffects) dupAnim).getCurrentColor().a);
                dupAnim.setSprite(toAdd, false, false);
                toAdd.addIncomingAnimator(dupAnim);
            }
        }
        for (Animator anim : outgoingAnimatorsToAdd) {
            Animator dupAnim = anim.duplicate();
            if (dupAnim.getClass().equals(MovementAnimator.class)) {
                MovementAnimator dupMov = (MovementAnimator) dupAnim;
                if (dupMov.isControllingX()) {
                    dupMov.setStartPos(toAdd.getX() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getX() + dupMov.getEndPos());
                }
                if (dupMov.isControllingY()) {
                    dupMov.setStartPos(toAdd.getY() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getY() + dupMov.getEndPos());
                }
                toAdd.addOutgoingAnimator(dupMov);
            } else if (dupAnim instanceof ColorEffects) {
                dupAnim.setSprite(toAdd, false, false);
                toAdd.addOutgoingAnimator(dupAnim);
            }
        }

        toAdd.setX(getX() + toAdd.getX());
        toAdd.setY(getY() + toAdd.getY());
        widgets.add(toAdd);
    }

    /**
     * start animators added to incomingAnimators
     */
    @Override
    public void moveIn() {
        super.moveIn();
        for (Widget widget : widgets) {
            widget.moveIn();
        }
    }

    /**
     * start animators added to outgoingAnimators
     */
    @Override
    public void moveOut() {
        super.moveOut();
        for (Widget widget : widgets) {
            widget.moveOut();
        }
    }

    @Override
    public void clearColorEffects() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
    }

    @Override
    public void clearMovementAnimators() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
    }

    @Override
    public void clearScaleAnimators() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
    }

    protected ArrayList<Animator> getIncomingAnimatorsToAdd() {
        return incomingAnimatorsToAdd;
    }

    protected ArrayList<Animator> getOutgoingAnimatorsToAdd() {
        return outgoingAnimatorsToAdd;
    }

    @Override
    public void setX(float x) {
        if (widgets != null) {
            for (Widget widget : widgets) {
                widget.setX(widget.getX() - getX() + x);
            }
        }
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        if (widgets != null) {
            for (Widget widget : widgets) {
                widget.setY(widget.getY() - getY() + y);
            }
        }
        super.setY(y);
    }

    public ArrayList<Widget> getChildren() {
        return widgets;
    }

    public void setWidgets(ArrayList<Widget> widgetsToSet) {
        this.widgets = new ArrayList<Widget>();
        for (Widget widget : widgetsToSet) {
            this.addPopupWidget(widget);
        }
    }

    public void clearChildren() {
        this.widgets = new ArrayList<Widget>();
    }

    public abstract float getPostionToCenterInScroll(ScrollArea scrollArea, int widgetID);

    public Widget findWidgetByID (int id) {
        Widget returnWidget = null;
        for (Widget widget : getChildren()) {
            if (widget.getID() == id)
                returnWidget = widget;
        }
        return returnWidget;
    }

    public abstract float getDistanceBetween(int index1, int index2);
}

