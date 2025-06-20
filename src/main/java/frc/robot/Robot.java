// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.concurrent.CountDownLatch;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final JacksPrintCommand jacksPrintCommand = new JacksPrintCommand();
  private final Timer timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotInit() {

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
      timer.reset();
      timer.start();
      jacksPrintCommand.schedule();
  }

  public enum RobotState {
    BEFORE_FIVE_SECONDS,
    BETWEEN_FIVE_AND_TEN_SECONDS,
    AFTER_TEN_SECONDS;
}

RobotState robotState = RobotState.BEFORE_FIVE_SECONDS;
private static final double FIVE_SECOND_THRESHOLD = 5.1;
private static final double TEN_SECOND_THRESHOLD = 10.0;

private boolean hasReset = false;
private boolean hasTransitionedOnce = false;

@Override
public void teleopPeriodic() {
    updateRobotState();
}

private void updateRobotState() {
    double timeElapsed = timer.get();

    switch (robotState) {
        case BEFORE_FIVE_SECONDS:
            if (timeElapsed >= FIVE_SECOND_THRESHOLD) {
                transitionToState(RobotState.BETWEEN_FIVE_AND_TEN_SECONDS);
            }
            break;
        case BETWEEN_FIVE_AND_TEN_SECONDS:
            if (timeElapsed >= TEN_SECOND_THRESHOLD) {
                transitionToState(RobotState.AFTER_TEN_SECONDS);
            }
            break;
            case AFTER_TEN_SECONDS:
            if (!hasTransitionedOnce) {
                timer.reset();
                transitionToState(RobotState.BEFORE_FIVE_SECONDS);
            } else {
                
                timer.reset();
                robotState = RobotState.BEFORE_FIVE_SECONDS;
            }
            break;
        
    }
}

private void transitionToState(RobotState newState) {
  if (hasTransitionedOnce) return;

  robotState = newState;
  System.out.println(newState + " reached");

  switch (newState) {
      case BETWEEN_FIVE_AND_TEN_SECONDS:
          jacksPrintCommand.cancel();
          break;
      case AFTER_TEN_SECONDS:
          jacksPrintCommand.schedule();
          break;
      case BEFORE_FIVE_SECONDS:
          if (!hasReset) {
              jacksPrintCommand.schedule();
              hasReset = true;
          }
          hasTransitionedOnce = true;
          break;
      default:
          break;
  }
}


  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
