package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Configurable
public class IntakeTest extends LinearOpMode {
    DcMotorEx intake;
    DcMotorEx transfer;
    public static double intakePower = 0;
    public static double transferPower = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
        transfer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transfer.setDirection(DcMotorSimple.Direction.REVERSE);
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        waitForStart();
        while (!isStopRequested()){
            intake.setPower(intakePower);
            transfer.setPower(transferPower);
        }

    }



}
