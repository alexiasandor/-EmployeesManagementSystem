package Presentation;

import View.View1;
import View.View2;
import View.View3;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Controller {
    private View view;
    private View1 view1;
    private  View2 view2;
    private View3 view3;

    public Controller (View view){
        this.view=view;
        view1=new View1();
        view2= new View2();
        view3=new View3();

    }

}
