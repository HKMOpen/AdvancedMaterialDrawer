package de.madcyph3r.materialnavigationdrawer.menu.item.section;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.R;
import de.madcyph3r.materialnavigationdrawer.listener.MaterialSectionChangeListener;
import de.madcyph3r.materialnavigationdrawer.listener.MaterialSectionOnClickListener;
import de.madcyph3r.materialnavigationdrawer.menu.item.MaterialMenuItem;
import de.madcyph3r.materialnavigationdrawer.listener.Selectable;
import de.madcyph3r.materialnavigationdrawer.ripple.MaterialPlain;
import de.madcyph3r.materialnavigationdrawer.ripple.MaterialRippleLayout;
import de.madcyph3r.materialnavigationdrawer.ripple.MaterialRippleLayoutNineOld;

public abstract class MaterialItemSection<CustomTextView extends TextView> extends MaterialMenuItem implements View.OnTouchListener, View.OnClickListener, Selectable {


    private MaterialNavigationDrawer drawer;
    private View view;
    private CustomTextView text;
    private CustomTextView notifications;
    private ImageView section_left_icon;
    private ImageView section_right_icon;
    protected MaterialSectionOnClickListener sectionListener;
    private boolean isSelected;
    private int sectionColor;
    private boolean fillIconColor;
    private boolean hasSectionColor;
    private boolean hasColorDark;
    private int colorUnpressed;
    private int colorSelected;
    private int colorOnPressHighlight;
    private int iconColor;
    private int colorDark;
    private int textColor;
    private int notificationColor;
    private int numberNotifications;
    protected String title;
    private boolean hasIcon = false;
    private boolean sectionDivided = false;


    /*public MaterialItemSection(Context ctx, boolean hasIcon, int target, boolean bottom, MaterialSectionChangeListener changeListener, boolean fullBanner) {
        init(ctx, hasIcon, target, bottom, changeListener, fullBanner);
    }*/
    @LayoutRes
    protected int getLayoutRes() {
        return -1;
    }

    @SuppressLint("WrongViewCast")
    protected void init(MaterialNavigationDrawer drawer, Drawable iconDrawable /*resIconID/*, boolean hasIcon, MaterialSectionChangeListener sectionChangeListener*/, boolean fullWidthIcon) {
        this.drawer = drawer;

        int currentApiVersion = Build.VERSION.SDK_INT;
        /**
         * theme location
         */
        Resources.Theme theme = drawer.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.sectionStyle, typedValue, true);
        TypedArray values = theme.obtainStyledAttributes(typedValue.resourceId, R.styleable.MaterialSection);

        if (iconDrawable == null) {
            hasIcon = false;
        } else {
            hasIcon = true;
        }
        if (getLayoutRes() == -1) {
            // inflate the right layout
            if (!hasIcon && currentApiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(getItemLayout(values, R.layout.layout_material_section), drawer);
            } else if (!hasIcon && currentApiVersion < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(getItemLayout(values, R.layout.layout_material_section_nine_old), drawer);
            } else if (hasIcon && !fullWidthIcon && currentApiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(getItemLayout(values, R.layout.layout_material_section_icon), drawer);
                section_left_icon = (ImageView) view.findViewById(R.id.amd_layout_left_icon);
                setIconView(iconDrawable);
            } else if (hasIcon && !fullWidthIcon && currentApiVersion < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(getItemLayout(values, R.layout.layout_material_section_icon_nine_old), drawer);
                section_left_icon = (ImageView) view.findViewById(R.id.amd_layout_left_icon);
                setIconView(iconDrawable);
            } else if (hasIcon && fullWidthIcon && currentApiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(R.layout.layout_material_section_full_image, drawer);
                section_left_icon = (ImageView) view.findViewById(R.id.amd_layout_left_icon);
                setIconView(iconDrawable);
            } else if (hasIcon && fullWidthIcon && currentApiVersion < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view = inflatView(R.layout.layout_material_section_full_image_nine_old, drawer);
                section_left_icon = (ImageView) view.findViewById(R.id.amd_layout_left_icon);
                setIconView(iconDrawable);
            }
        } else {
            view = inflatView(getLayoutRes(), drawer);
        }

        try {
            text = (CustomTextView) view.findViewById(R.id.amd_layout_text);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            notifications = (CustomTextView) view.findViewById(R.id.amd_layout_notification);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            section_left_icon = (ImageView) view.findViewById(R.id.amd_layout_left_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            section_right_icon = (ImageView) view.findViewById(R.id.amd_layout_right_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // hide text on icon banner
        if (hasIcon && fullWidthIcon) {
            text.setVisibility(View.GONE);
            notifications.setVisibility(View.GONE);
        }

        int rippleColor = values.getColor(R.styleable.MaterialSection_sectionRippleColor, 0x16000000);
        try {
            if (view.findViewById(R.id.amd_layout_touch_area) instanceof RelativeLayout) {
                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.amd_layout_touch_area);
                relativeLayout.setOnClickListener(this);
                relativeLayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.amd_layout_ripple) instanceof MaterialPlain) {
                MaterialPlain rippleLayout = (MaterialPlain) view.findViewById(R.id.amd_layout_ripple);
                rippleLayout.setOnClickListener(this);
                rippleLayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.amd_layout_ripple) instanceof MaterialRippleLayout) {
                MaterialRippleLayout rippleLayout = (MaterialRippleLayout) view.findViewById(R.id.section_ripple);
                rippleLayout.setRippleColor(rippleColor);
                rippleLayout.setOnClickListener(this);
                rippleLayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.amd_layout_ripple) instanceof MaterialRippleLayoutNineOld) {
                MaterialRippleLayoutNineOld rippleLayout = (MaterialRippleLayoutNineOld) view.findViewById(R.id.section_ripple);
                rippleLayout.setRippleColor(rippleColor);
                rippleLayout.setOnClickListener(this);
                rippleLayout.setOnTouchListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //colorPressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorPressed, 0x16000000);
        colorUnpressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColor, 0x00FFFFFF);
        colorSelected = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorSelected, 0x0A000000);
        colorOnPressHighlight = values.getColor(R.styleable.MaterialSection_sectionOnPressHighLightColor, 0x0A000000);

        iconColor = values.getColor(R.styleable.MaterialSection_sectionColorIcon, 0x000);
        textColor = values.getColor(R.styleable.MaterialSection_sectionColorText, 0x000);
        notificationColor = values.getColor(R.styleable.MaterialSection_sectionColorNotification, 0x000);
        sectionDivided = values.getBoolean(R.styleable.MaterialSection_sectionDivider, false);

        // set text color into the view
        if (textColor != 0x000) {
            text.setTextColor(textColor);
        }

        if (notificationColor != 0x000) {
            notifications.setTextColor(notificationColor);
        }

        isSelected = false;
        hasSectionColor = false;
        hasColorDark = false;
        numberNotifications = 0;
        fillIconColor = true;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void select() {
        isSelected = true;
        view.setBackgroundColor(colorSelected);
        if (hasSectionColor) {
            text.setTextColor(iconColor);
        }
    }

    @Override
    public void unSelect() {
        isSelected = false;
        view.setBackgroundColor(colorUnpressed);

        if (hasSectionColor) {
            text.setTextColor(textColor);
        }
    }

    /*public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }*/

    /*public void setOnClickListener(final MaterialSectionOnClickListener listener) {
        this.sectionListener = listener;
    }*/
    @Override
    public View getView() {
        return view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.text.setText(title);
    }

    public void setIconView(Drawable drawbleicon) {
        if (this.section_left_icon != null) {
            this.section_left_icon.setImageDrawable(drawbleicon);
            if (fillIconColor)
                this.section_left_icon.setColorFilter(iconColor);
            this.section_left_icon.setVisibility(View.VISIBLE);
        }

    }

    /*public void setIconView(Bitmap drawbleicon) {
        if (this.section_left_icon != null) {
            this.section_left_icon.setImageBitmap(drawbleicon);
            if (fillIconColor)
                this.section_left_icon.setColorFilter(iconColor);

            this.section_left_icon.setVisibility(View.VISIBLE);
        }
    }*/

    public boolean hasSectionColorDark() {
        return hasColorDark;
    }

    public MaterialItemSection setSectionColor(int color) {

        sectionColor = color;
        iconColor = color;
        if (section_left_icon != null)
            section_left_icon.setColorFilter(sectionColor);

        hasSectionColor = true;

        return this;
    }

    public int getSectionColorDark() {
        return colorDark;
    }

    public MaterialItemSection setSectionColor(int color, int colorDark) {
        setSectionColor(color);
        hasColorDark = true;
        this.colorDark = colorDark;

        return this;
    }

    public boolean hasSectionColor() {
        return hasSectionColor;
    }

    public int getSectionColor() {
        return iconColor;
    }

    /**
     * Set the number of notification for this section
     *
     * @param notifications the number of notification active for this section
     */
    public void setNotifications(int notifications) {
        String textNotification;

        textNotification = String.valueOf(notifications);

        if (notifications < 1) {
            textNotification = "";
        } else if (notifications > 99) {
            textNotification = "99+";
        } else if (notifications == 0) {
            this.notifications.setVisibility(View.GONE);
        }

        this.notifications.setText(textNotification);
        numberNotifications = notifications;
    }

    public int getNotifications() {
        return numberNotifications;
    }


    //public void setBottom(boolean bottom) {
    //   this.bottom = bottom;
    //}

    public boolean isFillIconColor() {
        return fillIconColor;
    }

    public void setFillIconColor(boolean fillIconColor) {
        this.fillIconColor = fillIconColor;
    }

    public TextView getText() {
        return text;
    }

    public ImageView getIconView() {
        return section_left_icon;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    @Override
    public void onClick(View v) {

        if (sectionListener != null) {
            final MaterialItemSection section = this;

            MaterialSectionChangeListener sectionChangeListener = drawer.getSectionChangeListener();

            if (sectionChangeListener != null)
                sectionChangeListener.onBeforeChangeSection(this);
            sectionListener.onClick(section, v);

            if (sectionChangeListener != null)
                sectionChangeListener.onAfterChangeSection(this);
        }
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionEvent = event.getActionMasked();
        switch (actionEvent) {
            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(colorOnPressHighlight);
                return false;
            case MotionEvent.ACTION_CANCEL:
                if (isSelected)
                    view.setBackgroundColor(colorSelected);
                else
                    view.setBackgroundColor(colorUnpressed);
                return false;
            case MotionEvent.ACTION_UP:
                checkrefresh();
                return false;
            default:
                return false;
        }
    }

    public void checkrefresh() {
        if (!isSelected) view.setBackgroundColor(colorUnpressed);
    }

}
