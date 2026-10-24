package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.controllers.FlywheelController;

@Configurable
@TeleOp
public class ShooterTest extends LinearOpMode {
    public static double kP = 0;
    public static double kD = 0;
    public static double kV = 0;
    DcMotorEx shooterMotor1;
    DcMotorEx shooterMotor2;
    FlywheelController flywheelController;
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
        shooterMotor2.setDirection(DcMotorSimple.Direction.FORWARD);

        flywheelController = new FlywheelController();
        flywheelController.setCoefficients(kP,kD,kV);
        waitForStart();
        while (!isStopRequested()){
            flywheelController.setTargetVelocity(targetVelocity);
            double power = flywheelController.update(shooterMotor1.getVelocity());
            shooterMotor1.setPower(power);
            shooterMotor2.setPower(power);
            telemetry.addData("velocity", shooterMotor1.getVelocity());
            telemetry.addData("power", power);
            telemetry.update();

        }
    }
}
