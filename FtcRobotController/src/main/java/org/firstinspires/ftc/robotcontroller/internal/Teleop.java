package org.firstinspires.ftc.robotcontroller.internal;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Juan Rivera + Joshua Young  on 9/16/2016.
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
    // Servos for beacon
    Servo LeftB;
    Servo RightB;
    ColorSensor ColorSensor;


    @Override
    public void init() {
        //mapping out the motors
        Right = hardwareMap.dcMotor.get("motorR");
        Left = hardwareMap.dcMotor.get("motorL");
        Shooter = hardwareMap.dcMotor.get("MotorS");
        Intake = hardwareMap.dcMotor.get("MotorI");
        Meatball = hardwareMap.servo.get("Meatball");
        LeftB = hardwareMap.servo.get("LeftBeacon");
        RightB = hardwareMap.servo.get("RightBeacon");
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");

    }
//  //all the teleop commands for both gamepads
    @Override
    public void loop() {


        // Drive Train..
        //Throttle and Direction
        float direction = gamepad1.left_stick_y;
        //float throttle = gamepad1.right_stick_x;

        float Forward = gamepad1.right_trigger;
        float Backward = gamepad1.left_trigger;
        //Tank Drive Calculations
        float right = Forward - direction;
        float left = Forward + direction;
        float lB = Backward - direction;
        float rB = Backward + direction;

        //Setting the Motor Power for wheels
        Right.setPower(right);
        Left.setPower(left);
        Right.setPower(rB);
        Left.setPower(lB);

        //X on gamepad 1 for a 180 degree turn



        if (gamepad1.a){
        turnAround();
        }
        if (gamepad1.b) {
        turnRight();
        }
        if (gamepad1.x) {
            turnLeft();
        }
        //X for intake
        if (gamepad2.x) {
            Intake.setPower(1.0);
        }
        //A for shoot command group
        if (gamepad2.a) {
            shoot();
        }
        if (gamepad2.y) {
            colorRead();
        }



    }

    //All the commands for Drivers Turns
      //command to do a quick 90 degreee counter-clockwise turn
      public void turnLeft() {

      }
      //command to do a quick 90 degree clockwise turn
      public void turnRight() {

      }
      //Command for making a quick 180 degree turn
      public void turnAround () {

      }



      //Command Group for shooting, which includes intake and meatball as well
      public void shoot () {

            Meatball.setPosition(1.0); // Sets the Meatball servo all the way open
            Intake.setPower(1.0);
            Shooter.setPower(1.0);
            try {
                Thread.sleep(7000);      //<---The program is already shooting so this makes a 7 second delay
            } catch (InterruptedException e) { //and then after the delay it will stop shooting
                e.printStackTrace();     //and will put down the meatball maker (the servo to block
            }                            //the balls so we can load them up again) and ends the
            Intake.setPower(0.0);        //command group
            Shooter.setPower(0.0);
            Meatball.setPosition(0.0);


    }
    public void colorRead () {

            if(ColorSensor.red() > ColorSensor.blue()) {
                RightB.setPosition(1.0);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RightB.setPosition(0.0);
            }
            else {
                LeftB.setPosition(1.0);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LeftB.setPosition(0.0);
            }

    }




    }


