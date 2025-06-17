package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class PrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count = 0;
    private double lastPrintTime = 0.0;
    @Override
    public void initialize() {
        System.out.println("PrintCommand initialized");
        count = 0;
        timer.reset();
        timer.start();
        lastPrintTime = 0.0;
    }

    @Override
    public void execute() {
        double currentTime = timer.get();
        if (currentTime - lastPrintTime >= 1.0 || count >= 10) {
            count++;
            System.out.println("PrintCommand executed " + count + " times");
            lastPrintTime = currentTime;
        }
        if (count >= 5 && count < 10) {
            System.out.println("PrintCommand has printed 5 times, ending command.");
            count++;
        }
    }

    @Override
    public boolean isFinished() {
        return count >= 5;

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("PrintCommand ended after " + count + " prints");
        timer.stop();
    }
}
