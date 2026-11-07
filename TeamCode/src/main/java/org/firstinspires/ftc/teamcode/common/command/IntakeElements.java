package org.firstinspires.ftc.teamcode.common.command;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Robot;

public class IntakeElements implements Command{
    private int stage = 0;
    ElapsedTime elapsedTime = new ElapsedTime();

    Boolean had4balls = false;

    Boolean confirmedAllBalls = false;

    @Override
    public void update(Robot robot) {
        if (stage == 0){
            robot.transfer.close();
            robot.intake.turnOnIntake();
            increment();
        } else if (stage == 1) {
            boolean allBalls = robot.sensors.allBalls();
            if (allBalls){
                if (!had4balls){
                    elapsedTime.reset();
                } else if (elapsedTime.milliseconds() > 150) {
                    confirmedAllBalls = true;
                }
            }
            if (confirmedAllBalls){
                Robot.allBalls = true;
                robot.intake.turnOffIntake();


                increment();
            }
            had4balls = allBalls;
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
