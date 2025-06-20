package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystems.DummySubsystem;
public class SequentialPrintCommand extends Command {
    private final Timer timer = new Timer();
    private int count;
    private double lastPrintTime;
    private double currentTime;
    private static int nextInstance = 1; 
    private int currentInstance;
    private final DummySubsystem subsystem1;

    public SequentialPrintCommand(DummySubsystem subsystem1) {
        this.currentInstance = nextInstance++;
        this.subsystem1 = subsystem1;
        addRequirements(subsystem1);
    }


    private static SequentialPrintCommand runningInstance = null;

    
    @Override
    public void initialize() {
        System.out.println("SequentialPrintCommand instance number " + currentInstance + " is initialized ");
        count = 0;
        lastPrintTime = 0.0;
        currentTime = 0.0;
        timer.restart();
    }

    @Override
    public void execute() {
        double currentTime = timer.get();
        if (currentTime - lastPrintTime >= 1.0) {
            count++;
            System.out.println("SequentialPrintCommand Instance number " +  currentInstance + " executed " + count + " times");
            lastPrintTime = currentTime; 
            }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
    }
}
