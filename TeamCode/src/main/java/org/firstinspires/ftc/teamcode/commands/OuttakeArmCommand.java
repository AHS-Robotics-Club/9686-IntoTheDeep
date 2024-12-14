package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSubsystem;

public class OuttakeArmCommand extends CommandBase {
    private OuttakeSubsystem subsystem;
    private boolean isUp;
    public OuttakeArmCommand(OuttakeSubsystem sub, boolean isUp){
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
