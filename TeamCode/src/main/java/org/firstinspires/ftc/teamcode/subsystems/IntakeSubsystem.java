package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.CRServo;
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
            angler1.setPower(1);
            angler2.setPower(1);
            //bucket.turnToAngle(-90);
        } else {
            angler1.setPower(-1);
            angler2.setPower(-1);
            //bucket.turnToAngle(0);
        }
    }

    public void anglerStop() {
        angler1.setPower(0);
        angler2.setPower(0);
    }

    public void bucket(boolean isOn) {
        if(isOn) {
            bucket.setPower(1);
        } else {
            bucket.setPower(-1);
        }
    }
    public void bucketStop() {
        bucket.setPower(0);
    }

    public void spin(boolean isForward) {
        if(isForward) {
            spinner.set(1);
        } else {
            spinner.set(-0.5);
        }

    }

    public void stopSpin() {
        spinner.set(0);
    }
}