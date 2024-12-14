package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class OuttakeSubsystem extends SubsystemBase {
    private CRServo arm, bucket;

    public  OuttakeSubsystem(CRServo arm, CRServo bucket) {
        this.arm = arm;
        this.bucket = bucket;
    }

    public void angler(boolean isUp) {
        if(isUp) {
            arm.setPower(1);
            bucket.setPower(-0.5);
        } else{
            arm.setPower(-1);
            bucket.setPower(0.5);
        }

    }

    public void angleStop() {
        arm.setPower(0);
        bucket.setPower(0);
    }

    public void bucket(boolean isOn) {
        if(isOn) {
            bucket.setPower(-1);
        } else {
            bucket.setPower(1);
        }

    }

    public void bucketOff() {
        bucket.setPower(0);
    }

}
