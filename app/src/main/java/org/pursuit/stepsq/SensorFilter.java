package org.pursuit.stepsq;

public class SensorFilter {

    private SensorFilter() {
    }
    public static double sum(double[] array) {
        double currentValue = 0;
        for (double anArray : array) {
            currentValue += anArray;
        }
        return currentValue;
    }

    public static double[] cross(double[] arrayA, double[] arrayB) {
        double[] retArray = new double[3];
        retArray[0] = arrayA[1] * arrayB[2] - arrayA[2] * arrayB[1];
        retArray[1] = arrayA[2] * arrayB[0] - arrayA[0] * arrayB[2];
        retArray[2] = arrayA[0] * arrayB[1] - arrayA[1] * arrayB[0];
        return retArray;
    }

    public static double norm(double[] array) {
        double currentValue = 0;
        for (double anArray : array) {
            currentValue += anArray * anArray;
        }
        return Math.sqrt(currentValue);
    }


    public static double dot(double[] a, double[] b) {
        double currentValue = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
        return currentValue;
    }

    public static double[] normalize(double[] a) {
        double[] currentValue = new double[a.length];
        double norm = norm(a);
        for (int i = 0; i < a.length; i++) {
            currentValue[i] = a[i] / norm;
        }
        return currentValue;
    }

}
