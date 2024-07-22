package twisk.outils;

import com.google.gson.InstanceCreator;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.ActiviteIG;

import java.lang.reflect.Type;

public class EtapeIGInstanceCreator implements InstanceCreator<EtapeIG> {
    @Override
    public EtapeIG createInstance(Type type) {
        // return a new instance of a concrete class that extends EtapeIG
        return new ActiviteIG("Default", 0, 0);
    }
}