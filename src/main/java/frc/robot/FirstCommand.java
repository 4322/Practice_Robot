package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class FirstCommand extends Command {
    private Timer commandTimer = new Timer();
    private int printCount = 0;
    private boolean triggered = false;
    private boolean infinite = false;


    @Override
    public void initialize() {
        this.commandTimer.reset();
        this.commandTimer.start();
        this.printCount = 0;
        System.out.println("Command initialized");

        if (this.triggered){
            this.infinite = true;
        } else {
            this.triggered = true;
        }
    }

    @Override
    public void execute() {
        //System.out.println("Executed");

        if (commandTimer.get()>=1) {
            this.printCount++;
            System.out.println(this.printCount);
            this.commandTimer.reset();
        }
    }

    @Override
    public boolean isFinished() {
        if (!infinite) {
            return printCount>=5;
        } else {
            return false;
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Command ended");
        commandTimer.stop();
        commandTimer.reset();
    }

}
