package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class TestSubsystem extends SubsystemBase {
    private com.arcrobotics.ftclib.drivebase.MecanumDrive mecanumDrive;
    private Motor frontLeft, frontRight, backLeft, backRight;

    public TestSubsystem(Motor fL, Motor fR, Motor bL, Motor bR){
        frontLeft = fL;
        frontRight = fR;
        backLeft = bL;
        backRight = bR;

        mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {

        mecanumDrive.driveRobotCentric(strafeSpeed, forwardSpeed, -turnSpeed, true);
    }

}