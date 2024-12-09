package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.CRServo;

public class IntakeSubsystem extends SubsystemBase {
    private SimpleServo angler;
    private CRServo spinner;


    public IntakeSubsystem(SimpleServo angler, CRServo spinner) {
        this.angler = angler;
        this.spinner = spinner;
    }

    public void angleDown(int deg) {
        angler.turnToAngle(deg);
        spinner.setPower(1);
    }

    public void angleUp(int deg) {
        angler.turnToAngle(deg);
        spinner.setPower(0);
    }
}
