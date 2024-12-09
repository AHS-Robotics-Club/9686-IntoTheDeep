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
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.ClawCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.TestCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TestSubsystem;


@TeleOp(name = "Pasha is soo pookie")
public class MainTeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;

    private Motor slides;
    private Motor intakeSlides;

    private TestSubsystem system;

    private TestCommand command;


    private GamepadEx gPad1;

    private SimpleServo claw;
    private SimpleServo intakeAngler;
    private CRServo intakeSpinner;

    private ClawSubsystem clawSub;
    private ClawCommand clawCom;

    private IntakeSubsystem intakeSub;
    private IntakeCommand intakeCom;

    private final double DRIVE_MULT = 1.0;
    private final double SLOW_MULT = 0.5;
    @Override
    public void initialize() {
        frontLeft = new Motor(hardwareMap, "fL");
        frontRight = new Motor(hardwareMap, "fR");
        backLeft = new Motor(hardwareMap, "bL");
        backRight = new Motor(hardwareMap, "bR");
        slides = new Motor(hardwareMap,"slides");
        //intakeSlides = new Motor(hardwareMap,"intakeSlides");

        gPad1 = new GamepadEx(gamepad1);

        //claw = new SimpleServo(hardwareMap, "claw", -180, 30);
        //clawSub = new ClawSubsystem(claw);
        //clawCom = new ClawCommand(clawSub, 40, -6);

        intakeAngler = new SimpleServo(hardwareMap, "angler",-180,30);
        intakeSpinner = hardwareMap.get(CRServo.class,"intakeSpinner");
        intakeSpinner.setDirection(CRServo.Direction.FORWARD);
        intakeSub = new IntakeSubsystem(intakeAngler,intakeSpinner);
        intakeCom = new IntakeCommand(intakeSub,-102,-10);


        //frontLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        //backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slides.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //intakeSlides.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


        frontLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slides.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //intakeSlides.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        system = new TestSubsystem(frontLeft, frontRight, backLeft, backRight);

        command = new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,DRIVE_MULT);

        gPad1.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(() -> intakeAngler.turnToAngle(-56)));
        gPad1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(intakeCom);

        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new StartEndCommand(() -> slides.set(0.35), () -> slides.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new StartEndCommand(() -> slides.set(-0.35), () -> slides.stopMotor()));
        //gPad1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(new StartEndCommand(() -> intakeSlides.set(0.35), () -> intakeSlides.stopMotor()));
        //gPad1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(new StartEndCommand(() -> intakeSlides.set(-0.35), () -> intakeSlides.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new TestCommand(system, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX,SLOW_MULT));
        gPad1.getGamepadButton(GamepadKeys.Button.A).whenHeld(new StartEndCommand(() -> intakeSpinner.setPower(-1), () -> intakeSpinner.setPower(0)));
        register(system);
        system.setDefaultCommand(command);
    }
}
