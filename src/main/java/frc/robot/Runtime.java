package frc.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
public class Runtime extends Command{
    private final Timer stuff = new Timer();
    private Integer seconds;
    private Integer times = 1;
    @Override
    public void initialize(){
        stuff.restart();
        seconds = 1;
    }
    @Override
    public void execute(){
        if (stuff.hasElapsed(seconds)){
            System.out.println(times + " Times this has been has printed");
            seconds = seconds + 1;
            times = times + 1;
        }
    }

}
