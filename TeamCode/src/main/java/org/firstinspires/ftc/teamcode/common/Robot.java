package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.common.subsystems.Flower;
import org.firstinspires.ftc.teamcode.common.subsystems.Intake;
import org.firstinspires.ftc.teamcode.common.subsystems.Sensors;
import org.firstinspires.ftc.teamcode.common.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.common.subsystems.Transfer;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class Robot {
    public Follower follower;


    public Intake intake;
    public Transfer transfer;
    public Shooter shooter;
    public Sensors sensors;

    public Flower flower;

    public static boolean allBalls = false;


    public Robot(HardwareMap hardwareMap){
        follower = Constants.create(hardwareMap);
        flower = new Flower(hardwareMap);

        intake = new Intake(hardwareMap);
        transfer = new Transfer(hardwareMap);
        shooter = new Shooter(hardwareMap);
        sensors = new Sensors(hardwareMap);



    }
    public void update(){
        follower.update();
        shooter.update();



    }
}
