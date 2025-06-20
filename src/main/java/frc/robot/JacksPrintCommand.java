
package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class JacksPrintCommand extends Command { 
    private final Timer timer = new Timer();
    private int count = 0;
    private boolean hasReset = false;

    public JacksPrintCommand() {}

    @Override 
    public void initialize() {
        count = 0;
        hasReset = false;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(count + 1)) {
            count++;

            if (!hasReset && count > 5) {
                count = 1;
                hasReset = true;
            }

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
