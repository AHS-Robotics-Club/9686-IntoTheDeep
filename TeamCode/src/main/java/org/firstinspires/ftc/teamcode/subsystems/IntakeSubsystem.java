package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeSubsystem extends SubsystemBase {
    private SimpleServo bucket, angler1, angler2;
    private Motor spinner;

    public IntakeSubsystem(SimpleServo bucket, SimpleServo angler1, SimpleServo angler2, Motor spinner) {
        this.angler1 = angler1;
        this.angler2 = angler2;
        this.bucket = bucket;
        this.spinner = spinner;
    }

    public void anglerEngage(boolean isUp) {
        if(isUp) {
            angler1.rotateByAngle(90);
            angler2.rotateByAngle(90);
            bucket.rotateByAngle(-90);
        } else {
            angler1.rotateByAngle(-90);
            angler2.rotateByAngle(-90);
            bucket.rotateByAngle(90);
        }
    }

    public void anglerExecute(boolean isUp) {
        if(isUp) {
            angler1.rotateByAngle(30);
            angler2.rotateByAngle(30);
        } else {
            angler1.rotateByAngle(-30);
            angler2.rotateByAngle(-30);
        }
    }

    public void bucket(boolean isOn) {
        if(isOn) {
            bucket.rotateByAngle(-20);
        } else {
            bucket.rotateByAngle(20);
        }
    }

    public void spin(boolean isForward) {
        if(isForward) {
            spinner.set(1);
        } else {
            spinner.set(-1);
        }

    }

    public void stopSpin() {
        spinner.set(0);
    }
}
