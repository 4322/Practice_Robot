package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;


public class commandCreation extends Command {
    // Add any required subsystem dependencies here
    private final Timer hewoTimer = new Timer();
    private final Timer lastPrintTimer = new Timer();
    private double lastPrintTime = 0;
    private int printCount = 0;
    // private final SendableChooser<String> m_chooser = new SendableChooser<>();
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
    @Override
    public void initialize() {
      hewoTimer.reset();
      hewoTimer.start(); 
    System.out.println("This was started");
    
      if (this.weee){
       this.wooo = true;
      } else {
        this.weee = true;
    }
    
    }

    @Override
    public void execute() {
      if (lastPrintTimer.hasElapsed(10) && lastPrintTime <= 1) {
        if (hewoTimer.hasElapsed(1)){
          printCount++;
          hewoTimer.reset();
          System.out.println("Seconds: " + printCount);
        }
      }
      else{
      while (lastPrintTime <= 1){
      if (hewoTimer.hasElapsed(1) && printCount<=4 && lastPrintTime <= 0){
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
  }


    @Override
    public void end(boolean interrupted) {
        System.out.println("PrintCommand ended after " + printCount + " prints");
        hewoTimer.stop();
        hewoTimer.reset();
        return;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
  
  } 
// End of commandCreation class
  
  
 