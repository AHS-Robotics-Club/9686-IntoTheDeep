package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo bucket, angler1, angler2;
    private Motor spinner;

    public IntakeSubsystem(CRServo bucket, CRServo angler1, CRServo angler2, Motor spinner) {
        this.angler1 = angler1;
        this.angler2 = angler2;
        this.bucket = bucket;
        this.spinner = spinner;
    }

    public void anglerEngage(boolean isUp) {
        if(isUp) {
            angler1.set(1);
            angler2.set(1);
            //bucket.turnToAngle(-90);
        } else {
            angler1.set(-1);
            angler2.set(-1);
            //bucket.turnToAngle(0);
        }
    }

    public void anglerStop() {
        angler1.set(0);
        angler2.set(0);
    }

    public void bucket(boolean isOn) {
        if(isOn) {
            bucket.set(1);
        } else {
            bucket.set(-1);
        }
    }
    public void bucketStop() {
        bucket.set(0);
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