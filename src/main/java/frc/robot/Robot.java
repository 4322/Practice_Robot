package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;


 
public class Robot extends TimedRobot { 
    private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final Timer hewoTimer = new Timer();
  private double lastPrintTime = 0;
  private int printCount = 0;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private boolean weee= false;
  private boolean wooo = false;
  public PrintCommand printCommand = new PrintCommand("Hello from PrintCommand!"); 
  // No need to add another PrintCommand here.
  // The existing 'printCommand' is already used in your switch statement.
  // Remove or comment out the 'myPrintCommand' declaration to avoid confusion.
  

  public enum DaMode {
    Waiting,
    AfterFiverSeconds,
    AfterTenSeconds;
  }

  DaMode daMode = DaMode.Waiting;

  public void checkDaMode() {
    switch (daMode) {
      case Waiting:
        System.out.println("Currently waiting.");
        if (hewoTimer.hasElapsed(5)) {
          daMode = DaMode.AfterFiverSeconds;
          printCommand.cancel();
        }
        break;
      case AfterFiverSeconds:
        System.out.println("Five seconds have passed.");
        if (hewoTimer.hasElapsed(10)) {
          daMode = DaMode.AfterTenSeconds;
          printCommand.schedule();
        }
        break;
      case AfterTenSeconds:
        System.out.println("Ten seconds have passed.");
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
