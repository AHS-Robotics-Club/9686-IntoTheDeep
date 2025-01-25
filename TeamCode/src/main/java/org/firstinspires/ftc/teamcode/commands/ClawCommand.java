package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class ClawCommand extends CommandBase {
    private ClawSubsystem subsystem;
    private boolean isOn;
    public ClawCommand(ClawSubsystem sub, boolean isOn){
        subsystem = sub;
        this.isOn = isOn;
    }

    @Override
    public void initialize(){
        subsystem.clawEngage(isOn);
    }
}
