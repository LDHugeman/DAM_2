package existdbactividad9;

import java.util.AbstractList;
import java.util.RandomAccess;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author luisd
 */
public class EnvoltorioNodos extends AbstractList<Node> implements RandomAccess {

    private final NodeList list;

    EnvoltorioNodos(NodeList list) {
        this.list = list;
    }

    @Override
    public Node get(int index) {
        return list.item(index);
    }

    @Override
    public int size() {
        return list.getLength();
    }

}
