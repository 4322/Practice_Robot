package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class PrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count;
    private double lastPrintTime;
    private double currentTime;
    static int nextInstance = 1; 
    int currentInstance;

    public PrintCommand() {
        this.currentInstance = nextInstance++;
    }
    
    @Override
    public void initialize() {
        boolean isScheduled = CommandScheduler.getInstance().isScheduled(this);
        if (isScheduled) {
            System.out.println("PrintCommand" + currentInstance + " is already scheduled");
        } else {
            System.out.println("PrintCommand" + currentInstance + " is not scheduled, scheduling now");
            CommandScheduler.getInstance().schedule(this);
    }
        System.out.println("PrintCommand initialized");
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
            System.out.println("PrintCommand" +  currentInstance + "executed " + count + " times");
            lastPrintTime = currentTime;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("PrintCommand" + currentInstance + "ended after " + count + " prints");
        timer.stop();
    }
}
