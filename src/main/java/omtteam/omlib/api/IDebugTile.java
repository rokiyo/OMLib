package omtteam.omlib.api;

import java.util.List;

/**
 * Created by nico on 23/05/17.
 * This interface is for all box
 */
public interface IDebugTile {
    /**
     * Returns the List of debug options.
     *
     * @return the List with debug info (do not forget to add super.getDebugInfo() if you extend a class which implements this.)
     */
    List<String> getDebugInfo();
}
