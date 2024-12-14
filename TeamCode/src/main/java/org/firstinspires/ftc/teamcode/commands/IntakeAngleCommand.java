package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeAngleCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private boolean isUp;
    public IntakeAngleCommand(IntakeSubsystem sub, boolean isUp){
        subsystem = sub;
    }

    @Override
    public void initialize(){
        subsystem.angler(isUp);
    }
    public void end(boolean interrupted){
        subsystem.angleStop();
    }
}
