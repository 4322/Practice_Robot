package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class FirstCommand extends Command {
    private Timer commandTimer = new Timer();
    private int printCount = 0;

    @Override
    public void initalize() {
        commandTimer.stop();
        commandTimer.reset();
        printCount = 0;
    }

    @Override
    public void execute() {
        if (commandTimer.get()>=5) {
            this.printCount++;
            System.out.println(this.printCount);
            commandTimer.reset();
        }
    }

    @Override
    public boolean isFinished() {

    }

    @Override
    public void end() {

    }
}
