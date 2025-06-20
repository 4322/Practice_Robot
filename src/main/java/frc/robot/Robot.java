package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


 
public class Robot extends TimedRobot { 
    private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final Timer hewoTimer = new Timer();
  private final Timer lastPrintTimer = new Timer();
  private double lastPrintTime = 0;
  private int printCount = 0;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private boolean weee= false;
  private boolean wooo = false;
  FirstCommand Command = new FirstCommand(); 
  Command Command2 = new FirstCommand();
  // No need to add another PrintCommand here.
  // The existing 'printCommand' is already used in your switch statement.
  // Remove or comment out the 'myPrintCommand' declaration to avoid confusion.
  
  @Override
  public void teleopInit() {
    hewoTimer.reset(); // Reset the timer
    hewoTimer.start(); // Start the timer
    lastPrintTimer.reset();
    lastPrintTimer.start();
    Command.schedule(); // Schedule the PrintCommand to run
    Command2.schedule();
  }

  public enum DaMode {
    Waiting,
    AfterFiverSeconds,
    AfterTenSeconds,
    After15Seconds;
  }

  DaMode daMode = DaMode.Waiting;

  @Override
  public void teleopPeriodic() {
    switch (daMode) {
      case Waiting:
      if (hewoTimer.hasElapsed(5.1)) {
        daMode = DaMode.AfterFiverSeconds;
        
        Command2.schedule(); 
      }
        break;
      case AfterFiverSeconds:
      if (hewoTimer.hasElapsed(10.2)) {
          daMode = DaMode.AfterTenSeconds;
          printCount = 0;  
          Command.cancel();      }
        break;
      case AfterTenSeconds:
      if (hewoTimer.hasElapsed(15.2)) {
        Command.schedule();
      }

        break;
      case After15Seconds:
      if (hewoTimer.hasElapsed(20.2)) {
      }
        break;
      
    }
  }
  
  
  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotInit() {
    
  }

  @Override
      public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    
        
      }
  }
    
  


  
