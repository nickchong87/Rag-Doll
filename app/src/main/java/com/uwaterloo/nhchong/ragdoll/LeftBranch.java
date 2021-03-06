package com.uwaterloo.nhchong.ragdoll;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

public class LeftBranch extends BodyPart {

    // Construct a circle with the given dimensions
    // The matrix will be used to determine location (defaults to identity matrix)
    // By default, is drawn centred at the origin
    // Assumes: positive radius
    LeftBranch(float _width, float _height) {
        super("Left Upper Arm", 45, _width, _height);
    }

    // Draw using the current matrix
    protected void drawBodyPart(Canvas canvas, Paint paint) {
        Paint green = new Paint(Paint.ANTI_ALIAS_FLAG);
        green.setColor(Color.BLACK);
        canvas.drawRoundRect(rectangle, 20, 20, green);
    }

    protected void initialize() {
        rectangle = new RectF(0, 0, width, height);
        BodyPart parent = getParent();
        float headHeightBuffer = getHeight();
        float headWidthBuffer = (parent.getWidth()/2)-(getWidth()/2);

        anchorPointX = 0;
        anchorPointY = 0;

        this.translate(-(width/2), -getHeight());
        this.matrix.postRotate(330, anchorPointX, anchorPointY);
        this.rotationMatrix.postRotate(330, anchorPointX, anchorPointY);
    }

    protected float getRotationDegrees(float prevX, float prevY, float newX, float newY) {
        float[] transformedPoint = getTransformedPoints(prevX, prevY);
        double angle = Math.toDegrees(Math.atan(newY/newX) -  Math.atan(transformedPoint[1]/transformedPoint[0]));
        System.out.println("angle =  " + Math.toDegrees(angle));
        return -(float)angle;
    }

}
