package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Juan Rivera + Joshua Young on 9/16/2016.
 */
//change that needs so i can push it succesfully
public class Teleop extends OpMode {
    //motors for drivetrain
    DcMotor Right;
    DcMotor Left;
    //motors for shooting
    DcMotor Shooter;
    //motors for intake
    DcMotor Intake;
    //servo to let balls into the shooter from the intake sh
    Servo Meatball;

    @Override
    public void init() {
        //mapping out the motors
        Right = hardwareMap.dcMotor.get("motorR");
        Left = hardwareMap.dcMotor.get("motorL");
        Shooter = hardwareMap.dcMotor.get("MotorS");
        Intake = hardwareMap.dcMotor.get("MotorI");
        Meatball = hardwareMap.servo.get("Meatball");

    }

    @Override
    public void loop() {


        // Drive Train..
        //Throttle and Direction
        float direction = gamepad1.left_stick_y;
        float throttle = gamepad1.right_stick_x;
        //Tank Drive Calculations
        float right = throttle - direction;
        float left = throttle + direction;

        //Setting the Motor Power
        Right.setPower(right);
        Left.setPower(left);

        //Shooter...
        //Making the shot like kobe...
        float kobe = gamepad2.right_trigger;
        //Shooting...
        Shooter.setPower(kobe);


        //Intake...
        if (gamepad2.x) {
            Intake.setPower(1.0);
        }


        //Mappoing out Servo...
        final double MAX_POS = 1.0;     // Maximum rotational position
        final double MIN_POS = 0.0;     // Minimum rotational position

        //shooting command that opens servo and shoots balls and then closes servo
        if (gamepad2.a) {
            Meatball.setPosition(MAX_POS); // Sets the Meatball servo all the way opn
            Shooter.setPower(kobe);
            try {
                Thread.sleep(7000);      //
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Shooter.setPower(0.0);
            Meatball.setPosition(MIN_POS);

        }





    }
}