package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeExecuteCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private boolean isUp;
    public IntakeExecuteCommand(IntakeSubsystem sub, boolean isUp){
        subsystem = sub;
        this.isUp = isUp;
    }

    @Override
    public void initialize(){
        subsystem.anglerExecute(isUp);
    }
}
