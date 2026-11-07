package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Processor;
import org.firstinspires.ftc.teamcode.common.Robot;
import org.firstinspires.ftc.teamcode.common.buttons.Button;
import org.firstinspires.ftc.teamcode.common.buttons.ButtonListener;
import org.firstinspires.ftc.teamcode.common.command.IntakeElements;
import org.firstinspires.ftc.teamcode.common.command.OuttakeElements;
import org.firstinspires.ftc.teamcode.common.command.ShootElements;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class DriverControl extends LinearOpMode {
    private Robot robot;

    private boolean vibrate = true;
    private Processor processor;
    private ButtonListener listener;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        processor = new Processor();
        listener = new ButtonListener(gamepad1);


        listener.addListener(Button.L_TRIGGER_DOWN, () -> {
            robot.flower.up();
            processor.override(new IntakeElements());
        });

        listener.addListener(Button.R_TRIGGER_DOWN, () ->{
            processor.override(new ShootElements());
            vibrate = true;
        });

        listener.addListener(Button.L_BUMPER_DOWN, ()->{
            processor.override(new OuttakeElements());
            vibrate = true;
        });

        listener.addListener(Button.R_BUMPER_DOWN, ()->{
            robot.flower.down();
        });

        waitForStart();

        robot.flower.up();
        processor.override(new IntakeElements());

        while (!isStopRequested()){
            if (Robot.allBalls && vibrate && processor.getCommand() instanceof IntakeElements) {
                gamepad1.rumble(1000);
                vibrate = false;
            }
            if (processor.getLastExecuted() instanceof ShootElements && !processor.isBusy()){
                gamepad1.rumble(500);
                processor.override(new IntakeElements());
            }



            robot.follower.manual(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
            telemetry.addData("vel", robot.shooter.getVel());
            telemetry.update();

            robot.update();
            listener.update();
            processor.update(robot);
        }
    }
}
