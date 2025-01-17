package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.commands.IntakeBucketCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeEngageCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeExecuteCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeSpinnerCommand;
import org.firstinspires.ftc.teamcode.commands.TestCommand;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TestSubsystem;

@TeleOp(name = "Pasha is soo pookie")
public class Meet4TeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private TestSubsystem system;
    private TestCommand command;
    private GamepadEx gPad1;

    private SimpleServo angler1, angler2, bucket;
    private Motor spinner;
    private Motor slides;
    private IntakeSubsystem intakeS;

    private IntakeBucketCommand bucketOn, bucketOff;
    private IntakeEngageCommand engageUp, engageDown;
    private IntakeExecuteCommand executeUp, executeDown;
    private IntakeSpinnerCommand spin, backSpin;

    private final double DRIVE_MULT = 1.0;
    private final double SLOW_MULT = 0.5;

    public void initialize() {
        frontLeft = new Motor(hardwareMap, "fL");
        frontRight = new Motor(hardwareMap, "fR");
        backLeft = new Motor(hardwareMap, "bL");
        backRight = new Motor(hardwareMap, "bR");
        angler1 = new SimpleServo(hardwareMap,"angler1",0,360);
        angler2 = new SimpleServo(hardwareMap,"angler2",0,360);
        bucket = new SimpleServo(hardwareMap,"bucket",0,360);
        spinner = new Motor(hardwareMap, "spinner");
        slides = new Motor(hardwareMap,"slides");


        gPad1 = new GamepadEx(gamepad1);

        frontLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        frontRight.motor.setDirection(DcMotor.Direction.REVERSE);
        backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        backRight.motor.setDirection(DcMotor.Direction.REVERSE);
        angler2.setInverted(true);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        spinner.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slides.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinner.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slides.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        system = new TestSubsystem(frontLeft, frontRight, backLeft, backRight);
        intakeS = new IntakeSubsystem(bucket,angler1,angler2,spinner);
        bucketOn = new IntakeBucketCommand(intakeS,true);
        bucketOff = new IntakeBucketCommand(intakeS,false);
        engageUp = new IntakeEngageCommand(intakeS,true);
        engageDown = new IntakeEngageCommand(intakeS,false);
        executeUp = new IntakeExecuteCommand(intakeS,true);
        executeDown = new IntakeExecuteCommand(intakeS, false);
        spin = new IntakeSpinnerCommand(intakeS,true);
        backSpin = new IntakeSpinnerCommand(intakeS,false);

        command = new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,DRIVE_MULT);

        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,SLOW_MULT));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).toggleWhenPressed(bucketOn,bucketOff);

        gPad1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(engageUp,engageDown);
        //gPad1.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(executeDown,executeUp);
        gPad1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenHeld(spin);
        gPad1.getGamepadButton(GamepadKeys.Button.B).whenHeld(backSpin);
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new StartEndCommand(() -> slides.set(0.35), () -> slides.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new StartEndCommand(() -> slides.set(-0.35), () -> slides.stopMotor()));

        register(system);
        system.setDefaultCommand(command);
    }

}
