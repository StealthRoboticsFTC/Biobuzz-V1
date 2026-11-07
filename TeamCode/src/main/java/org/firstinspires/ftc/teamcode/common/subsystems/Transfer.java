package org.firstinspires.ftc.teamcode.common.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Transfer {
    private final Servo transfer;

    private static final double open = 0.19;
    private static final double close = 0.3;

    public Transfer(HardwareMap hardwareMap) {
        transfer = hardwareMap.get(Servo.class, "transferServo");
    }

    public void open(){
        transfer.setPosition(open);
    }
    public void close(){
        transfer.setPosition(close);
    }
}
