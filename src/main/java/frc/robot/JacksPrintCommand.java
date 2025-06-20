package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class JacksPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count = 0;
    private boolean hasReset = false;
    private final int instanceId;

    public JacksPrintCommand(int instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public void initialize() {
        count = 0;
        hasReset = false;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double elapsed = timer.get();
        int target = count + 1;

        if (elapsed >= target) {
            count++;

            if (!hasReset && count > 5) {
                count = 1;
                hasReset = true;
            }

            System.out.println("Instance " + instanceId + ", message " + count);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        System.out.println("Instance " + instanceId + " stopped.");
    }
}
