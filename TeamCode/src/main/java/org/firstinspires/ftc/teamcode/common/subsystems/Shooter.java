package org.firstinspires.ftc.teamcode.common.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.common.controllers.PIDFCoefficients;
import org.firstinspires.ftc.teamcode.common.controllers.PIDFController;

public class Shooter {
    private final DcMotorEx shooter1;
    private final DcMotorEx shooter2;
    private final PIDFController controller;

    private final static double kp = 0.005;
    private final static double ki = 0;
    private final static double kd = 0;
    private final static double kf = 0.0005;

    private final static int shoot = 840;
    private final static int off = 0;

    private int targetVel = 840;

    public Shooter(HardwareMap hardwareMap){
        shooter1 = hardwareMap.get(DcMotorEx.class, "shooter1");
        shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter1.setDirection(DcMotorSimple.Direction.FORWARD);

        shooter2 = hardwareMap.get(DcMotorEx.class, "shooter2");
        shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);


        PIDFCoefficients coefficients = new PIDFCoefficients(kp, ki, kd, kf);
        controller = new PIDFController(coefficients);
    }

    public void setTargetVel(int vel){
        targetVel = vel;
    }

    public void update(){
        controller.setTargetPosition(targetVel);
        controller.updateFeedForwardInput(controller.getTargetPosition());
        controller.updatePosition(shooter1.getVelocity());
        shooter1.setPower(controller.run());
        shooter2.setPower(controller.run());
    }
    public boolean atTargetVel(){
        return shooter1.getVelocity() > controller.getTargetPosition() - 20 && shooter1.getVelocity() < controller.getTargetPosition() + 20;
    }
    public double getVel(){
        return shooter1.getVelocity();
    }
}
