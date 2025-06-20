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


    @Override
    public void initialize() {
        printCount = 0;
        lastPrintTime = 0;
        hewoTimer.reset();
        hewoTimer.start();

        if (runningInstance != null) {
            System.out.println("Instance " + currentInstance + " is waiting for Instance " +
                               runningInstance.currentInstance + " to finish");
            return;
        } else {
            System.out.println("Instance  " + currentInstance + " is scheduled");
            runningInstance = this;
        }

        }    
        @Override
        public void execute() {

            if (runningInstance != this) {
                runningInstance = this;
                System.out.println("Instance " + currentInstance + " started executing");
                return;
            }
            double currentTime = hewoTimer.get();
            if (runningInstance == this) {
                if (currentTime - lastPrintTime >= 1.0) {
                    printCount++;
                    System.out.println("This Instance " +  currentInstance + " executed " + printCount + " times");
                    lastPrintTime = currentTime;
                }
            }
        }
    
        @Override
        public void end(boolean interrupted) {
            hewoTimer.stop();
            if (runningInstance == this) {
                runningInstance = null;
            }
        }
    
        @Override
        public boolean isFinished() {
            return false;
        }
    }




  