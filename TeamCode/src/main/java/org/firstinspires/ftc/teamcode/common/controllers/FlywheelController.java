package org.firstinspires.ftc.teamcode.common.controllers;

public class FlywheelController {
    // Tuned coefficients (kV is your Feedforward)
    private double kP = 0;
    private double kD = 0;
    private double kV = 0; // Power per tick/sec (approx 1.0 / maxVelocity)

    private double targetVelocity = 0;
    private double lastError = 0;
    private long lastTime = 0;

    public void setCoefficients(double kP, double kD, double kV){
        this.kP = kP;
        this.kD = kD;
        this.kV = kV;

    }

    public void setTargetVelocity(double velocity) {
        this.targetVelocity = velocity;
    }

    public double update(double currentVelocity) {
        long currentTime = System.nanoTime();
        double dt = (currentTime - lastTime) / 1e9; // Convert to seconds
        if (dt <= 0) return 0;

        double error = targetVelocity - currentVelocity;

        // 1. STATE-SWITCHING LOGIC
        // If we are way below speed (e.g., just started or just fired), blast the motor
        if (error > targetVelocity * 0.10) {
            lastTime = currentTime;
            lastError = error;
            return 1.0; // 100% full power recovery
        }

        // 2. CUSTOM CLOSED-LOOP CONTROL
        // Feedforward predicts the baseline voltage needed for this RPM
        double feedforward = targetVelocity * kV;

        // Proportional fixes minor steady-state errors
        double proportional = error * kP;

        // Derivative dampens overshoot as we settle into the target window
        double derivative = ((error - lastError) / dt) * kD;

        // Save states for next loop iteration
        lastError = error;
        lastTime = currentTime;

        // Clip the total output between 0 and 1.0 (flywheels never need reverse power)
        double totalPower = feedforward + proportional + derivative;
        return Math.max(0.0, Math.min(1.0, totalPower));
    }
}
