package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;


import org.firstinspires.ftc.teamcode.subsystems.TestSubsystem;

import java.util.function.DoubleSupplier;

public class TestCommand extends CommandBase {
    private final TestSubsystem mecDrive;
    private final DoubleSupplier m_strafe, m_forward, m_turn;
    private double multiplier;


    public TestCommand(TestSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double mult){
        mecDrive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = mult;

        addRequirements(subsystem);
    }
    public TestCommand(TestSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn){
        mecDrive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = 1.0;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        mecDrive.drive(m_strafe.getAsDouble() * 0.9 * multiplier,
                m_forward.getAsDouble() * 0.9 * multiplier,
                m_turn.getAsDouble() * -0.88 * multiplier);
    }
}