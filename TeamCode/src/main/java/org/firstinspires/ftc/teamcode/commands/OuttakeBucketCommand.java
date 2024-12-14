package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSubsystem;

public class OuttakeBucketCommand extends CommandBase {
    private OuttakeSubsystem subsystem;
    private boolean isOn;
    public OuttakeBucketCommand(OuttakeSubsystem sub, boolean isOn){
        subsystem = sub;
        this.isOn = isOn;
    }

    @Override
    public void initialize(){
        subsystem.bucket(isOn);
    }
    public void end(boolean interrupted){
        subsystem.bucketOff();
    }
}
