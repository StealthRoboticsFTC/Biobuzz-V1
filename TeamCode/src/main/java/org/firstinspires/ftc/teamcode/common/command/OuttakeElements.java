package org.firstinspires.ftc.teamcode.common.command;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Robot;

public class OuttakeElements implements Command{
    private int stage = 0;
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void update(Robot robot) {
        if (stage == 0){
            robot.transfer.close();
            robot.intake.reverseIntake();
            Robot.allBalls = false;
            increment();
        } else if (stage == 1 && elapsedTime.milliseconds()>500) {
            robot.intake.turnOnIntake();
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
