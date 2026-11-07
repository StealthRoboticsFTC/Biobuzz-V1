package org.firstinspires.ftc.teamcode.common.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Flower {
    private final Servo flower;

    private static final double up = 0.5;
    private static final double down = 0.9;
    private static final double block = 0.4;

    public Flower(HardwareMap hardwareMap) {
        flower = hardwareMap.get(Servo.class, "flower");
    }

    public void up(){
        flower.setPosition(up);
    }
    public void down(){
        flower.setPosition(down);
    }
    public void block(){
        flower.setPosition(block);
    }
}
