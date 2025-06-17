package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;


public class commandCreation extends Command {
    // Add any required subsystem dependencies here
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final Timer hewoTimer = new Timer();
    private double lastPrintTime = 0;
    private int printCount = 0;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private boolean weee= false;
    private boolean wooo = false;

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
  }; 
  
  
 