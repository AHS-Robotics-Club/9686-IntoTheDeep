package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeEngageCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private boolean isUp;
    public IntakeEngageCommand(IntakeSubsystem sub, boolean isUp){
        subsystem = sub;
        this.isUp = isUp;
    }

    @Override
    public void initialize(){
        subsystem.anglerEngage(isUp);
    }
    public void end(boolean interrupted){
        subsystem.anglerStop();
    }
}
