package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Processor;
import org.firstinspires.ftc.teamcode.common.Robot;
import org.firstinspires.ftc.teamcode.common.command.IntakeElements;

@Autonomous
public class ParkAuto extends LinearOpMode {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(15, 134, 0);
    private final Pose path1 = poseFactory.of(15, 120, 0);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }
    private Robot robot;

    private Processor processor;

    private ElapsedTime elapsedTime;

    private int stage = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        processor = new Processor();
        elapsedTime = new ElapsedTime();
        robot.transfer.close();
        robot.flower.up();


        waitForStart();
        robot.follower.setPose(start);

        while (!isStopRequested()) {
            if (stage == 0) {
                robot.follower.follow(path1());
                stage++;
            }
            robot.update();
            processor.update(robot);
            telemetry.update();
        }

    }
}
