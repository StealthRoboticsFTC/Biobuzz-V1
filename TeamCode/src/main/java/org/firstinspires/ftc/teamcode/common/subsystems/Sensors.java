package org.firstinspires.ftc.teamcode.common.subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    private final DigitalChannel sensor1;
    private final DigitalChannel sensor2;
    private final DigitalChannel sensor3;
    private final DigitalChannel sensor4;

    public Sensors(HardwareMap hardwareMap){
        sensor1 = hardwareMap.get(DigitalChannel.class, "sensor1");
        sensor2 = hardwareMap.get(DigitalChannel.class, "sensor2");
        sensor3 = hardwareMap.get(DigitalChannel.class, "sensor3");
        sensor4 = hardwareMap.get(DigitalChannel.class, "sensor4");
    }

    public boolean allBalls(){
        boolean ball1 = sensor1.getState();
        boolean ball2 = sensor2.getState();
        boolean ball3 = sensor3.getState();
        boolean ball4 = sensor4.getState();

        return ball1 & ball2 & ball3 & ball4;
    }

    public boolean noBalls(){
        boolean ball1 = sensor1.getState();
        boolean ball2 = sensor2.getState();
        boolean ball3 = sensor3.getState();
        boolean ball4 = sensor4.getState();

        return !ball1 && !ball2 & !ball3 & !ball4;
    }
}
