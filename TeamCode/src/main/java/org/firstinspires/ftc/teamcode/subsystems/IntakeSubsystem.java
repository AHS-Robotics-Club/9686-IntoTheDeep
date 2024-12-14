package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo bucket;
    private CRServo angler1, angler2;
    private Motor spinner;


    public IntakeSubsystem(CRServo angler1, CRServo angler2, CRServo bucket, Motor spinner) {
        this.angler1 = angler1;
        this.angler2 = angler2;
        this.bucket = bucket;
        this.spinner = spinner;
    }

    public void angler(boolean isUp) {
        if(isUp) {
            angler1.setPower(1);
            angler2.setPower(1);
        } else{
            angler1.setPower(-1);
            angler2.setPower(-1);

        }

    }

    public void angleStop() {
        angler1.setPower(0);
        angler2.setPower(0);

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

    public void bucket(boolean isOn) {
        if(isOn) {
            bucket.setPower(-.75);
        } else {
            bucket.setPower(.75);
        }

    }

    public void bucketOff() {
        bucket.setPower(0);
    }

}
