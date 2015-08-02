package com.desitum.castleWars.libraries.ui.layout;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.desitum.castleWars.libraries.ui.widgets.ScrollArea;

/**
 * Created by Kody.VanRy on 7/25/2015.
 */
public class LinearLayout extends Layout {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    private int orientation;
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
     * @param camera     camera of the world
     */
    public LinearLayout(Texture background, float x, float y, float width, float height, Camera camera) {
        super(background, x, y, width, height, camera);
    }

    @Override
    public float getPostionToCenterInScroll(ScrollArea scrollArea, int widgetID) {
        int widgetPos = getChildren().indexOf(findWidgetByID(widgetID));
        float distance = 0;
        if (orientation == HORIZONTAL) {
            distance = -getDistanceBetween(0, widgetPos);
            distance += scrollArea.getWidth() / 2;
            distance -= getChildren().get(widgetPos).getWidth()/2;
            if (distance > 0) distance = 0;
            if (distance < -getWidth() + scrollArea.getWidth()) distance = -getWidth() + scrollArea.getWidth();
        } else if (orientation == VERTICAL) {
            distance = scrollArea.getHeight() - getHeight() + getDistanceBetween(0, widgetPos);
            distance -= scrollArea.getHeight() / 2;
            distance += getChildren().get(widgetPos).getWidth()/2;
            if (distance < 0) distance = 0;
            if (distance > getHeight() - scrollArea.getHeight()) distance = getHeight() - scrollArea.getHeight();
        }
        return distance;
    }

    public float getDistanceBetween(int index1, int index2) {
        if ((index1 - index2) == 0) {
            return 0;
        }
        float totalDistance = 0;
        if (orientation == HORIZONTAL) {
            for (int i = index1; i <= index2; i++) {
                totalDistance += getChildren().get(i).getWidth();
            }
        } else if (orientation == VERTICAL) {
            for (int i = index1; i <= index2; i++) {
                totalDistance += getChildren().get(i).getHeight();
            }
        }
        return totalDistance;
    }
}
