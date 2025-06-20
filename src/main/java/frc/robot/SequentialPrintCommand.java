package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class SequentialPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count;
    private double lastPrintTime;
    private double currentTime;
    private static int nextInstance = 1; 
    private int currentInstance;

    public SequentialPrintCommand() {
        this.currentInstance = nextInstance++;
    }
    private static SequentialPrintCommand runningInstance = null;

    
    @Override
    public void initialize() {
        if (runningInstance != null) {
            System.out.println("Incoming SequentialPrintCommand Instance number " + currentInstance + " is canceled because instance number " + runningInstance.currentInstance + " is already running");
            CommandScheduler.getInstance().cancel(this);
        } 
        else {
            System.out.println("SequentialPrintCommand Instance number " + currentInstance + " is scheduled");
            runningInstance = this;
        }
        System.out.println("SequentialPrintCommand initialized");
        count = 0;
        lastPrintTime = 0.0;
        currentTime = 0.0;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (runningInstance == this)  {
        double currentTime = timer.get();
        if (currentTime - lastPrintTime >= 1.0) {
            count++;
            System.out.println("SequentialPrintCommand Instance number " +  currentInstance + " executed " + count + " times");
            lastPrintTime = currentTime; 
            }
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (runningInstance == this) {
            runningInstance = null; // Clear the running instance when this command ends
            System.out.println("SequentialPrintCommand Instance number " + currentInstance + " ended after " + count + " prints");
            timer.stop();
        }
    }
}
