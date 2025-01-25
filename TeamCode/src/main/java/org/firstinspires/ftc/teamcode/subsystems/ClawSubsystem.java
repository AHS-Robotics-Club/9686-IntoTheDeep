package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class ClawSubsystem extends SubsystemBase {
    private SimpleServo servo;

    public ClawSubsystem(SimpleServo servo) {
        this.servo = servo;
    }

    public void clawEngage(boolean isOn) {
        if(isOn) {
            servo.turnToAngle(360);
        } else {
            servo.turnToAngle(0);
        }
    }
}