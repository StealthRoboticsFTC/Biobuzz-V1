package org.firstinspires.ftc.teamcode.common.command;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Robot;

public class ShootElements implements Command{
    private int stage = 0;

    ElapsedTime elapsedTime = new ElapsedTime();


    @Override
    public void update(Robot robot) {
        if (stage == 0){
            robot.intake.turnOnIntake();
            robot.transfer.open();
            increment();
        } else if (stage == 1 && (elapsedTime.milliseconds() > 1000 || robot.sensors.noBalls())) {
            robot.transfer.close();
            Robot.allBalls = false;
            increment();
        }
    }

    @Override
    public boolean isDone() {
        return stage == 2;
    }


    private void increment() {
        stage++;
        elapsedTime.reset();
    }
}
