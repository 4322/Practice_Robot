package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class PrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count;
    private double lastPrintTime;
    static int nextInstance = 1;
    int currentInstance;

    // Static field to track the running instance
    private static PrintCommand runningInstance = null;

    public PrintCommand() {
        this.currentInstance = nextInstance++;
    }

    @Override
    public void initialize() {
        if (runningInstance != null) {
            System.out.println("PrintCommand" + currentInstance + " is waiting for PrintCommand" +
                               runningInstance.currentInstance + " to finish");
        } else {
            System.out.println("PrintCommand" + currentInstance + " is now the running instance");
        }

        count = 0;
        lastPrintTime = 0.0;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // Wait until there is no active instance
        if (runningInstance == null) {
            runningInstance = this;
            System.out.println("PrintCommand" + currentInstance + " started executing");
        }

        // Only proceed if this is the active instance
        if (runningInstance == this) {
            double currentTime = timer.get();
            if (currentTime - lastPrintTime >= 1.0) {
                count++;
                System.out.println("PrintCommand" + currentInstance + " executed " + count + " times");
                lastPrintTime = currentTime;
            }
        }
    }

    @Override
    public boolean isFinished() {
        // Keep running indefinitely
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (runningInstance == this) {
            System.out.println("PrintCommand" + currentInstance + " ended after " + count + " prints");
            runningInstance = null;  // Release control
        } else {
            System.out.println("PrintCommand" + currentInstance + " ended but was never active");
        }
        timer.stop();
    }
}
