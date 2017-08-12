package de.madcyph3r.example.example.testLand;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import de.madcyph3r.example.example.FragmentDummy;
import de.madcyph3r.example.example.FragmentInstruction;
import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.activity.MaterialNavHeadItemActivity;
import de.madcyph3r.materialnavigationdrawer.listener.MaterialSectionOnClickListener;
import de.madcyph3r.materialnavigationdrawer.menu.MaterialMenu;
import de.madcyph3r.materialnavigationdrawer.menu.item.custom.MaterialItemCustom;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSection;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionFragment;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionOnClick;

/**
 * Created by hesk on 12/8/2017.
 */

public class dgdemo extends MaterialNavHeadItemActivity {
    MaterialNavigationDrawer drawer = null;

    private MaterialMenu parent_menu() {

        // create menu
        MaterialMenu menu = new MaterialMenu();
        //MaterialItemCustom mc = new MaterialItemCustom(this, );

        MaterialItemSectionOnClick section = new MaterialItemSectionOnClick(this, "Cinemas");
        section.setOnSectionClickListener(new MaterialSectionOnClickListener() {
            @Override
            public void onClick(MaterialItemSection section, View view) {
                Toast.makeText(drawer, "this is one", Toast.LENGTH_LONG).show();
                item_from_home();
            }
        });

        menu.add(section);
        MaterialItemSectionOnClick section1 = new MaterialItemSectionOnClick(this, "Eat & Drink");
        section1.setOnSectionClickListener(new MaterialSectionOnClickListener() {
            @Override
            public void onClick(MaterialItemSection section, View view) {
                Toast.makeText(drawer, "do to the second layer", Toast.LENGTH_LONG).show();
                item_from_membership();
            }
        });
        menu.add(section1);
        menu.add(new MaterialItemSectionFragment(this, "Membership", new FragmentDummy(), "Section 2"));
        menu.add(new MaterialItemSectionFragment(this, "Party", new FragmentDummy(), "Section 3"));
        menu.add(new MaterialItemSectionFragment(this, "Other", new FragmentDummy(), "Section 3"));
        menu.add(new MaterialItemSectionFragment(this, "Language", new FragmentDummy(), "Section 3"));

        // load menu
        this.loadMenu(menu);
        return menu;
    }

    private void item_from_membership() {
        MaterialMenu menu = new MaterialMenu();
        MaterialItemSectionOnClick section = new MaterialItemSectionOnClick(this, "Back");
        section.setOnSectionClickListener(new MaterialSectionOnClickListener() {
            @Override
            public void onClick(MaterialItemSection section, View view) {
                Toast.makeText(drawer, "go back to home", Toast.LENGTH_LONG).show();
                parent_menu();
            }
        });
        menu.add(section);
        menu.add(new MaterialItemSectionFragment(this, "Membership 1", new FragmentDummy(), "Section 1"));
        menu.add(new MaterialItemSectionFragment(this, "Membership 2", new FragmentDummy(), "Section 2"));
        menu.add(new MaterialItemSectionFragment(this, "Membership 3", new FragmentDummy(), "Section 3"));
        loadMenu(menu);
    }

    private void item_from_home() {
        MaterialMenu menu = new MaterialMenu();
        MaterialItemSectionOnClick section = new MaterialItemSectionOnClick(this, "Back");
        section.setOnSectionClickListener(new MaterialSectionOnClickListener() {
            @Override
            public void onClick(MaterialItemSection section, View view) {
                Toast.makeText(drawer, "go back to home", Toast.LENGTH_LONG).show();
                parent_menu();
            }
        });
        menu.add(section);
        menu.add(new MaterialItemSectionFragment(this, "Cinemas 1", new FragmentDummy(), "Section 1"));
        menu.add(new MaterialItemSectionFragment(this, "Cinemas 2", new FragmentDummy(), "Section 2"));
        menu.add(new MaterialItemSectionFragment(this, "Cinemas 3", new FragmentDummy(), "Section 3"));
        loadMenu(menu);
    }


    @Override
    public void init(Bundle savedInstanceState) {

        drawer = this;

        // information text for the fragment
        Bundle bundle = new Bundle();
        bundle.putString("instruction", "Default back pattern. It only calls super.onBackPressed() .");
        Fragment fragmentInstruction = new FragmentInstruction();
        fragmentInstruction.setArguments(bundle);

        // load first MaterialItemSectionFragment in the menu, because there is no start position
        this.loadStartFragmentFromMenu(parent_menu());
        // set back pattern
        this.setBackPattern(MaterialNavigationDrawer.DRAWER_DEFAULT_WIDTH);
    }

    @Override
    protected boolean finishActivityOnNewIntent() {
        return false;
    }


    @Override
    public void afterInit(Bundle savedInstanceState) {

    }

    @Override
    protected int getNewIntentRequestCode(Class clazz) {
        return 0;
    }
}
