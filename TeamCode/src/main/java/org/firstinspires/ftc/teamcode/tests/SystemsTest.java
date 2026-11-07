package org.firstinspires.ftc.teamcode.tests;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.controllers.PIDFCoefficients;
import org.firstinspires.ftc.teamcode.common.controllers.PIDFController;

@TeleOp
@Configurable

public class SystemsTest extends LinearOpMode {
    DcMotorEx shooter1;
    DcMotorEx shooter2;
    DcMotorEx intake;
    DcMotorEx transfer;
    Servo transferServo;
    PIDFController controller;


    public static double intakePower = 0;
    public static double transferPower = 0;
    public static double shooterTargetVwl = 0;

    public static double transferServoPos = 0.5;


    @Override
    public void runOpMode() throws InterruptedException {
        shooter1 = hardwareMap.get(DcMotorEx.class, "shooter1");
        shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter1.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter2 = hardwareMap.get(DcMotorEx.class, "shooter2");
        shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
        transfer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transfer.setDirection(DcMotorSimple.Direction.FORWARD);
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        transferServo = hardwareMap.get(Servo.class, "transferServo");

        controller = new PIDFController(new PIDFCoefficients(0.0025, 0, 0, 0.0005));

        waitForStart();
        while (!isStopRequested()){
            controller.updateFeedForwardInput(shooterTargetVwl);
            controller.setTargetPosition(shooterTargetVwl);
            controller.updatePosition(shooter1.getVelocity());
            shooter1.setPower(controller.run());
            shooter1.setPower(controller.run());
            intake.setPower(intakePower);
            transfer.setPower(transferPower);
            transferServo.setPosition(transferServoPos);
            telemetry.addData("currentVel", shooter1.getVelocity());
            telemetry.update();
        }

    }



}
