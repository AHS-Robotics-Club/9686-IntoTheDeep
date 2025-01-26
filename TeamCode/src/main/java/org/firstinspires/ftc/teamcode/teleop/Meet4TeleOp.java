package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeBucketCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeEngageCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeSpinnerCommand;
import org.firstinspires.ftc.teamcode.commands.TestCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TestSubsystem;

@TeleOp(name = "Pasha is soo pookie")
public class Meet4TeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private TestSubsystem system;
    private TestCommand command;
    private GamepadEx gPad1;
    private GamepadEx gPad2;

    private CRServo angler1, angler2, bucket;
    private SimpleServo clawServo;
    private Motor spinner;
    private Motor slidesI, slidesC;
    private IntakeSubsystem intakeS;
    private ClawSubsystem clawS;

    private IntakeBucketCommand bucketOn, bucketOff;
    private IntakeEngageCommand engageUp, engageDown;
    private IntakeSpinnerCommand spin, backSpin;

    private ClawCommand clawOn, clawOff;

    private final double DRIVE_MULT = 1.0;
    private final double SLOW_MULT = 0.5;

    public void initialize() {
        frontLeft = new Motor(hardwareMap, "fL");
        frontRight = new Motor(hardwareMap, "fR");
        backLeft = new Motor(hardwareMap, "bL");
        backRight = new Motor(hardwareMap, "bR");
        angler1 = hardwareMap.crservo.get("angler1");
        angler2 =  hardwareMap.crservo.get("angler2");
        bucket =  hardwareMap.crservo.get("bucket");
        spinner = new Motor(hardwareMap, "spinner");
        slidesI = new Motor(hardwareMap,"slidesI");
        slidesC = new Motor(hardwareMap, "slidesC");
        clawServo = new SimpleServo(hardwareMap,"claw",0,360);


        gPad1 = new GamepadEx(gamepad1);
        gPad2 = new GamepadEx(gamepad2);

        frontLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        frontRight.motor.setDirection(DcMotor.Direction.REVERSE);
        backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        backRight.motor.setDirection(DcMotor.Direction.REVERSE);
        angler2.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        spinner.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slidesI.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slidesC.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinner.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slidesI.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slidesC.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        system = new TestSubsystem(frontLeft, frontRight, backLeft, backRight);
        intakeS = new IntakeSubsystem(bucket,angler1,angler2,spinner);
        clawS = new ClawSubsystem(clawServo);
        bucketOn = new IntakeBucketCommand(intakeS,true);
        bucketOff = new IntakeBucketCommand(intakeS,false);
        engageUp = new IntakeEngageCommand(intakeS,true);
        engageDown = new IntakeEngageCommand(intakeS,false);
        spin = new IntakeSpinnerCommand(intakeS,true);
        backSpin = new IntakeSpinnerCommand(intakeS,false);
        clawOn = new ClawCommand(clawS, true);
        clawOff = new ClawCommand(clawS, false);

        command = new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,DRIVE_MULT,false);

        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,SLOW_MULT,false));
        gPad2.getGamepadButton(GamepadKeys.Button.A).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,DRIVE_MULT,true));
        gPad2.getGamepadButton(GamepadKeys.Button.B).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,SLOW_MULT,true));
        gPad2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(bucketOn);
        gPad2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(bucketOff);

        gPad2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(engageUp);
        gPad2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(engageDown);
        gPad2.getGamepadButton(GamepadKeys.Button.X).whenHeld(clawOn);
        gPad2.getGamepadButton(GamepadKeys.Button.Y).whenHeld(clawOff);
       // gPad1.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(executeDown,executeUp);
        gPad2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenHeld(spin);
        gPad2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(backSpin);
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(new StartEndCommand(() -> slidesI.set(0.35), () -> slidesI.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(new StartEndCommand(() -> slidesI.set(-0.35), () -> slidesI.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new StartEndCommand(() -> slidesC.set(0.35), () -> slidesC.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new StartEndCommand(() -> slidesC.set(-0.35), () -> slidesC.stopMotor()));


        register(system);
        system.setDefaultCommand(command);
    }

}
