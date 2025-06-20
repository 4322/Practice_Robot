package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class JacksPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count = 0;

    @Override
    public void initialize() {
        count = 0;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double elapsed = timer.get();

        if (elapsed >= count) {
            count++;
            System.out.println("Message Printed " + count + " times.");
        }
    }

    @Override
    public boolean isFinished() {
        return false; 
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        System.out.println("PrintCommand stopped.");
    }
}
