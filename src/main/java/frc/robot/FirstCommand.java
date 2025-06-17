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
        if (hewoTimer.get() - lastPrintTime >= 1.0) {
            printCount++;
            System.out.println("Printed " + printCount + " times since scheduled.");
            lastPrintTime = hewoTimer.get();
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
// End of commandCreation class


