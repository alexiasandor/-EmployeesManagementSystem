package Start;

import View.View1;
import View.View2;
import View.View3;
import Presentation.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        View view = new View();

        Controller controller = new Controller(view);
    }
}
