package com.review;

import java.io.IOException;

public class CircleWithException {
    private double radius;
    private static int numberOfObjects;

    public CircleWithException(){
        this(1.0);
    }
    public CircleWithException(double radius){
        setRadius(radius);
        numberOfObjects++;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius)throws IOException {
        if (radius >=0)
            this.radius = radius;
        else
            throw new IOException("Radius cannot be negative");
    }

    public static int getNumberOfObjects() {
        return numberOfObjects;
    }

    public double getArea(){
        return radius * radius * 3.14;
    }
}

class CircleMain{
    public static void main(String[] args) {
        try {
            CircleWithException c1 = new CircleWithException(2);
            CircleWithException c2 = new CircleWithException(-4);
            CircleWithException c3 = new CircleWithException();
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
        System.out.println("Number of Objects created: "+ CircleWithException.getNumberOfObjects());
    }

}