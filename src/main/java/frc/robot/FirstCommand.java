package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



public class FirstCommand extends Command {
    // Add any required subsystem dependencies here
    private final Timer hewoTimer = new Timer();
    private final Timer lastPrintTimer = new Timer();
    private double lastPrintTime = 0;
    private int printCount = 0;
    // private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private boolean weee= false;
    private boolean wooo = false;
    static int nextInstance = 1; 
    int currentInstance;
  
    public FirstCommand() {
        this.currentInstance = nextInstance++;
    }
    private static FirstCommand runningInstance = null;
// Add a setter or constructor to initialize FirstCommand as needed

    @Override
    public void initialize() {
        printCount = 0;
        lastPrintTime = 0;
        hewoTimer.reset();
        hewoTimer.start();

        if (runningInstance != null) {
            System.out.println("Error: Instance number " + currentInstance + " tried to start, but instance " + runningInstance.currentInstance + " is already running.");
            CommandScheduler.getInstance().cancel(this);
            return;
        } else {
            System.out.println("Instance  " + currentInstance + " is scheduled");
            runningInstance = this;
        }
    }    
        @Override
        public void execute() {
            // Only allow the running instance to execute
            if (runningInstance != this) {
                return;
            }
            double currentTime = hewoTimer.get();
            if (currentTime - lastPrintTime >= 1.0) {
                printCount++;
                System.out.println("This Instance " +  currentInstance + " executed " + printCount + " times");
                lastPrintTime = currentTime;
            }
        }
    
        @Override
        public void end(boolean interrupted) {
            hewoTimer.stop();
            // If this instance is ending, clear the runningInstance reference
            if (runningInstance == this) {
                runningInstance = null;
            }
        }
    
        @Override
        public boolean isFinished() {
            // Never finishes on its own
            return false;
        }
    }




  