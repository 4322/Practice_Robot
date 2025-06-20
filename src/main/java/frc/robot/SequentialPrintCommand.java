package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class SequentialPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count;
    private double lastPrintTime;
    private double currentTime;
    static int nextInstance = 1; 
    int currentInstance;

    public SequentialPrintCommand() {
        this.currentInstance = nextInstance++;
    }
    
    @Override
    public void initialize() {
        System.out.println("sequentialPrintCommand initialized");
        count = 0;
        lastPrintTime = 0.0;
        currentTime = 0.0;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double currentTime = timer.get();
        if (currentTime - lastPrintTime >= 1.0) {
            count++;
            System.out.println("sequentialPrintCommand Instance number " +  currentInstance + " executed " + count + " times");
            lastPrintTime = currentTime;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("sequentialPrintCommand Instance number " + currentInstance + " ended after " + count + " prints");
        timer.stop();
    }
}
