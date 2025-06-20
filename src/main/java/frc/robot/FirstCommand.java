package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;



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
        
    }
  
// Add a setter or constructor to initialize FirstCommand as needed

    @Override
    public void initialize() {
        printCount = 0;
        lastPrintTime = 0;
        hewoTimer.reset();
        hewoTimer.start();
    }

    @Override
    public void execute() {
        if (lastPrintTimer.hasElapsed(1) && printCount < 6) {
            lastPrintTimer.reset();
            lastPrintTimer.start();
            printCount++;
            System.out.println("Seconds: " + printCount);
          }
    }

   

    @Override
    public void end(boolean interrupted) {

        hewoTimer.stop();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
    }




    
    

 