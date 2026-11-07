package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.controllers.PIDFCoefficients;
import org.firstinspires.ftc.teamcode.common.controllers.PIDFController;


@TeleOp
@Configurable
public class ShooterPIDFTest extends LinearOpMode {

    public static double kp = 0;
    public static double ki = 0;
    public static double kd = 0;
    public static double kf = 0;
    DcMotorEx shooterMotor1;
    DcMotorEx shooterMotor2;

    PIDFController controller;

    public static int targetVelocity = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        shooterMotor1 = hardwareMap.get(DcMotorEx.class, "shooter1");
        shooterMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterMotor2 = hardwareMap.get(DcMotorEx.class, "shooter2");
        shooterMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        controller = new PIDFController(new PIDFCoefficients(kp, ki, kd, kf));
        waitForStart();
        while (!isStopRequested()){
            controller.setD(kd);
            controller.setF(kf);
            controller.setP(kp);
            controller.setI(ki);
            controller.updateFeedForwardInput(targetVelocity);
            controller.setTargetPosition(targetVelocity);
            controller.updatePosition(shooterMotor1.getVelocity());
            shooterMotor1.setPower(controller.run());
            shooterMotor2.setPower(controller.run());

            telemetry.addData("TargetVelocity", targetVelocity);
            telemetry.addData("CurrentVelocity", shooterMotor1.getVelocity());
            telemetry.addData("Power", controller.run());
            telemetry.update();

        }
    }
}
