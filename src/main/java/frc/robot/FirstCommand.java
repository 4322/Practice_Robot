package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class FirstCommand extends Command {
    private Timer commandTimer = new Timer();
    private int printCount = 0;

    @Override
    public void initialize() {
        commandTimer.reset();
        commandTimer.start();
        printCount = 0;
        System.out.println("Command initialized");
    }

    @Override
    public void execute() {
        //System.out.println("Executed");

        if (commandTimer.get()>=1) {
            this.printCount++;
            System.out.println(this.printCount);
            commandTimer.reset();
        }
    }

    @Override
    public boolean isFinished() {
        return printCount>=5;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Command ended");
        commandTimer.stop();
        commandTimer.reset();
    }
}
