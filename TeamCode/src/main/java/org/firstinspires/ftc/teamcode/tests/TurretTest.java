package org.firstinspires.ftc.teamcode.tests;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Configurable
public class TurretTest extends LinearOpMode {
     Servo turret1;
     Servo turret2;
    public static double turretPos = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        turret1 = hardwareMap.get(Servo.class, "servo1");
        turret1.setDirection(Servo.Direction.REVERSE);
        turret2 = hardwareMap.get(Servo.class, "servo2");
        turret2.setDirection(Servo.Direction.FORWARD);
        waitForStart();
        while (!isStopRequested()){
            turret1.setPosition(turretPos);
            turret2.setPosition(turretPos);
        }
    }
}
