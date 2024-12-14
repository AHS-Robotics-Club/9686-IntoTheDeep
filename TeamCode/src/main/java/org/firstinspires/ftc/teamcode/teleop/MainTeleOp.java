package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeAngleCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeBucketCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeSpinCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeArmCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeBucketCommand;
import org.firstinspires.ftc.teamcode.commands.TestCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TestSubsystem;


@TeleOp(name = "Pasha is soo pookie")
public class MainTeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;

    private TestSubsystem system;

    private TestCommand command;
    private GamepadEx gPad1, gPad2;

    private CRServo angler1, angler2, bucket;
    private CRServo arm, outtakeBucket;
    private Motor spinner;
    private Motor slides;
    private IntakeSubsystem intakeS;

    private IntakeAngleCommand intakeAngleUp, intakeAngleDown;

    private IntakeBucketCommand intakeBucketOn, intakeBucketOff;

    private  OuttakeSubsystem outtakeS;

    private OuttakeArmCommand armCommandUp, armCommandDown;

    private  OuttakeBucketCommand outtakeBucketOn, outtakeBucketOff;




    private final double DRIVE_MULT = 1.0;
    private final double SLOW_MULT = 0.5;
    @Override
    public void initialize() {
        frontLeft = new Motor(hardwareMap, "fL");
        frontRight = new Motor(hardwareMap, "fR");
        backLeft = new Motor(hardwareMap, "bL");
        backRight = new Motor(hardwareMap, "bR");
        spinner = new Motor(hardwareMap,"spinner");
        slides = new Motor(hardwareMap, "slides");
        //intakeSlides = new Motor(hardwareMap,"intakeSlides");
        angler1 = hardwareMap.get(CRServo.class,"angler1");
        angler2 = hardwareMap.get(CRServo.class,"angler2");
        bucket = hardwareMap.get(CRServo.class,"bucket");
        //arm = hardwareMap.get(CRServo.class,"arm");
        //outtakeBucket = hardwareMap.get(CRServo.class,"outtakeBucket");
        gPad1 = new GamepadEx(gamepad1);
        //gPad2 = new GamepadEx(gamepad2);

        angler1.setDirection(CRServo.Direction.FORWARD);
        angler2.setDirection(CRServo.Direction.REVERSE);
        bucket.setDirection(CRServo.Direction.FORWARD);



        //arm.setDirection(CRServo.Direction.FORWARD);
        //outtakeBucket.setDirection(CRServo.Direction.FORWARD);



        frontLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        frontRight.motor.setDirection(DcMotor.Direction.REVERSE);
        backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        backRight.motor.setDirection(DcMotor.Direction.REVERSE);
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

        intakeS = new IntakeSubsystem(angler1,angler2,bucket,spinner);
        intakeAngleUp = new IntakeAngleCommand(intakeS,true);
        intakeAngleDown = new IntakeAngleCommand(intakeS,false);
        intakeBucketOn = new IntakeBucketCommand(intakeS, true);
        intakeBucketOff = new IntakeBucketCommand(intakeS, false);

        //outtakeS = new OuttakeSubsystem(arm,outtakeBucket);
        //armCommandUp = new OuttakeArmCommand(outtakeS, true);
        //armCommandDown = new OuttakeArmCommand(outtakeS, false);
        //outtakeBucketOn = new OuttakeBucketCommand(outtakeS, true);
        //outtakeBucketOff = new OuttakeBucketCommand(outtakeS, false);

        system = new TestSubsystem(frontLeft, frontRight, backLeft, backRight);

        command = new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,DRIVE_MULT);



        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,SLOW_MULT));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(intakeAngleUp);
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(intakeAngleDown);
        gPad1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenHeld(new IntakeSpinCommand(intakeS,true));
        gPad1.getGamepadButton(GamepadKeys.Button.X).whenHeld(new IntakeSpinCommand(intakeS,false));
        gPad1.getGamepadButton(GamepadKeys.Button.A).whenHeld(intakeBucketOn);
        gPad1.getGamepadButton(GamepadKeys.Button.B).whenHeld(intakeBucketOff);
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(new StartEndCommand(() -> slides.set(0.35), () -> slides.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(new StartEndCommand(() -> slides.set(-0.35), () -> slides.stopMotor()));
        //gPad2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(armCommandUp);
        //Pad2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(armCommandDown);
        //gPad2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(outtakeBucketOn);
        //gPad2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(outtakeBucketOff);

        register(system);
        system.setDefaultCommand(command);
    }
}
