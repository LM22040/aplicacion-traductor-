package com.google.appinventor.components.runtime.util;

import java.util.List;

/* loaded from: classes.dex */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double magnitude() {
        return Math.sqrt(magnitudeSquared());
    }

    public double magnitudeSquared() {
        double d = this.x;
        double d2 = this.y;
        return (d * d) + (d2 * d2);
    }

    public boolean isGreater(Vector2D that) {
        return magnitudeSquared() > that.magnitudeSquared();
    }

    public Vector2D getNormalVector() {
        return new Vector2D(this.y, -this.x);
    }

    public static Vector2D difference(Vector2D vector1, Vector2D vector2) {
        return new Vector2D(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY());
    }

    public static Vector2D addition(Vector2D vector1, Vector2D vector2) {
        return new Vector2D(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY());
    }

    public static double dotProduct(Vector2D vector1, Vector2D vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY());
    }

    public Vector2D unitVector() {
        return new Vector2D(getX() / magnitude(), getY() / magnitude());
    }

    public Vector2D getClosestVector(List<Vector2D> vectors) {
        Vector2D closestVector = vectors.get(0);
        double minDistance = Double.MAX_VALUE;
        for (Vector2D v : vectors) {
            double distance = difference(this, v).magnitudeSquared();
            if (distance < minDistance) {
                minDistance = distance;
                closestVector = v;
            }
        }
        return closestVector;
    }

    public void rotate(double angle) {
        double newX = (this.x * Math.cos(angle)) - (this.y * Math.sin(angle));
        double newY = (this.x * Math.sin(angle)) + (this.y * Math.cos(angle));
        this.x = newX;
        this.y = newY;
    }
}
