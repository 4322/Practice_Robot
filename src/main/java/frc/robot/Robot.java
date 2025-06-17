package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
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
          FirstCommand.cancel();
        }
        break;
      case AfterFiverSeconds:
        System.out.println("Five seconds have passed.");
        if (hewoTimer.hasElapsed(10)) {
          daMode = DaMode.AfterTenSeconds;
          FirstCommand.schedule();
        }
        break;
      case AfterTenSeconds:
        System.out.println("Ten seconds have passed.");
        break;
      
    }
  }
  
  private Command FirstCommand = new Command() {
    
    @Override
    public void initialize() {
      hewoTimer.reset();
      hewoTimer.start(); 
    System.out.println("This was started");
    
      if (weee){
       wooo = true;
      } else {
        weee = true;
    }
     FirstCommand.schedule();
    }

    @Override
    public void execute() {
      while (lastPrintTime <= 1){
      if (hewoTimer.hasElapsed(1) && printCount<=4 && lastPrintTime <= 1){
        printCount++;
        hewoTimer.reset();
        System.out.println("Seconds: " + printCount);
      }
      else if (printCount >= 5) {
        printCount = 0;
        hewoTimer.reset();
        lastPrintTime++;
      }
    }
    }

    @Override
    public void end(boolean interrupted) {
    System.out.println("Command ended");
    hewoTimer.stop();
    hewoTimer.reset();}

    @Override
    public boolean isFinished() { 
      if (!wooo) {
      return printCount>=5;
  } 
    else {
      return false;
  }
  
  } 
  }; // End of FirstCommand anonymous class
  
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
