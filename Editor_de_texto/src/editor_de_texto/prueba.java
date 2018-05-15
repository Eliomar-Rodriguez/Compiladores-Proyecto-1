package editor_de_texto;

import java.util.Hashtable;

public class prueba {
    private Hashtable<Object,Object> hash;

    public prueba() {
        this.hash = new Hashtable<Object, Object>();
    }

    public Object getHash(Object key) {
        return this.hash.get(key);
    }

    public void setHash(Object key, Object value) {
        this.hash.put(key,value);
    }
}
