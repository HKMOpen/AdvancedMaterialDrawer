package de.madcyph3r.example.example.headItemTypes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.amulyakhare.textdrawable.TextDrawable;

import de.madcyph3r.example.R;
import de.madcyph3r.example.example.FragmentDummy;
import de.madcyph3r.example.example.FragmentInstruction;
import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.activity.MaterialNavHeadItemActivity;
import de.madcyph3r.materialnavigationdrawer.menu.head.MaterialHeadItem;
import de.madcyph3r.materialnavigationdrawer.menu.MaterialMenu;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionFragment;
import de.madcyph3r.materialnavigationdrawer.tools.RoundedCornersDrawable;

public class HeadItemThreeDontCloseOnChangeActivity extends MaterialNavHeadItemActivity {

    MaterialNavigationDrawer drawer = null;

    @Override
    protected boolean finishActivityOnNewIntent() {
        return false;
    }

    @Override
    protected int getNewIntentRequestCode(Class clazz) {
        return 0;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        drawer = this;

        // add head Item (menu will be loaded automatically)
        this.addHeadItem(getHeadItem2());
        this.addHeadItem(getHeadItem3());
        this.addHeadItem(getHeadItem1());

        // load menu
        this.loadMenu(getCurrentHeadItem().getMenu());

        // load the MaterialItemSectionFragment, from the given startIndex
        this.loadStartFragmentFromMenu(getCurrentHeadItem().getMenu());
    }

    private MaterialHeadItem getHeadItem1() {

        // information text for the fragment
        Bundle bundle = new Bundle();
        bundle.putString("instruction", "This example shows the head item style with three items. \" +\n" +
                "                \"If a new head item is chosen, the drawer will not close. \" +\n" +
                "                \"See the method headerType in the source code.");

        Fragment fragmentInstruction = new FragmentInstruction();
        fragmentInstruction.setArguments(bundle);

        // create menu
        MaterialMenu menu = new MaterialMenu();
        menu.add(new MaterialItemSectionFragment(this, "Instruction", fragmentInstruction, "Don't Close Drawer On HeadItem Change (Three Items)"));
        menu.add(new MaterialItemSectionFragment(this, "Section 1", new FragmentDummy(), "Section 1"));
        menu.add(new MaterialItemSectionFragment(this, "Section 2", new FragmentDummy(), "Section 2"));
        menu.add(new MaterialItemSectionFragment(this, "Section 3", new FragmentDummy(), "Section 3"));

        // create Head Item
        // use bitmap and make a circle photo
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_item_icon);
        final RoundedCornersDrawable drawableAppIcon = new RoundedCornersDrawable(getResources(), bitmap);
        MaterialHeadItem headItem = new MaterialHeadItem(this, "A HeadItem", "A Subtitle", drawableAppIcon, R.drawable.mat1, menu);
        headItem.setCloseDrawerOnChanged(false); // don't close the drawer, if this head item chosen

        return headItem;
    }

    private MaterialHeadItem getHeadItem2() {

        // create menu
        MaterialMenu menu = new MaterialMenu();
        menu.add(new MaterialItemSectionFragment(this, "Section 1 (Head 2)", new FragmentDummy(), "Section 1 (Head 2)"));
        menu.add(new MaterialItemSectionFragment(this, "Section 2", new FragmentDummy(), "Section 2"));

        // create Head Item
        TextDrawable.Builder headPhoto = new TextDrawable.Builder();
        headPhoto.setColor(Color.GREEN);
        headPhoto.setText("B");
        headPhoto.setShape(TextDrawable.DRAWABLE_SHAPE_ROUND_RECT);
        headPhoto.setBorderThickness(3);
        TextDrawable icon = headPhoto.build();

        MaterialHeadItem headItem = new MaterialHeadItem(this, "B HeadItem", "B Subtitle", icon, R.drawable.mat6, menu);
        headItem.setCloseDrawerOnChanged(false); // don't close the drawer, if this head item chosen

        return headItem;
    }

    private MaterialHeadItem getHeadItem3() {

        // create menu
        MaterialMenu menu = new MaterialMenu();
        menu.add(new MaterialItemSectionFragment(this, "Section 1 (Head 3)", new FragmentDummy(), "Section 1 (Head 3)"));
        menu.add(new MaterialItemSectionFragment(this, "Section 2", new FragmentDummy(), "Section 2"));

        // create Head Item
        TextDrawable.Builder headPhoto = new TextDrawable.Builder();
        headPhoto.setColor(Color.GREEN);
        headPhoto.setText("G");
        headPhoto.setShape(TextDrawable.DRAWABLE_SHAPE_ROUND_RECT);
        headPhoto.setBorderThickness(3);
        TextDrawable icon = headPhoto.build();

        MaterialHeadItem headItem = new MaterialHeadItem(this, "C HeadItem", "C Subtitle", icon, R.drawable.mat6, menu);
        headItem.setCloseDrawerOnChanged(false); // don't close the drawer, if this head item chosen

        return headItem;
    }

    @Override
    public void afterInit(Bundle savedInstanceState) {

    }
}