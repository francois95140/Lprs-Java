package modele;

import javafx.beans.property.SimpleIntegerProperty;


public class Timer {
    public SimpleIntegerProperty hh = new SimpleIntegerProperty();
    public SimpleIntegerProperty mm = new SimpleIntegerProperty();
    public SimpleIntegerProperty ss = new SimpleIntegerProperty();
    public SimpleIntegerProperty th = new SimpleIntegerProperty();
    public SimpleIntegerProperty hd = new SimpleIntegerProperty();

    SimpleIntegerProperty cpt = new SimpleIntegerProperty();

    public void update(){
        cpt.set(cpt.get()-1);
        hd.set((cpt.get())%-10+9);
        th.set((cpt.get()/10)%-10+9);
        ss.set((cpt.get()/100)%-60+60);
        mm.set((cpt.get()/6000)%-60+1);
    }
}
