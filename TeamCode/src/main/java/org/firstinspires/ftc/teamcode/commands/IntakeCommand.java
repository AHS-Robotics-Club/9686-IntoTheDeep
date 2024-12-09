package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private int closeDeg, openDeg;
    public IntakeCommand(IntakeSubsystem sub, int close, int open){
        subsystem = sub;
        closeDeg = close;
        openDeg = open;
    }

    @Override
    public void initialize(){
        subsystem.angleDown(closeDeg);
    }
    public void end(boolean interrupted){
        subsystem.angleUp(openDeg);
    }
}
