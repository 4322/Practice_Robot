package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class SequentialPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count = 0;
    private double lastPrintTime = 0.0;
    private double currentTime = 0.0;
    @Override
    public void initialize() {
        System.out.println("SequentialPrintCommand initialized");
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
            System.out.println("SequentialPrintCommand executed " + count + " times");
            lastPrintTime = currentTime;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("SequentialPrintCommand ended after " + count + " prints");
        timer.stop();
    }
}
