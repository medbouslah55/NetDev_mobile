/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.services.MenuServices;

/**
 *
 * @author Louay
 */
public class PieChart extends BaseForm {

//    private ChartService dogS;
    private Menu high;
    private Menu low;
    private Menu mid;
    Form current;
    Form t;

    /**
     *
     * Creates a renderer for the specified colors.
     *
     */
    public PieChart(Resources res) {
        super("Nos Menus", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Statistique");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 16) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 16);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(
                sl
        ));
        
        /****************************/
        t = createPieChartForm();
        add(t);
        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(res).show());
    }

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(70);
        renderer.setLegendTextSize(70);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        
        high = MenuServices.getInstance().getHigh();
        
        mid = MenuServices.getInstance().getAvrage();
        
        low = MenuServices.getInstance().getLow();
        
        series.add("High", high.getTotalCalories());
        series.add("Avrage", mid.getTotalCalories());
        series.add("Low", low.getTotalCalories());
        
        return series;
    }

    public Form createPieChartForm() {
        // Generate the values
        double[] values = new double[]{33, 33, 34};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setLabelsTextSize(50);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        com.codename1.charts.views.PieChart chart = new com.codename1.charts.views.PieChart(buildCategoryDataset("Statistique Calorique", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Statistique Calorique", new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        return f;

    }

}
