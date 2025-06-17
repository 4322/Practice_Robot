package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot { 
    private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final Timer hewoTimer = new Timer();
  private double lastPrintTime = 0;
  private int printCount = 0;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private boolean weee= false;
  private boolean wooo = false;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
          SmartDashboard.putData("Auto choices", m_chooser);
      }
      


  public void initialize() {
    this.hewoTimer.reset();
    this.hewoTimer.start(); 

    
      if (this.weee){
        this.wooo = true;
      } else {
        this.weee = true;
    }
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  public void execute() {
    if (this.hewoTimer.hasElapsed(1))  {
    this.printCount++;
    System.out.println(this.printCount);
    this.hewoTimer.reset();
  }


}
  public boolean isFinished() {
    if (!wooo) {
      return printCount>=5;
  } else {
      return false;
  }
  
  } 

  public void end(boolean interrupted) {
    System.out.println("Command ended");
    hewoTimer.stop();
    hewoTimer.reset();
}
}