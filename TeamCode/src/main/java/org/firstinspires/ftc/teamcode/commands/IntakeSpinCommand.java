package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSpinCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private boolean isForward;
    public IntakeSpinCommand(IntakeSubsystem sub, boolean isForward){
        subsystem = sub;
        this.isForward = isForward;
    }

    @Override
    public void initialize(){
        subsystem.spin(isForward);
    }
    public void end(boolean interrupted){
        subsystem.stopSpin();
    }
}
