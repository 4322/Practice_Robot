package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;

public class Message extends Command {
    
  @Override
  public void initialize() {
    System.out.println("Hi!");
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
