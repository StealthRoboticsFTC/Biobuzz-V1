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
import org.firstinspires.ftc.teamcode.common.command.ShootElements;

@Autonomous
public class Auto extends LinearOpMode {
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose start = poseFactory.of(55, 8, 270);
    private final Pose path1 = poseFactory.of(55, 10, 270);
    private final Pose point2 = poseFactory.of(10, 2, 180);
    private final Pose point2Control1 = poseFactory.of(40, 0, 0);
    private final Pose point3Start = poseFactory.of(12, 2, 180);
    private final Pose point3 = poseFactory.of(48, 120, 110);
    private final Pose point3Control1 = poseFactory.of(30, 96, 0);
    private final Pose point4Start = poseFactory.of(48, 120, 110);
    private final Pose point4 = poseFactory.of(48, 129, 90);
    private final Pose point5Start = poseFactory.of(48, 129, 90);
    private final Pose point5 = poseFactory.of(48, 123, 105);
    private final Pose point6Start = poseFactory.of(48, 123, 105);
    private final Pose point6 = poseFactory.of(25, 48, 180);
    private final Pose point6Control1 = poseFactory.of(20, 80, 0);
    private final Pose point7Start = poseFactory.of(25, 48, 180);
    private final Pose point7 = poseFactory.of(15, 48, 180);
    private final Pose point8 = poseFactory.of(25, 48, 180);
    private final Pose point9Start = poseFactory.of(20, 48, 180);
    private final Pose point9 = poseFactory.of(48, 123, 105);
    private final Pose point9Control1 = poseFactory.of(20, 80, 0);
    private final Pose point10Start = poseFactory.of(48, 123, 105);
    private final Pose point10 = poseFactory.of(15, 100, 0);

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
            if (stage == 0){
                processor.override(new IntakeElements());
                robot.follower.follow(path1());
                increment();
            } else if (stage == 1 && robot.follower.atParametricEnd() && robot.shooter.atTargetVel()) {
                processor.override(new ShootElements());
                increment();
            } else if (stage == 2 && !processor.isBusy()) {
                processor.override(new IntakeElements());
                robot.follower.follow(path2());
                increment();
            } else if (stage == 3 && robot.follower.atParametricEnd()) {
                robot.follower.follow(path3());
                increment();
            } else if (stage == 4 && robot.follower.atParametricEnd()) {
                robot.flower.down();
                processor.override(new ShootElements());
                increment();
            } else if (stage == 5 && !processor.isBusy()) {
                processor.override(new IntakeElements());
                robot.follower.follow(path4());
                increment();
            } else if (stage == 6 && (robot.sensors.allBalls() || elapsedTime.milliseconds() > 3000)) {
                robot.follower.follow(path5());
                increment();
            } else if (stage == 7 && robot.follower.atParametricEnd()) {
                processor.override(new ShootElements());
                increment();
            } else if (stage == 8 && !processor.isBusy()) {
                processor.override(new IntakeElements());
                robot.follower.follow(path6());
                increment();
            } else if (stage == 9 && robot.follower.atParametricEnd()) {
                robot.follower.follow(path7());
                increment();
            } else if (stage == 10 && (robot.sensors.allBalls() || elapsedTime.milliseconds()>3000)) {
                robot.follower.follow(path8());
                increment();
            } else if (stage == 11 && robot.follower.atParametricEnd()) {
                robot.flower.up();
                robot.follower.follow(path9());
                increment();
            } else if (stage == 12 && robot.follower.atParametricEnd()) {
                processor.override(new ShootElements());
                increment();
            } else if (stage == 13 && !processor.isBusy()) {
                robot.follower.follow(path10());
                increment();
            }

            robot.update();
            processor.update(robot);
            telemetry.addData("vel", robot.shooter.getVel());
            telemetry.update();
        }
    }

    Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(path1, point2Control1, point2).constant(point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point5Start, point5).linear(point5Start, point5);
    }

    public Path path6() {
        return Paths.curve(point6Start, point6Control1, point6).linear(point6Start, point6);
    }

    public Path path7() {
        return Paths.line(point7Start, point7).linear(point7Start, point7);
    }

    public Path path8() {
        return Paths.line(point7, point8).reverseTangent();
    }

    public Path path9() {
        return Paths.curve(point9Start, point9Control1, point9).linear(point9Start, point9);
    }

    public Path path10() {
        return Paths.line(point10Start, point10).linear(point10Start, point10);
    }


    private void increment(){
        stage++;
        elapsedTime.reset();

    }

}

