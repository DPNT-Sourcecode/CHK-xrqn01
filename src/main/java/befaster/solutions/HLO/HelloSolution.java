package befaster.solutions.HLO;

import java.awt.*;
import java.util.Optional;

public class HelloSolution {

    // PLEASE DON'T MIND ME, I'M JUST TESTING THE RECORDER

    public static void main(String[] args) {
        final Dimension screenSize = Toolkit.getDefaultToolkit()
                                            .getScreenSize();
        Optional.ofNullable(GraphicsEnvironment.getLocalGraphicsEnvironment())
                .map(GraphicsEnvironment::getDefaultScreenDevice)
                .map(GraphicsDevice::getDefaultConfiguration)
                .map(GraphicsConfiguration::getBounds)
                .ifPresent(System.out::println);
        System.out.println(new Rectangle(screenSize));
    }

    public String hello(String friendName) {
        return "Hello, " + friendName + '!';
    }
}
