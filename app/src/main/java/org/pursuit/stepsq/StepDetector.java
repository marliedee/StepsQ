package org.pursuit.stepsq;

public class StepDetector {

    private static final int ACCEL_RING_SIZE = 50;
    private static final int VEL_RING_SIZE = 10;

    // change this threshold according to your sensitivity preferences
    private static final double STEP_THRESHOLD = 10.00;
    private static final int STEP_DELAY_NS = 250000000;

    private int accelRingCounter = 0;
    private double[] accelRingX = new double[ACCEL_RING_SIZE];
    private double[] accelRingY = new double[ACCEL_RING_SIZE];
    private double[] accelRingZ = new double[ACCEL_RING_SIZE];
    private int velRingCounter = 0;
    private double[] velRing = new double[VEL_RING_SIZE];
    private long lastStepTimeNs = 0;
    private double oldVelocityEstimate = 0;

    private StepListener listener;

    public void registerListener(StepListener listener) {
        this.listener = listener;
    }


    public void updateAccel(long timeNs, double x, double y, double z) {
        double[] currentAccel = new double[3];
        currentAccel[0] = x;
        currentAccel[1] = y;
        currentAccel[2] = z;

        // First step is to update our guess of where the global z vector is.
        accelRingCounter++;
        accelRingX[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[0];
        accelRingY[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[1];
        accelRingZ[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[2];

        double[] updateSteps = new double[3];
        updateSteps[0] = SensorFilter.sum(accelRingX) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        updateSteps[1] = SensorFilter.sum(accelRingY) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        updateSteps[2] = SensorFilter.sum(accelRingZ) / Math.min(accelRingCounter, ACCEL_RING_SIZE);

        double normalization_factor = SensorFilter.norm(updateSteps);

        updateSteps[0] = updateSteps[0] / normalization_factor;
        updateSteps[1] = updateSteps[1] / normalization_factor;
        updateSteps[2] = updateSteps[2] / normalization_factor;

        double currentZ = SensorFilter.dot(updateSteps, currentAccel) - normalization_factor;
        velRingCounter++;
        velRing[velRingCounter % VEL_RING_SIZE] = currentZ;

        double velocityEstimate = SensorFilter.sum(velRing);

        if (velocityEstimate > STEP_THRESHOLD && oldVelocityEstimate <= STEP_THRESHOLD
                && (timeNs - lastStepTimeNs > STEP_DELAY_NS)) {
            listener.step(timeNs);
            lastStepTimeNs = timeNs;
        }
        oldVelocityEstimate = velocityEstimate;
    }
}
