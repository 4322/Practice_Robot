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
    // Declare FirstCommand as a Command object
    private Command FirstCommand;

    public enum DaMode {
      Waiting,
      AfterFiverSeconds,
      AfterTenSeconds;
    }
  
    DaMode daMode = DaMode.Waiting;
  
    // Add a setter or constructor to initialize FirstCommand as needed
    public void setFirstCommand(Command command) {
      this.FirstCommand = command;
    }

    public void checkDaMode() {
      switch (daMode) {
        case Waiting:
          System.out.println("Currently waiting.");
          if (hewoTimer.hasElapsed(5)) {
            daMode = DaMode.AfterFiverSeconds;
            if (FirstCommand != null) {
              FirstCommand.cancel();
            }
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
    
    public class PrintCommand extends Command {
        private int printCount = 0;
        private double lastPrintTime = 0;
        private final Timer timer = new Timer();
    
        @Override
        public void initialize() {
            printCount = 0;
            lastPrintTime = 0;
            timer.reset();
            timer.start();
        }
    
        @Override
        public void execute() {
            if (timer.get() - lastPrintTime >= 1.0) {
                printCount++;
                System.out.println("Printed " + printCount + " times since scheduled.");
                lastPrintTime = timer.get();
            }
        }
    
        @Override
        public void end(boolean interrupted) {
            timer.stop();
        }
    
        @Override
        public boolean isFinished() {
            return false;
        }
    }
  
  } 
// End of commandCreation class
  
  
 