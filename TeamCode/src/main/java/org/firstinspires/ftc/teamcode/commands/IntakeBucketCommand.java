package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeBucketCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private boolean isOn;
    public IntakeBucketCommand(IntakeSubsystem sub, boolean isOn){
        subsystem = sub;
        this.isOn = isOn;
    }

    @Override
    public void initialize(){
        subsystem.bucket(isOn);
    }
}
