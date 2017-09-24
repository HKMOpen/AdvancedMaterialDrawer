package de.madcyph3r.materialnavigationdrawer.menu.item.style;

import android.view.View;

import de.madcyph3r.materialnavigationdrawer.menu.item.MaterialMenuItem;
import de.madcyph3r.materialnavigationdrawer.listener.Selectable;

public class MaterialItemDevisor extends MaterialMenuItem implements Selectable {

/*    public MaterialItemDevisor() {
        setBottom(false);
    }

    public MaterialItemDevisor(boolean bottom) {
        setBottom(bottom);
    }*/

    @Override
    public void select() {

    }

    @Override
    public void unSelect() {

    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public View getView() {
        return null;
    }
}
