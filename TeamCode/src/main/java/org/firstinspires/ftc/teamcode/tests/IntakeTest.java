package org.firstinspires.ftc.teamcode.tests;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Configurable

public class IntakeTest extends LinearOpMode {
    DcMotorEx intake;
    DcMotorEx transfer;
    Servo flower;

    DigitalChannel sensor1;
    DigitalChannel sensor2;
    DigitalChannel sensor3;
    DigitalChannel sensor4;

    public static double intakePower = 0;
    public static double transferPower = 0;

    public static double flowerPos = 0.5;


    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
        transfer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transfer.setDirection(DcMotorSimple.Direction.FORWARD);
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flower = hardwareMap.get(Servo.class, "flower");

        sensor1 = hardwareMap.get(DigitalChannel.class, "sensor1");
        sensor2 = hardwareMap.get(DigitalChannel.class, "sensor2");
        sensor3 = hardwareMap.get(DigitalChannel.class, "sensor3");
        sensor4 = hardwareMap.get(DigitalChannel.class, "sensor4");

        waitForStart();
        while (!isStopRequested()){
            intake.setPower(intakePower);
            transfer.setPower(transferPower);
            flower.setPosition(flowerPos);


            telemetry.addData("ball1", sensor1.getState());
            telemetry.addData("ball2", sensor2.getState());;
            telemetry.addData("ball3", sensor3.getState());;
            telemetry.addData("ball4", sensor4.getState());;
            telemetry.update();
        }

    }



}
