/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOP", group="Iterative Opmode")

public class TeleOp extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    //Rubber band motors!!!!!
    private DcMotor rubberBandBoiLeft = null;
    private DcMotor rubberBandBoiRight = null;
    //Forebar
    private DcMotor forebar = null;





    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        leftDrive  = hardwareMap.get(DcMotor.class, "front_left_motor");
        rightDrive = hardwareMap.get(DcMotor.class, "front_right_motor");

        rubberBandBoiLeft = hardwareMap.get(DcMotor.class, "rubber_band_left");
        rubberBandBoiRight = hardwareMap.get(DcMotor.class, "rubber_band_right");

        forebar = hardwareMap.get(DcMotor.class, "forebar");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rubberBandBoiRight.setDirection(DcMotor.Direction.REVERSE);
        rubberBandBoiLeft.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        double leftPower;
        double rightPower;

        double ForebarPower;
        double RBpower;


        double drive = gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    =  Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   =  -Range.clip(drive - turn, -1.0, 1.0) ;

        double RBmoveF = gamepad1.right_trigger;
        double RBmoveB = -gamepad1.left_trigger;

        RBpower = Range.clip(RBmoveF + RBmoveB, -1.0, 1.0);

        boolean forebarUp = gamepad1.dpad_up;
        boolean forebarDown = gamepad1.dpad_down;

        if(forebarUp)
        {
            forebar.setDirection(DcMotor.Direction.FORWARD);
            forebar.setPower(0.5);
        }
        else if(forebarDown)
        {
            forebar.setDirection(DcMotor.Direction.REVERSE);
            forebar.setPower(0.5);
        }
        else
        {
            forebar.setPower(0.0);
        }




        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }


    @Override
    public void stop() {
    }

}
